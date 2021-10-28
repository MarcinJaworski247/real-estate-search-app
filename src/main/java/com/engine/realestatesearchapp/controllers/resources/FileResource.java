package com.engine.realestatesearchapp.controllers.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResource {
    private UUID id;
    private String originalFileName;
    private String contentType;
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
