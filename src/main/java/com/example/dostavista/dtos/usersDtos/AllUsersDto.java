package com.example.dostavista.dtos.usersDtos;

import java.util.UUID;
import org.springframework.hateoas.RepresentationModel;

public class AllUsersDto extends RepresentationModel<AllUsersDto>{
    private UUID id;
    private String email;
    private String numberPhone;
    private String name;

    public AllUsersDto(UUID id, String email, String numberPhone, String name) {
        this.id = id;
        this.email = email;
        this.numberPhone = numberPhone;
        this.name = name;
    }

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
}
