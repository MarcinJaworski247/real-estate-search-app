package com.engine.realestatesearchapp.repositiories.entities;

import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.PlotType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "HOUSES")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class House {

    @Id
    @Column(name = "ID")
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private HouseType type;

    @Column(name = "PLOT_SIZE", nullable = false)
    private BigDecimal plotSize;

    @Column(name = "FURNISHED")
    private Boolean furnished;

    @Column(name = "ROOMS_NUMBER")
    private short roomsNumber;

    @Column(name = "FLOORS_NUMBER")
    private short floorsNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REAL_ESTATE_INFO_ID", nullable = false)
    private RealEstate realEstateInfo;
}
