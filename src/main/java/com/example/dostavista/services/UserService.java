package com.example.dostavista.services;

import com.example.dostavista.dtos.usersDtos.AddUserDto;
import com.example.dostavista.dtos.usersDtos.AllUsersDto;
import com.example.dostavista.dtos.usersDtos.UpdateUserDto;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

public interface UserService {
    void addUser(AddUserDto addUserDto);
    List<AllUsersDto> allUsers();
    Optional<AllUsersDto> getUserById(UUID id);
    void updateUser(UpdateUserDto updateUserDto, UUID id);
    void deleteUser(UUID id);
}