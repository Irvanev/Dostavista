package com.example.dostavista.dtos.usersDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import com.example.dostavista.utils.UniqueEmail;

import jakarta.validation.constraints.NotEmpty;

@Schema(description = "Сущность пользователя")
public class AddUserDto {
    @UniqueEmail
    private String email;
    private String numberPhone;
    private String name;

    @Schema(description = "Почта", example = "test@mail.ru")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Schema(description = "Номер телефона", example = "87777777777")
    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @NotEmpty(message = "Name cannot be empty")
    @Length(min = 2, message = "Name must be more than 2 characters!")
    @Schema(description = "ФИО", example = "Иванов Иван Иванович")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
