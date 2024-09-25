package com.alex.eko.paragon.utils.abstraction;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore @CreatedDate @Column(name = "created")
    LocalDateTime created;

    @JsonIgnore @LastModifiedDate @Column(name = "updated")
    LocalDateTime updated;

    @JsonIgnore @Column(name = "created_by")
    Long createdBy;


    @Column(name = "deleted")
    Boolean deleted;

}
