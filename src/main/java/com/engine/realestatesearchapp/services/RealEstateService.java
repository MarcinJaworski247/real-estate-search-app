package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.controllers.assemblers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.requests.UpdateRealEstateRequest;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.HouseRepository;
import com.engine.realestatesearchapp.repositiories.RealEstateRepository;
import com.engine.realestatesearchapp.repositiories.entities.File;
import com.engine.realestatesearchapp.repositiories.entities.House;
import com.engine.realestatesearchapp.repositiories.entities.Localization;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.OfferType;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
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
    private final LocalizationService localizationService;
    private final FileService fileService;

    public RealEstate createRealEstate(RealEstateRequest request) {
        RealEstate basicInfo = assembler.mapToEntity(request);
        Localization localization = localizationService.getLocalizationById(request.getLocalizationId());
        basicInfo.setLocalization(localization);
        basicInfo = realEstateRepository.save(basicInfo);
        if(request.getCategory().equals(RealEstateCategory.HOUSES.label)) {
            House house = assembler.mapToHouseEntity(request);
            house.setBasicInfo(basicInfo);
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
        if(basicInfo.getCategory().equals(RealEstateCategory.HOUSES)) {
            House house = getHouseById(realEstateId);
            request.getHouseType().ifPresent(type -> house.setType(HouseType.valueOfLabel(request.getHouseType().get())));
            request.getRent().ifPresent(house::setRent);
            request.isFurnished().ifPresent(house::setFurnished);
            request.getFloors().ifPresent(house::setFloorsNumber);
            request.getRoomsNumber().ifPresent(house::setRoomsNumber);
            request.getPlotSize().ifPresent(house::setPlotSize);
            house.setBasicInfo(basicInfo);
        } else {
            throw new InvalidRequestException("Category not supported");
        }
        return realEstateRepository.save(basicInfo);
    }

    public RealEstate setRealEstateAsSold(UUID realEstateId) {
        RealEstate entity = getRealEstateById(realEstateId);
        entity.setSold(true);
        return realEstateRepository.save(entity);
    }

    public RealEstate deleteRealEstate(UUID realEstateId) {
        RealEstate entity = getRealEstateById(realEstateId);
        entity.setDeleted(true);
        return realEstateRepository.save(entity);
    }

    public RealEstate getRealEstateById(UUID id) {
        return realEstateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Real estate with id %s not found", id)));
    }

    public RealEstateResource getRealEstateResourceById(UUID basicInfoId, UUID realEstateId) {
        RealEstate basicInfo = getRealEstateById(basicInfoId);
        if (basicInfo.getCategory().equals(RealEstateCategory.HOUSES)) {
            House house = getHouseById(realEstateId);
            return assembler.mapToHouseResource(house);
        } else {
            throw new InvalidRequestException("Category not supported");
        }
    }

    public House getHouseById(UUID id) {
        return houseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("House with id %s not found", id)));
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
