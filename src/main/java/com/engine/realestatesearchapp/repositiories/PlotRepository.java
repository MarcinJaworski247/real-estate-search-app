package com.engine.realestatesearchapp.repositiories;

import com.engine.realestatesearchapp.repositiories.entities.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlotRepository extends JpaRepository<Plot, UUID> {
}
