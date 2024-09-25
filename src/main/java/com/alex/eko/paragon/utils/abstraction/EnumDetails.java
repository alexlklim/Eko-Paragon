package com.alex.eko.paragon.utils.abstraction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface EnumDetails {

    String getDisplayName();
    String getDescription();



    // get all values in the enum as EnumDTOs to represent them on client side
    static <T extends Enum<T> & EnumDetails> List<EnumDTO> getAllEnumDTOs(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(e -> new EnumDTO(e.name(), e.getDisplayName(), e.getDescription()))
                .collect(Collectors.toList());
    }

    // get enum by display name and return its ENUM representation
    static <T extends Enum<T> & EnumDetails> T getByName(Class<T> enumClass, String displayName) {
        for (T enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.getDisplayName().equalsIgnoreCase(displayName)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("No enum constant with display name " + displayName);
    }


}
