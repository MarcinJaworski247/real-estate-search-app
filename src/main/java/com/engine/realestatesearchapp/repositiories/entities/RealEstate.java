package com.engine.realestatesearchapp.repositiories.entities;

import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.OfferType;
import com.engine.realestatesearchapp.repositiories.enums.PlotType;
import com.engine.realestatesearchapp.repositiories.enums.PremisesPurpose;
import com.engine.realestatesearchapp.repositiories.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "REAL_ESTATE")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "RENT")
    private BigDecimal rent;

    @Column(name = "SIZE", nullable = false)
    private BigDecimal size;

    @Column(name = "PLOT_SIZE")
    private BigDecimal plotSize;

    @Column(name = "DELETED")
    private boolean deleted = false;

    @Column(name = "SOLD")
    private boolean sold = false;

    @Column(name = "ROOMS")
    private short roomsNumber;

    @Column(name = "FLOORS")
    private short floors;

    @Column(name = "FURNISHED")
    private Boolean furnished;

    @Embedded
    private RealEstateTypes types;

    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void setOfferType(String offerType) {
        this.types.setOfferType(OfferType.valueOfLabel(offerType));
    }

    public void setPlotType(String plotType) {
        this.types.setPlotType(PlotType.valueOfLabel(plotType));
    }

    public void setRoomType(String roomType) {
        this.types.setRoomType(RoomType.valueOfLabel(roomType));
    }

    public void setHouseType(String houseType) {
        this.types.setHouseType(HouseType.valueOfLabel(houseType));
    }

    public void setFlatType(String flatType) {
        this.types.setFlatType(FlatType.valueOfLabel(flatType));
    }

    public void setPremisesPurpose(String premisesPurpose) {
        this.types.setPremisesPurpose(PremisesPurpose.valueOfLabel(premisesPurpose));
    }

}
