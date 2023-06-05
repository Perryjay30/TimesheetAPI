package com.codingchallenge.timesheetapi.data.dto;

import com.codingchallenge.timesheetapi.data.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;
}
