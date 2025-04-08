package com.cavanosa.virtual.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TioDto {
    @NotBlank
    private String nombre;
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
    
}
