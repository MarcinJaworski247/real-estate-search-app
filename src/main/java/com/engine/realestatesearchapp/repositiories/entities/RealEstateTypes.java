package com.engine.realestatesearchapp.repositiories.entities;

import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.OfferType;
import com.engine.realestatesearchapp.repositiories.enums.PlotType;
import com.engine.realestatesearchapp.repositiories.enums.PremisesPurpose;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import com.engine.realestatesearchapp.repositiories.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateTypes {

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private RealEstateCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "OFFER_TYPE")
    private OfferType offerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "PLOT_TYPE")
    private PlotType plotType;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROOM_TYPE")
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @Column(name = "HOUSE_TYPE")
    private HouseType houseType;

    @Enumerated(EnumType.STRING)
    @Column(name = "FLAT_TYPE")
    private FlatType flatType;

    @Enumerated(EnumType.STRING)
    @Column(name = "PREMISES_PURPOSE")
    private PremisesPurpose premisesPurpose;

}
