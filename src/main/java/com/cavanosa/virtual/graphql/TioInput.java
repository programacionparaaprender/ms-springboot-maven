package com.cavanosa.virtual.graphql;

import lombok.Data;

@Data
public class TioInput {
    private Long id;
    private String nombre;
    private String email;
    private String password;
}
