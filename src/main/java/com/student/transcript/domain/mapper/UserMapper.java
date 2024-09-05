package com.student.transcript.domain.mapper;

import com.student.transcript.domain.dto.UserDTO;
import com.student.transcript.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getName()
        );
    }
    public static List<UserDTO> toUserDTO(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(toUserDTO(user));
        }
        return userDTOs;
    }
    public static User toUser(UserDTO userDTO) {
        return new User(
            userDTO.getId(),
            userDTO.getName()
        );
    }
    public static List<User> toUser(List<UserDTO> userDTOs) {
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            users.add(toUser(userDTO));
        }
        return users;
    }
}
