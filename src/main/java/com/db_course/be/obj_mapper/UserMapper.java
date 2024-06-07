package com.db_course.be.obj_mapper;

import com.db_course.dto.UserDto;
import com.db_course.be.entity_model.User;

public class UserMapper {

    public static UserDto userToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

}
