package me.choi.userservice.service;

import me.choi.userservice.dto.UserDto;
import me.choi.userservice.jpa.UserEntity;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();

}
