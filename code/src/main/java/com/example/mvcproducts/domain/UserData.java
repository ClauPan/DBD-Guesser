package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class UserData implements Serializable {
    private String username;
    private String password;
    private String email;

    public void encode() {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        this.password = bcrypt.encode(this.password);
    }
}