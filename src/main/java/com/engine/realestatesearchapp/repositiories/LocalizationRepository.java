package com.engine.realestatesearchapp.repositiories;

import com.engine.realestatesearchapp.repositiories.entities.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Integer> {
}
