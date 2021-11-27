package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.controllers.assemblers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.requests.UpdateRealEstateRequest;
import com.engine.realestatesearchapp.repositiories.HouseRepository;
import com.engine.realestatesearchapp.repositiories.entities.File;
import com.engine.realestatesearchapp.repositiories.entities.House;
import com.engine.realestatesearchapp.repositiories.entities.Localization;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
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

import static com.engine.realestatesearchapp.utilities.specifications.HouseSpecifications.getHouseSpecifications;
import static com.engine.realestatesearchapp.utilities.specifications.RealEstateSpecifications.getRealEstateSpecification;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final CommonAssembler assembler;
    private final HouseRepository houseRepository;
    private final LocalizationService localizationService;
    private final FileService fileService;

    public House createHouseOffer(RealEstateRequest request) {
        request.validateHouseFields();
        House entity = assembler.mapToHouseEntity(request);
        Localization localization = localizationService.getLocalizationById(request.getLocalizationId());
        entity.setLocalization(localization);
        return houseRepository.save(entity);
    }

    public RealEstate updateRealEstate(UUID realEstateId, UpdateRealEstateRequest request) {
        RealEstate entity = getHouseOfferById(realEstateId);
        request.getTitle().ifPresent(entity::setTitle);
        request.getDescription().ifPresent(entity::setDescription);
        request.getOfferType().ifPresent(entity::setOfferType);
        request.getPrice().ifPresent(entity::setPrice);
        request.getSize().ifPresent(entity::setSize);
        request.getRent().ifPresent(entity::setRent);
        request.isFurnished().ifPresent(entity::setFurnished);
        request.getFloors().ifPresent(entity::setFloors);
        request.getRoomsNumber().ifPresent(entity::setRoomsNumber);
        request.getPlotSize().ifPresent(entity::setPlotSize);
        if (request.getLocalizationId().isPresent()) {
            Localization localization = localizationService.getLocalizationById(request.getLocalizationId().get());
            entity.setLocalization(localization);
        }
        request.getPlotType().ifPresent(entity::setPlotType);
        request.getRoomType().ifPresent(entity::setRoomType);
        request.getHouseType().ifPresent(entity::setHouseType);
        request.getFlatType().ifPresent(entity::setFlatType);
        request.getPremisesPurpose().ifPresent(entity::setPremisesPurpose);
        return houseRepository.save(entity);
    }

    public RealEstate setRealEstateAsSold(UUID realEstateId) {
        RealEstate entity = getHouseOfferById(realEstateId);
        entity.setSold(true);
        return houseRepository.save(entity);
    }

    public RealEstate deleteRealEstate(UUID realEstateId) {
        RealEstate entity = getHouseOfferById(realEstateId);
        entity.setDeleted(true);
        return houseRepository.save(entity);
    }

    public House getHouseOfferById(UUID id) {
        return houseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("House with id %s not found", id)));
    }

    public Page<House> getRealEstatePage(RealEstateQueryFilters filters, Pageable pageable) {
        Specification<House> specification = getHouseSpecifications(filters);
        return houseRepository.findAll(specification, pageable);
    }

    public List<File> uploadOfferFiles(UUID realEstateId, MultipartFile[] files) {
        House realEstate = getHouseOfferById(realEstateId);
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
        House house = getHouseOfferById(realEstateId);
        house.getFiles().removeIf(file -> file.getId().equals(fileId));
        houseRepository.save(house);
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
