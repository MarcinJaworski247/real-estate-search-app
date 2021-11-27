package com.engine.realestatesearchapp.repositiories.entities;

import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateInfo {

    @Id
    @Column(name = "ID")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY", nullable = false)
    private RealEstateCategory category;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "SIZE", nullable = false)
    private BigDecimal size;

    @Column(name = "DELETED")
    private boolean deleted;

    @ManyToOne
    private Localization localization;

    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

}