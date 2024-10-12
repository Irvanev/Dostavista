package com.example.dostavista.dtos.usersDtos;

import com.example.dostavista.models.enums.UserRoleEnum;
import org.springframework.hateoas.RepresentationModel;

public class UpdateUserDto extends RepresentationModel<UpdateUserDto> {
    private String email;
    private String numberPhone;
    private String name;
    private String passport;
    private UserRoleEnum role;

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
}
