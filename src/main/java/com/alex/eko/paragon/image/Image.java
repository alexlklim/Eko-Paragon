package com.alex.eko.paragon.image;

import com.alex.eko.paragon.utils.abstraction.BaseEntity;
import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "images")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image
        extends BaseEntity
        implements BaseProperty {


    Boolean isPublic;

    String name;

    String type;

    @Lob
    @Column(name = "byte_data", length = 1000)
    byte[] byteData;
}
