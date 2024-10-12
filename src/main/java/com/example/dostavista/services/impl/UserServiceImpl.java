package com.example.dostavista.services.impl;

import com.example.dostavista.dtos.usersDtos.AddUserDto;
import com.example.dostavista.dtos.usersDtos.AllUsersDto;
import com.example.dostavista.dtos.usersDtos.UpdateUserDto;
import com.example.dostavista.models.entities.Users;
import com.example.dostavista.models.enums.UserRoleEnum;
import com.example.dostavista.repositories.UserRepository;
import com.example.dostavista.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AllUsersDto addUser(AddUserDto addUserDto) {
        Users user = modelMapper.map(addUserDto, Users.class);

        user.setRole(UserRoleEnum.CUSTOMER);
        user.setTimeCreation(LocalDateTime.now());

        return modelMapper.map(userRepository.saveAndFlush(user), AllUsersDto.class);
    }

    @Override
    public AllUsersDto updateUser(UpdateUserDto updateUserDto, UUID userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (updateUserDto.getEmail() != null) {
            user.setEmail(updateUserDto.getEmail());
        }
        if (updateUserDto.getNumberPhone() != null) {
            user.setNumberPhone(updateUserDto.getNumberPhone());
        }
        if (updateUserDto.getName() != null) {
            user.setName(updateUserDto.getName());
        }
        if (updateUserDto.getPassport() != null) {
            user.setPassport(updateUserDto.getPassport());
        }

        return modelMapper.map(userRepository.saveAndFlush(user), AllUsersDto.class);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<AllUsersDto> getUserById(UUID id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, AllUsersDto.class));
    }


    @Override
    public List<AllUsersDto> allUsers() {
        return userRepository.findAll().stream().map(users -> modelMapper.map(users, AllUsersDto.class))
                .collect(Collectors.toList());
    }
}
