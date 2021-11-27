package com.engine.realestatesearchapp.repositiories.entities;

import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "FLATS")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Flat extends RealEstateInfo {

    @Column(name = "RENT", nullable = false)
    private BigDecimal rent;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private FlatType type;

    @Column(name = "FURNISHED")
    private boolean furnished;

    @Column(name = "LEVEL")
    private short level;

    @Column(name = "ROOMS_NUMBER")
    private short roomsNumber;

}
