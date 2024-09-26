package com.alex.eko.paragon.security;

import com.alex.eko.paragon.security.domain.Role;
import com.alex.eko.paragon.security.domain.User;
import com.alex.eko.paragon.security.dto.RegisterDto;
import com.alex.eko.paragon.security.dto.UserDto;
import com.alex.eko.paragon.utils.DateService;
import lombok.Data;

import java.util.List;

@Data
public class UserMapper {

    public static User toUser(RegisterDto dto) {
        User user = new User();
        user.setFirstname(dto.getFirstName());
        user.setLastname(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setRoles(List.of(Role.USER));
        user.setActive(false);
        user.setLastActivity(DateService.getDateNow());

        return user;
    }


    public static UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstname());
        dto.setLastName(entity.getLastname());
        dto.setEmail(entity.getEmail());
        dto.setActive(entity.isActive());

        return dto;
    }


}
