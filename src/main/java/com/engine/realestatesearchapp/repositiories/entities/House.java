package com.engine.realestatesearchapp.repositiories.entities;

import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "HOUSES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @org.hibernate.annotations.Type(type = "pg-uuid")
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

    @Column(name = "RENT")
    private BigDecimal rent;

    @OneToOne
    private RealEstate basicInfo;

}