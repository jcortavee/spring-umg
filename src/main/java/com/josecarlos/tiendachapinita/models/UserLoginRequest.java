package com.josecarlos.tiendachapinita.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserLoginRequest {

    private String username;
    private String password;

}
