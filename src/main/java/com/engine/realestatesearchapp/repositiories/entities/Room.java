package com.engine.realestatesearchapp.repositiories.entities;

import com.engine.realestatesearchapp.repositiories.enums.RoomType;
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

@Entity
@Table(name = "ROOMS")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Room extends RealEstateInfo {

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private RoomType type;

}