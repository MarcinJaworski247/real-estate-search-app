package com.engine.realestatesearchapp.controllers;

import com.engine.realestatesearchapp.controllers.assemblers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.assemblers.RealEstateAssembler;
import com.engine.realestatesearchapp.controllers.requests.*;
import com.engine.realestatesearchapp.controllers.resources.FileResource;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.entities.File;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.services.FileService;
import com.engine.realestatesearchapp.services.RealEstateService;
import com.engine.realestatesearchapp.utilities.filters.RealEstateQueryFilters;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("real-estate")
@RequiredArgsConstructor
public class RealEstateController {

    private final CommonAssembler assembler;
    private final FileService fileService;
    private final RealEstateService realEstateService;
    private final RealEstateAssembler realEstateAssembler;

    @GetMapping
    @ApiOperation(value = "Get real estate offers page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you " +
                    "want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records " +
                    "per page", defaultValue = "10")
    })
    public PagedModel<RealEstateResource> getOffers(RealEstateQueryFilters filters,
            PagedResourcesAssembler<RealEstate> paged, @ApiIgnore @PageableDefault Pageable pageable) {
        return paged.toModel(realEstateService.getRealEstatePage(filters, pageable), realEstateAssembler);
    }

    @GetMapping("/{basic_info_id}/{real_estate_id}")
    @ApiOperation(value = "Get real estate offer by id")
    public RealEstateResource getRealEstateById(@PathVariable("basic_info_id") UUID basicInfoId,
            @PathVariable("real_estate_id") UUID realEstateId) {
        return realEstateService.getRealEstateResourceById(basicInfoId, realEstateId);
    }

    @GetMapping("/proposed")
    @ApiOperation(value = "Get current user proposed offers")
    public List<RealEstateResource> getCurrentUserProposedOffers() {
        return realEstateService.getCurrentUserProposedOffers().stream()
                .map(assembler::mapToResourceWithAvatar)
                .collect(Collectors.toList());
    }

    @GetMapping("/favourites")
    @ApiOperation(value = "Get current user favourite offers")
    public List<RealEstateResource> getCurrentUserFavourites() {
        return realEstateService.getCurrentUserFavourites().stream()
                .map(assembler::mapToResourceWithAvatar)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{basic_info_id}/{real_estate_id}")
    @ApiOperation(value = "Update real estate offer by id")
    public RealEstateResource updateRealEstate(@PathVariable("basic_info_id") UUID basicInfoId,
            @PathVariable("real_estate_id") UUID realEstateId,
            @RequestBody @Valid UpdateRealEstateRequest request) {
        return assembler.mapToResourceWithFiles(realEstateService.updateRealEstate(basicInfoId, realEstateId, request));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{basic_info_id}")
    @ApiOperation(value = "Delete real estate offer by id")
    public void deleteRealEstate(@PathVariable("basic_info_id") UUID realEstateId) {
        realEstateService.deleteRealEstate(realEstateId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{basic_info_id}/change-banned")
    @ApiOperation(value = "Ban or unban real estate offer")
    public void banRealEstate(@PathVariable("basic_info_id") UUID realEstateId, @RequestBody @Valid
        BanRealEstateRequest request) {
        realEstateService.changeRealEstateBanStatus(realEstateId, request);
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{basic_info_id}/set-sold")
    @ApiOperation(value = "Mark real estate offer as sold")
    public void sellRealEstate(@PathVariable("basic_info_id") UUID realEstateId) {
        realEstateService.setRealEstateAsSold(realEstateId);
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{basic_info_id}/set-favourite")
    @ApiOperation(value = "Mark real estate offer as user favourite")
    public void setRealEstateAsFavourite(@PathVariable("basic_info_id") UUID realEstateId) {
        realEstateService.addRealEstateToCurrentUserFavourites(realEstateId);
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{basic_info_id}/unset-favourite")
    @ApiOperation(value = "Unmark real estate offer as user favourite")
    public void removeRealEstateFromFavourites(@PathVariable("basic_info_id") UUID realEstateId) {
        realEstateService.removeRealEstateFromCurrentUserFavourites(realEstateId);
    }

//    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{basic_info_id}/offer-visits")
    @ApiOperation(value = "Increment offer visits counter")
    public RealEstateResource incrementVisitsCounter(@PathVariable("basic_info_id") UUID realEstateId) {
        return assembler.mapToResourceWithFiles(realEstateService.incrementVisitsCounter(realEstateId));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{basic_info_id}/phone-views")
    @ApiOperation(value = "Increment phone views counter of the offer")
    public RealEstateResource incrementPhoneViewsCounter(@PathVariable("basic_info_id") UUID realEstateId) {
        return assembler.mapToResourceWithFiles(realEstateService.incrementPhoneViewsCounter(realEstateId));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @ApiOperation(value = "Create real estate offer")
    public RealEstateResource createRealEstate(@RequestBody @Valid RealEstateRequest request) {
        return assembler.mapToResourceWithFiles(realEstateService.createRealEstate(request));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/{basic_info_id}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Upload file for the offer")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileResource> uploadOfferFiles(@PathVariable("basic_info_id") UUID realEstateId,
            @RequestPart("file") MultipartFile[] files) {
        return realEstateService.uploadOfferFiles(realEstateId, files).stream()
                .map(assembler::mapToResource)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/{real_estate_id}/files/{file_id}")
    @ApiOperation(value = "Download file related to the offer")
    public ResponseEntity<StreamingResponseBody> downloadFile(@PathVariable("real_estate_id") UUID realEstateId,
            @PathVariable("file_id") UUID fileId, final HttpServletResponse response) {
        File file = fileService.getFileById(fileId);
        StreamingResponseBody responseBody = getFileStreamingResponseBody(response, file);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/{real_estate_id}/files/{file_id}/img",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get offer image bytes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> getOfferImageBytes(@PathVariable("real_estate_id") UUID realEstateId,
            @PathVariable("file_id") UUID fileId) {
        byte[] logoBytes = fileService.getFileBytes(fileService.getFileById(fileId).getPath());
        return new ResponseEntity<>(logoBytes, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping(value = "/{real_estate_id}/files/{file_id}")
    @ApiOperation(value = "Delete file related to the offer")
    public void deleteApplicationFile(@PathVariable("real_estate_id") UUID realEstateId,
            @PathVariable("file_id") UUID fileId) {
        realEstateService.deleteOfferFile(realEstateId, fileId);
    }

    private StreamingResponseBody getFileStreamingResponseBody(HttpServletResponse response, File file) {
        InputStream inputStream = fileService.downloadFile(file);
        response.setContentType(file.getContentType());
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",
                file.getOriginalFileName()));

        return outputStream -> {
            IOUtils.copy(inputStream, outputStream);
        };
    }
}
