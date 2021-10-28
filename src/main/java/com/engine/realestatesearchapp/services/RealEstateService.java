package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.controllers.assemblers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.requests.UpdateRealEstateRequest;
import com.engine.realestatesearchapp.repositiories.RealEstateRepository;
import com.engine.realestatesearchapp.repositiories.entities.File;
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

import static com.engine.realestatesearchapp.utilities.specifications.RealEstateSpecifications.getRealEstateSpecification;

@Service
@RequiredArgsConstructor
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;
    private final FileService fileService;

    public RealEstate createRealEstate(RealEstateRequest request) {
        RealEstate entity = CommonAssembler.mapToEntity(request);
        return realEstateRepository.save(entity);
    }

    public RealEstate updateRealEstate(UUID realEstateId, UpdateRealEstateRequest request) {
        RealEstate entity = getRealEstateById(realEstateId);
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
        //request.getLocalizationId().ifPresent();
        request.getPlotType().ifPresent(entity::setPlotType);
        request.getRoomType().ifPresent(entity::setRoomType);
        request.getHouseType().ifPresent(entity::setHouseType);
        request.getFlatType().ifPresent(entity::setFlatType);
        request.getPremisesPurpose().ifPresent(entity::setPremisesPurpose);
        return realEstateRepository.save(entity);
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
