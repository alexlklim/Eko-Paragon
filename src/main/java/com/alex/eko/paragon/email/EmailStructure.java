package com.alex.eko.paragon.email;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class EmailStructure {

    String email;
    EmailType emailType;
}
