package com.app.artisandor.utils;

import com.app.artisandor.dto.UsersDTO;
import com.app.artisandor.entity.User;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class EntityToDtoConverterUtils {


    public UsersDTO convertToUsersDto(User users) {
        UsersDTO usersDto = new UsersDTO();
        usersDto.setId(users.getId());
        usersDto.setFirstName(users.getFirstName());
        usersDto.setLastName(users.getLastName());
        usersDto.setEmail(users.getEmail());
        usersDto.setPicture(users.getPicture() != null ? convertImageToBase64(users.getPicture()) : null);
        usersDto.setRole(users.getRole());
        usersDto.setCreatedAt(users.getCreatedAt());
        return usersDto;
    }
    

    private String convertImageToBase64(byte[] image) {
        if (image == null) {
            return null;
        } else {
            return Base64.getEncoder().encodeToString(image);
        }
    }
}
