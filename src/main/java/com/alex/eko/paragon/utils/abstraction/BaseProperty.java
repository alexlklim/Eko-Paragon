package com.alex.eko.paragon.utils.abstraction;

import java.time.LocalDateTime;

public interface BaseProperty {

    Long getId();
    void setId(Long id);


    Boolean getDeleted();
    void setDeleted(Boolean deleted);


    Long getCreatedBy();
    void setCreatedBy(Long createdBy);


    LocalDateTime getCreated();
    void setCreated(LocalDateTime created);

}
