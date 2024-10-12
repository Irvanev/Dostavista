package com.example.dostavista.contollers;

import com.example.dostavista.dtos.usersDtos.AllUsersDto;
import com.example.dostavista.dtos.usersDtos.AddUserDto;
import com.example.dostavista.dtos.usersDtos.UpdateUserDto;
import com.example.dostavista.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @Operation(summary = "Регистрация пользователя", description = "Позволяет зарегистрировать пользователя")
    public ResponseEntity<AllUsersDto> addUser(@RequestBody @Valid AddUserDto addUserDto) {
        AllUsersDto createdUser = userService.addUser(addUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Список всех пользователей", description = "Позволяет вывести всех пользователей")
    public ResponseEntity<CollectionModel<AllUsersDto>> allUsers() {
        List<AllUsersDto> users = userService.allUsers();
        users.forEach(user -> {
            user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel()
                    .withType("GET")
                    .withTitle("Получение пользователя по ID")
            );
        });
        Link allUsersLink = linkTo(methodOn(UserController.class).allUsers())
                .withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(users, allUsersLink));
    }

    @PatchMapping("edit/{id}")
    @Operation(summary = "Обновление персональных данных пользователя", description = "Обновление персональных данных пользователя")
    public ResponseEntity<AllUsersDto> updateUser(@PathVariable UUID id, @RequestBody @Valid UpdateUserDto updateUserDto) {
        userService.updateUser(updateUserDto, id);

        AllUsersDto updatedUser = userService.getUserById(id)
                .map(user -> {
                    user.add(linkTo(methodOn(UserController.class).getUserById(id))
                            .withSelfRel());
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Удаление пользвателя", description = "Удаление пользователя по ID")
    public ResponseEntity<AllUsersDto> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        AllUsersDto updatedUser = userService.getUserById(id)
                .map(user -> {
                    user.add(linkTo(methodOn(UserController.class).getUserById(id))
                            .withSelfRel());
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("find/{id}")
    @Operation(summary = "Поиск пользователя по ID", description = "Позволяет найти пользователя по ID")
    public ResponseEntity<AllUsersDto> getUserById(@PathVariable UUID id) {
        AllUsersDto user = userService.getUserById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.add(linkTo(methodOn(UserController.class).getUserById(id))
                .withSelfRel()
                .withType("GET")
                .withTitle("Получение пользователя по ID")
        );
        user.add(linkTo(methodOn(UserController.class).updateUser(id, new UpdateUserDto()))
                .withRel("update")
                .withType("PUT")
                .withTitle("Обновление пользователя по ID")
        );
        user.add(linkTo(methodOn(UserController.class).deleteUser(id))
                .withRel("delete")
                .withType("DELETE")
                .withTitle("Удаление пользователя по ID")
        );

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
}