package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.controllers.assemblers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.requests.UpdateRealEstateRequest;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.FlatRepository;
import com.engine.realestatesearchapp.repositiories.HouseRepository;
import com.engine.realestatesearchapp.repositiories.PlotRepository;
import com.engine.realestatesearchapp.repositiories.PremisesRepository;
import com.engine.realestatesearchapp.repositiories.RealEstateRepository;
import com.engine.realestatesearchapp.repositiories.RoomRepository;
import com.engine.realestatesearchapp.repositiories.entities.File;
import com.engine.realestatesearchapp.repositiories.entities.Flat;
import com.engine.realestatesearchapp.repositiories.entities.House;
import com.engine.realestatesearchapp.repositiories.entities.Localization;
import com.engine.realestatesearchapp.repositiories.entities.Plot;
import com.engine.realestatesearchapp.repositiories.entities.Premises;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.repositiories.entities.Room;
import com.engine.realestatesearchapp.repositiories.entities.User;
import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.PlotType;
import com.engine.realestatesearchapp.repositiories.enums.PremisesPurpose;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import com.engine.realestatesearchapp.repositiories.enums.RoomType;
import com.engine.realestatesearchapp.utilities.exceptions.InvalidRequestException;
import com.engine.realestatesearchapp.utilities.exceptions.NotFoundException;
import com.engine.realestatesearchapp.utilities.filters.RealEstateQueryFilters;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static com.engine.realestatesearchapp.utilities.specifications.RealEstateSpecifications.getRealEstateSpecification;

@Service
@RequiredArgsConstructor
public class RealEstateService {

    private final CommonAssembler assembler;
    private final RealEstateRepository realEstateRepository;
    private final HouseRepository houseRepository;
    private final PlotRepository plotRepository;
    private final PremisesRepository premisesRepository;
    private final FlatRepository flatRepository;
    private final RoomRepository roomRepository;
    private final LocalizationService localizationService;
    private final FileService fileService;
    private final UserService userService;

    public RealEstate createRealEstate(RealEstateRequest request) {
        RealEstate basicInfo = assembler.mapToEntity(request);
        Localization localization = localizationService.getLocalizationById(request.getLocalizationId());
        basicInfo.setLocalization(localization);
        User user = userService.getCurrentUser();
        basicInfo.setUser(user);
        basicInfo = realEstateRepository.save(basicInfo);
        if (request.getCategory().equals(RealEstateCategory.HOUSES.label)) {
            request.validateHouseFields();
            House house = assembler.mapToHouseEntity(request);
            house.setBasicInfo(basicInfo);
            house = houseRepository.save(house);
            basicInfo.setRealEstateId(house.getId());
        } else if (request.getCategory().equals(RealEstateCategory.PLOTS.label)) {
            request.validatePlotFields();
            Plot plot = assembler.mapToPlotEntity(request);
            plot.setBasicInfo(basicInfo);
            plot = plotRepository.save(plot);
            basicInfo.setRealEstateId(plot.getId());
        } else if (request.getCategory().equals(RealEstateCategory.OFFICES_AND_PREMISES.label)) {
            request.validatePremisesFields();
            Premises premises = assembler.mapToPremisesEntity(request);
            premises.setBasicInfo(basicInfo);
            premises = premisesRepository.save(premises);
            basicInfo.setRealEstateId(premises.getId());
        } else if (request.getCategory().equals(RealEstateCategory.FLATS.label)) {
            request.validateFlatFields();
            Flat flat = assembler.mapToFlatEntity(request);
            flat.setBasicInfo(basicInfo);
            flat = flatRepository.save(flat);
            basicInfo.setRealEstateId(flat.getId());
        } else if (request.getCategory().equals(RealEstateCategory.ROOMS.label)) {
            request.validateRoomFields();
            Room room = assembler.mapToRoomEntity(request);
            room.setBasicInfo(basicInfo);
            room = roomRepository.save(room);
            basicInfo.setRealEstateId(room.getId());
        } else {
            throw new InvalidRequestException("Category not supported");
        }
        return realEstateRepository.save(basicInfo);
    }

    public RealEstate updateRealEstate(UUID basicInfoId, UUID realEstateId, UpdateRealEstateRequest request) {
        RealEstate basicInfo = getRealEstateById(basicInfoId);
        request.getTitle().ifPresent(basicInfo::setTitle);
        request.getDescription().ifPresent(basicInfo::setDescription);
        request.getPrice().ifPresent(basicInfo::setPrice);
        request.getSize().ifPresent(basicInfo::setSize);
        request.getOfferType().ifPresent(basicInfo::setOfferType);
        if (request.getLocalizationId().isPresent()) {
            Localization localization = localizationService.getLocalizationById(request.getLocalizationId().get());
            basicInfo.setLocalization(localization);
        }
        switch (basicInfo.getCategory()) {
            case HOUSES:
                House house = getHouseById(realEstateId);
                request.getHouseType()
                        .ifPresent(type -> house.setType(HouseType.valueOfLabel(request.getHouseType().get())));
                request.getRent().ifPresent(house::setRent);
                request.isFurnished().ifPresent(house::setFurnished);
                request.getFloors().ifPresent(house::setFloorsNumber);
                request.getRoomsNumber().ifPresent(house::setRoomsNumber);
                request.getPlotSize().ifPresent(house::setPlotSize);
                house.setBasicInfo(basicInfo);
                houseRepository.save(house);
                break;
            case PLOTS:
                Plot plot = getPlotById(realEstateId);
                request.getPlotType()
                        .ifPresent(type -> plot.setType(PlotType.valueOfLabel(request.getPlotType().get())));
                plot.setBasicInfo(basicInfo);
                plotRepository.save(plot);
                break;
            case OFFICES_AND_PREMISES:
                Premises premises = getPremisesById(realEstateId);
                request.getPremisesPurpose()
                        .ifPresent(purpose -> premises.setPurpose(PremisesPurpose.valueOfLabel((request.getPremisesPurpose()
                                .get()))));
                request.isFurnished().ifPresent(premises::setFurnished);
                premises.setBasicInfo(basicInfo);
                premisesRepository.save(premises);
                break;
            case FLATS:
                Flat flat = getFlatById(realEstateId);
                request.getRent().ifPresent(flat::setRent);
                request.getFlatType()
                        .ifPresent(type -> flat.setType(FlatType.valueOfLabel(request.getFlatType().get())));
                request.isFurnished().ifPresent(flat::setFurnished);
                request.getRoomsNumber().ifPresent(flat::setRoomsNumber);
                request.getFloors().ifPresent(flat::setLevel);
                flat.setBasicInfo(basicInfo);
                flatRepository.save(flat);
                break;
            case ROOMS:
                Room room = getRoomById(realEstateId);
                request.getRoomType()
                        .ifPresent(type -> room.setType(RoomType.valueOfLabel(request.getRoomType().get())));
                room.setBasicInfo(basicInfo);
                roomRepository.save(room);
                break;
            default:
                throw new InvalidRequestException("Category not supported");
        }
        return realEstateRepository.save(basicInfo);
    }

    public RealEstate setRealEstateAsSold(UUID realEstateId) {
        RealEstate entity = getRealEstateById(realEstateId);
        entity.setSold(true);
        return realEstateRepository.save(entity);
    }

    public RealEstate incrementVisitsCounter(UUID realEstateId) {
        RealEstate entity = getRealEstateById(realEstateId);
        entity.setVisitsCounter(entity.getVisitsCounter() + 1);
        return realEstateRepository.save(entity);
    }

    public RealEstate incrementPhoneViewsCounter(UUID realEstateId) {
        RealEstate entity = getRealEstateById(realEstateId);
        entity.setPhoneViewsCounter(entity.getPhoneViewsCounter() + 1);
        return realEstateRepository.save(entity);
    }

    public void deleteRealEstate(UUID realEstateId) {
        RealEstate entity = getRealEstateById(realEstateId);
        entity.setDeleted(true);
        realEstateRepository.save(entity);
    }

    public void addRealEstateToCurrentUserFavourites(UUID realEstateId) {
        User user = userService.getCurrentUser();
        RealEstate entity = getRealEstateById(realEstateId);
        user.getFavourites().add(entity);
        userService.saveUser(user);
    }

    public void removeRealEstateFromCurrentUserFavourites(UUID realEstateId) {
        User user = userService.getCurrentUser();
        RealEstate entity = getRealEstateById(realEstateId);
        user.getFavourites().remove(entity);
        userService.saveUser(user);
    }


    public List<RealEstate> getCurrentUserFavourites() {
        return userService.getCurrentUser().getFavourites();
    }

    public RealEstate getRealEstateById(UUID id) {
        return realEstateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Real estate with id %s not found", id)));
    }

    public RealEstateResource getRealEstateResourceById(UUID basicInfoId, UUID realEstateId) {
        RealEstate basicInfo = getRealEstateById(basicInfoId);
        switch (basicInfo.getCategory()) {
            case HOUSES:
                House house = getHouseById(realEstateId);
                return assembler.mapToHouseResource(house);
            case PLOTS:
                Plot plot = getPlotById(realEstateId);
                return assembler.mapToPlotResource(plot);
            case OFFICES_AND_PREMISES:
                Premises premises = getPremisesById(realEstateId);
                return assembler.mapToPremisesResource(premises);
            case FLATS:
                Flat flat = getFlatById(realEstateId);
                return assembler.mapToFlatResource(flat);
            case ROOMS:
                Room room = getRoomById(realEstateId);
                return assembler.mapToRoomResource(room);
            default:
                throw new InvalidRequestException("Category not supported");
        }
    }

    public House getHouseById(UUID id) {
        return houseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("House with id %s not found", id)));
    }

    public Plot getPlotById(UUID id) {
        return plotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Plot with id %s not found", id)));
    }

    public Premises getPremisesById(UUID id) {
        return premisesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Premises with id %s not found", id)));
    }

    public Flat getFlatById(UUID id) {
        return flatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Flat with id %s not found", id)));
    }

    public Room getRoomById(UUID id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Flat with id %s not found", id)));
    }

    public Page<RealEstate> getRealEstatePage(RealEstateQueryFilters filters, Pageable pageable) {
        Specification<RealEstate> specification = getRealEstateSpecification(filters);
        return realEstateRepository.findAll(specification, pageable);
    }

    public List<File> uploadOfferFiles(UUID realEstateId, MultipartFile[] files) {
        RealEstate realEstate = getRealEstateById(realEstateId);
        List<File> filesList = new ArrayList<>();
        Arrays.asList(files).forEach(file -> filesList.add(uploadFileForOffer(realEstate, file)));
        return filesList;
    }

    @Transactional
    public File uploadFileForOffer(RealEstate realEstate, MultipartFile multipartFile) {
        int version = getFileVersion(realEstate);
        String fileName = String.format("file_%s_%s.%s",
                realEstate.getId(),
                String.format("%02d", version),
                FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        return fileService.createFile(fileName, version, multipartFile, realEstate);
    }

    @Transactional
    public void deleteOfferFile(UUID realEstateId, UUID fileId) {
        RealEstate realEstate = getRealEstateById(realEstateId);
        realEstate.getFiles().removeIf(file -> file.getId().equals(fileId));
        realEstateRepository.save(realEstate);
        fileService.deleteFile(fileId);
    }

    private int getFileVersion(RealEstate realEstate) {
        int version = realEstate.getFiles().stream()
                .map(File::getVersion)
                .max(Comparator.naturalOrder()).orElse(-1);
        if (++version >= 99) {
            throw new InvalidRequestException("Limit of stored files for application has been " +
                    "reached");
        }
        return version;
    }

}
