package com.example.dits.mapper;

import com.example.dits.dto.UserInfoDTO;
import com.example.dits.entity.User;
import com.example.dits.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final UserService userService;

    public User convertUserInfoDTOToUser(UserInfoDTO userInfoDTO) {
        return userService.getUserByLogin(userInfoDTO.getLogin());
    }
}