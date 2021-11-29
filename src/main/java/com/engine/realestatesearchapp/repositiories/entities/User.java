package com.engine.realestatesearchapp.repositiories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;

    @JoinColumn(name = "USERNAME", nullable = false)
    private String username;

    @JoinColumn(name = "ENCRYPTED_PASS", nullable = false)
    private String encryptedPassword;

    @JoinColumn(name = "PHONE_NUMBER", nullable = false)
    private Integer phoneNumber;
}
