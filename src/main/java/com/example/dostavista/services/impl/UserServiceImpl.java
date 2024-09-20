package com.example.dostavista.services.impl;

import com.example.dostavista.dtos.usersDtos.AddUserDto;
import com.example.dostavista.dtos.usersDtos.AllUsersDto;
import com.example.dostavista.dtos.usersDtos.UpdateUserDto;
import com.example.dostavista.models.entities.Users;
import com.example.dostavista.repositories.UserRepository;
import com.example.dostavista.services.UserService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void addUser(AddUserDto addUserDto) {
        Users user = new Users();
        user.setName(addUserDto.getName());
        user.setEmail(addUserDto.getEmail());
        user.setNumberPhone(addUserDto.getNumberPhone());
        user.setTimeCreation(LocalDateTime.now());

        userRepository.saveAndFlush(user);
    }

    @Override
    public void updateUser(UpdateUserDto updateUserDto, UUID userId) {
        Users user = userRepository.findById(userId).orElse(null);
        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
        user.setPassport(updateUserDto.getPassport());
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<AllUsersDto> getUserById(UUID id) {
        return userRepository.findById(id).map(user -> new AllUsersDto(user.getId(), user.getEmail(), user.getNumberPhone(), user.getName()));
    }


    @Override
    public List<AllUsersDto> allUsers() {
        return userRepository.findAll().stream().map(user -> new AllUsersDto(user.getId(), user.getEmail(), user.getNumberPhone(), user.getName()))
                .collect(Collectors.toList());
    }
}
