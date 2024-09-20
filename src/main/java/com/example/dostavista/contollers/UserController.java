package com.example.dostavista.contollers;

import com.example.dostavista.dtos.usersDtos.AllUsersDto;
import com.example.dostavista.dtos.usersDtos.AddUserDto;
import com.example.dostavista.dtos.usersDtos.UpdateUserDto;
import com.example.dostavista.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
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

    @PostMapping
    @Operation(summary = "Регистрация пользователя", description = "Позволяет зарегистрировать пользователя")
    public void addUser(@RequestBody @Valid AddUserDto addUserDto) {
        userService.addUser(addUserDto);
    }

    @GetMapping
    @Operation(summary = "Список всех пользователей", description = "Позволяет вывести всех пользователей")
    public ResponseEntity<CollectionModel<AllUsersDto>> allUsers() {
        List<AllUsersDto> users = userService.allUsers();
        users.forEach(user -> {
            user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
            user.add(linkTo(methodOn(UserController.class).updateUser(user.getId(), null)).withRel("updateUser"));
            user.add(linkTo(methodOn(UserController.class).deleteUser(user.getId())).withRel("deleteUser"));
        });
        Link allUsersLink = linkTo(methodOn(UserController.class).allUsers())
                .withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(users, allUsersLink));
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
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

    @GetMapping("/{id}")
    @Operation(summary = "Поиск пользвателя по почте", description = "Позволяет найти пользвателя по почте")
    public AllUsersDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(user -> {
                    user.add(linkTo(methodOn(UserController.class).getUserById(id))
                            .withSelfRel());
                    return user;
                })
                .orElse(null);
    }
}