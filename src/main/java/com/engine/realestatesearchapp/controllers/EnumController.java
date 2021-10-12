package com.engine.realestatesearchapp.controllers;

import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.OfferType;
import com.engine.realestatesearchapp.repositiories.enums.PlotType;
import com.engine.realestatesearchapp.repositiories.enums.PremisesPurpose;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import com.engine.realestatesearchapp.repositiories.enums.RoomType;
import com.engine.realestatesearchapp.repositiories.enums.Voivodeship;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("enums")
@RequiredArgsConstructor
public class EnumController {

    @GetMapping("/flat-type")
    public List<String> getFlatTypes() {
        return Stream.of(FlatType.values())
                .map(FlatType::getLabel)
                .collect(Collectors.toList());
    }

    @GetMapping("/house-type")
    public List<String> getHouseTypes() {
        return Stream.of(HouseType.values())
                .map(HouseType::getLabel)
                .collect(Collectors.toList());
    }

    @GetMapping("/offer-type")
    public List<String> getOfferTypes() {
        return Stream.of(OfferType.values())
                .map(OfferType::getLabel)
                .collect(Collectors.toList());
    }

    @GetMapping("/plot-type")
    public List<String> getPlotTypes() {
        return Stream.of(PlotType.values())
                .map(PlotType::getLabel)
                .collect(Collectors.toList());
    }

    @GetMapping("/premises-purpose")
    public List<String> getPremisesPurposes() {
        return Stream.of(PremisesPurpose.values())
                .map(PremisesPurpose::getLabel)
                .collect(Collectors.toList());
    }

    @GetMapping("/real-estate-category")
    public List<String> getRealEstateCategories() {
        return Stream.of(RealEstateCategory.values())
                .map(RealEstateCategory::getLabel)
                .collect(Collectors.toList());
    }

    @GetMapping("/room-type")
    public List<String> getRoomTypes() {
        return Stream.of(RoomType.values())
                .map(RoomType::getLabel)
                .collect(Collectors.toList());
    }

    @GetMapping("/voivodeship")
    public List<String> getVoivodeships() {
        return Stream.of(Voivodeship.values())
                .map(Voivodeship::getLabel)
                .collect(Collectors.toList());
    }
}
