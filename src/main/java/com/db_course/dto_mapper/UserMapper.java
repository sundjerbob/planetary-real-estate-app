package com.db_course.dto_mapper;

import com.db_course.dto.UserDto;
import com.db_course.entity_model.User;

public class UserMapper {

    public static UserDto userToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

}
