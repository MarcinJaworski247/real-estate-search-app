package com.engine.realestatesearchapp.controllers;

import com.engine.realestatesearchapp.controllers.assemblers.CommonAssembler;
import com.engine.realestatesearchapp.controllers.assemblers.RealEstateAssembler;
import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.requests.UpdateRealEstateRequest;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("real-estate")
@RequiredArgsConstructor
public class RealEstateController {

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

    @GetMapping("/{real_estate_id}")
    @ApiOperation(value = "Get real estate offer by id")
    public RealEstateResource getRealEstateById(@PathVariable("real_estate_id") UUID realEstateId) {
        return CommonAssembler.mapToResource(realEstateService.getRealEstateById(realEstateId));
    }

    @PatchMapping("/{real_estate_id}")
    @ApiOperation(value = "Update real estate offer by id")
    public RealEstateResource updateRealEstate(@PathVariable("real_estate_id") UUID realEstateId,
            @RequestBody @Valid UpdateRealEstateRequest request) {
        return CommonAssembler.mapToResource(realEstateService.updateRealEstate(realEstateId, request));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{real_estate_id}")
    @ApiOperation(value = "Delete real estate offer by id")
    public void deleteRealEstate(@PathVariable("real_estate_id") UUID realEstateId) {
        realEstateService.deleteRealEstate(realEstateId);
    }

    @PatchMapping("/{real_estate_id}/set-sold")
    @ApiOperation(value = "Mark real estate offer as sold")
    public void sellRealEstate(@PathVariable("real_estate_id") UUID realEstateId) {
        realEstateService.setRealEstateAsSold(realEstateId);
    }

    @PostMapping
    @ApiOperation(value = "Create real estate offer")
    public RealEstateResource createRealEstate(@RequestBody @Valid RealEstateRequest request) {
        return CommonAssembler.mapToResource(realEstateService.createRealEstate(request));
    }

    @PostMapping(value = "/{real_estate_id}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Upload file for the offer")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileResource> uploadOfferFiles(@PathVariable("real_estate_id") UUID realEstateId,
            @RequestPart("file") MultipartFile[] files) {
        return realEstateService.uploadOfferFiles(realEstateId, files).stream()
                .map(CommonAssembler::mapToResource)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{real_estate_id}/files/{file_id}")
    @ApiOperation(value = "Download file related to the offer")
    public ResponseEntity<StreamingResponseBody> downloadFile(@PathVariable("real_estate_id") UUID realEstateId,
            @PathVariable("file_id") UUID fileId, final HttpServletResponse response) {
        File file = fileService.getFileById(fileId);
        StreamingResponseBody responseBody = getFileStreamingResponseBody(response, file);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping(value = "/{real_estate_id}/files/{file_id}/img",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get offer image bytes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> getOfferImageBytes(@PathVariable("real_estate_id") UUID realEstateId,
            @PathVariable("file_id") UUID fileId) {
        File file = fileService.getFileById(fileId);
        byte[] logoBytes = fileService.getFileBytes(file);
        return new ResponseEntity<>(logoBytes, HttpStatus.OK);
    }

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
