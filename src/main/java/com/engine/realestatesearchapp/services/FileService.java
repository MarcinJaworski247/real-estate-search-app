package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.repositiories.FileRepository;
import com.engine.realestatesearchapp.repositiories.entities.File;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.utilities.exceptions.InvalidRequestException;
import com.engine.realestatesearchapp.utilities.exceptions.NotFoundException;
import com.engine.realestatesearchapp.utilities.exceptions.ServerErrorException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    @Value("${files.upload-path}")
    private String fileUploadPath;

    @Transactional
    public File createFile(String fileName, int version, MultipartFile multipartFile, RealEstate offer) {
        String path = fileUploadPath + java.io.File.separator + StringUtils.cleanPath(fileName);
        validateImageContentType(multipartFile.getContentType());
        uploadFile(multipartFile, path);
        File file = File.builder()
                .path(path)
                .contentType(multipartFile.getContentType())
                .originalFileName(multipartFile.getOriginalFilename())
                .version(version)
                .realEstateOffer(offer)
                .build();
        return fileRepository.save(file);
    }

    public InputStream downloadFile(File file) {
        final java.io.File initialFile = new java.io.File(file.getPath());
        try {
            return new FileInputStream(initialFile);
        } catch (IOException e) {
            throw new ServerErrorException("Could not download file " + file.getOriginalFileName());
        }
    }

    public StreamingResponseBody getFileStreamingResponseBody(HttpServletResponse response, File file) {
        InputStream inputStream = downloadFile(file);
        response.setContentType(file.getContentType());
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",
                file.getOriginalFileName()));
        return outputStream -> {
            IOUtils.copy(inputStream, outputStream);
        };
    }

    public byte[] getFileBytes(String path) {
        final java.io.File initialFile = new java.io.File(path);
        try {
            return FileUtils.readFileToByteArray(initialFile);
        } catch (IOException e) {
            throw new ServerErrorException("Could not download file");
        }
    }

    public byte[] getAvatarBytes(String path) {
        byte[] originalFile = getFileBytes(path);
        return scale(originalFile);
    }

    public byte[] scale(byte[] fileData) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            int height = (int) (img.getHeight() * 0.5);
            int width = (int) (img.getWidth() * 0.5);
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
            throw new ServerErrorException("IOException in scale");
        }
    }

    public File getFileById(UUID fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException(String.format("File with id %s not found", fileId)));
    }

    @Transactional
    public void deleteFile(UUID fileId) {
        File file = getFileById(fileId);
        boolean result = FileUtils.deleteQuietly(new java.io.File(file.getPath()));
        if (!result) {
            throw new ServerErrorException("Could not delete file " + file.getOriginalFileName());
        }
        fileRepository.delete(file);
    }

    public void validateImageContentType(String contentType) {
        String[] allowedFileFormats = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE,
                MediaType.IMAGE_GIF_VALUE};
        if (!Arrays.asList(allowedFileFormats).contains(contentType)) {
            throw new InvalidRequestException("Invalid file format");
        }
    }

    private void uploadFile(MultipartFile multipartFile, String path) {
        java.io.File destinationFile = new java.io.File(path);
        try {
            FileUtils.copyToFile(multipartFile.getInputStream(), destinationFile);
        } catch (IOException e) {
            throw new ServerErrorException(String.format("Could not store file %s. Reason: %s",
                    multipartFile.getOriginalFilename(), e.getMessage()));
        }
    }

}
