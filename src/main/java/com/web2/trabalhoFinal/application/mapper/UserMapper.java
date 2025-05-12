package com.web2.trabalhoFinal.application.mapper;

import com.web2.trabalhoFinal.application.dto.user.UserRequestDto;
import com.web2.trabalhoFinal.domain.model.user.User;

public class UserMapper {
    public static User toDomain(UserRequestDto dto) {
        return new User(dto.email, dto.password);
    }
}
