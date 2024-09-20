package com.example.dostavista.dtos.usersDtos;

import org.springframework.hateoas.RepresentationModel;

public class UpdateUserDto extends RepresentationModel<UpdateUserDto> {
    private String name;
    private String passport;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
