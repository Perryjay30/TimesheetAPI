package com.codingchallenge.timesheetapi.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@RequiredArgsConstructor
public class Manager {
    @Id
    private String userId;
    private String username;
    private String email;
    private String password;
    private String role;
}
