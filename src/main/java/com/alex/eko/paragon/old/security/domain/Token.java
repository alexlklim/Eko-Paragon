package com.alex.eko.paragon.old.security.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    UUID token;

    @CreatedDate
    LocalDateTime created;


    @LastModifiedDate
    LocalDateTime updated;

    @LastModifiedDate
    LocalDateTime expired;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    User user;

}