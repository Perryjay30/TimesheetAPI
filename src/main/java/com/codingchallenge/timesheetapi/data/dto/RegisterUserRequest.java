package com.codingchallenge.timesheetapi.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

@Getter
@Setter
public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;

    public String getPassword() {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
