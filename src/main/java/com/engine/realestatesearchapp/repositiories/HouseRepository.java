package com.engine.realestatesearchapp.repositiories;

import com.engine.realestatesearchapp.repositiories.entities.House;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HouseRepository extends JpaRepository<House, UUID>, JpaSpecificationExecutor<House> {
}
