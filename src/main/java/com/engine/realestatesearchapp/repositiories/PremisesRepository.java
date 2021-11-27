package com.engine.realestatesearchapp.repositiories;

import com.engine.realestatesearchapp.repositiories.entities.Premises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PremisesRepository extends JpaRepository<Premises, UUID> {
}
