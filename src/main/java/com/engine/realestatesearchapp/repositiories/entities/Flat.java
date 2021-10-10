package com.engine.realestatesearchapp.repositiories.entities;

import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
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
@Table(name = "FLATS")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Flat {

    @Id
    @Column(name = "ID")
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REAL_ESTATE_INFO_ID", nullable = false)
    private RealEstate realEstateInfo;

}
