package com.engine.realestatesearchapp.controllers.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResource {
    private UUID id;
    private String originalFileName;
    private String contentType;
    private byte[] bytes;
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
