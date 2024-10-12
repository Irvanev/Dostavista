package com.example.dostavista.dataFethers;

import com.example.dostavista.dtos.usersDtos.AddUserDto;
import com.example.dostavista.dtos.usersDtos.AllUsersDto;
import com.example.dostavista.dtos.usersDtos.UpdateUserDto;
import com.example.dostavista.models.entities.Users;
import com.example.dostavista.models.enums.UserRoleEnum;
import com.example.dostavista.repositories.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@DgsComponent
public class UserDataFetcher {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserDataFetcher(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @DgsQuery
    public Optional<AllUsersDto> findUserById(@InputArgument UUID id) {
        return userRepository.findById(id)
                .map(user -> new AllUsersDto(user.getId(), user.getEmail(), user.getNumberPhone(), user.getName()));
    }

    @DgsQuery
    public List<AllUsersDto> getUsers() {
        return userRepository.findAll().stream().map(users -> modelMapper.map(users, AllUsersDto.class))
                .collect(Collectors.toList());
    }

    @DgsMutation
    public AllUsersDto updateUser(@InputArgument(name = "user") UpdateUserDto updateUserDto, UUID id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

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

    @DgsMutation
    public boolean deleteUser(@InputArgument UUID id) {
        userRepository.deleteById(id);
        return true;
    }

    @DgsMutation
    public AllUsersDto addUser(@InputArgument(name = "user") AddUserDto addUserDto) {
        Users user = modelMapper.map(addUserDto, Users.class);

        user.setRole(UserRoleEnum.CUSTOMER);
        user.setTimeCreation(LocalDateTime.now());

        return modelMapper.map(userRepository.saveAndFlush(user), AllUsersDto.class);
    }
}
