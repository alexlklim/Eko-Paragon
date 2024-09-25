package com.alex.eko.paragon.utils.abstraction;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnumDTO {

    String name;
    String displayName;
    String description;
}
