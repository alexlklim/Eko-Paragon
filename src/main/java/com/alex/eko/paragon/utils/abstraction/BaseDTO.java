package com.alex.eko.paragon.utils.abstraction;

import com.alex.eko.paragon.utils.UtilString;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseDTO implements Serializable {


    @Schema(description = UtilString.ID_DESCRIPTION, example = "1")
    Long id;

    @Schema(description = UtilString.DATETIME_DESCRIPTION, example = "2024-09-01T14:30:00")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime created;

    @Schema(description = UtilString.CREATED_BY_DESCRIPTION, example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long createdBy;

    @Schema(description = UtilString.DELETED_DESCRIPTION, example = "false")
    Boolean deleted;



}
