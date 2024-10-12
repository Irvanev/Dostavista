package com.example.dostavista.dtos.usersDtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.dostavista.models.enums.UserRoleEnum;
import org.springframework.hateoas.RepresentationModel;

public class AllUsersDto extends RepresentationModel<AllUsersDto>{
    private UUID id;
    private String email;
    private String numberPhone;
    private String name;
    private String passport;
    private UserRoleEnum role;
    private LocalDateTime timeCreation;

    public AllUsersDto(UUID id, String email, String numberPhone, String name) {
        this.id = id;
        this.email = email;
        this.numberPhone = numberPhone;
        this.name = name;
    }

    protected AllUsersDto() {};

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public LocalDateTime getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(LocalDateTime timeCreation) {
        this.timeCreation = timeCreation;
    }
}
