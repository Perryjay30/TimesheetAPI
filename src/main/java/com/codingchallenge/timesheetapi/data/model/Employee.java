package com.codingchallenge.timesheetapi.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {
    @Id
    private String id;
    private String name;
    private String position;
    private Boolean clockIn = false;
    private LocalDateTime clockInTime;
    private Boolean clockOut = false;
    private LocalDateTime clockOutTime;
    private Boolean startBreak = false;
    private LocalDateTime startBreakTime;
    private Boolean endBreak = false;
    private LocalDateTime endBreakTime;
    private Boolean loggedIn = false;
    private LocalDateTime loggedInTime;
    private Boolean loggedOut = false;
    private LocalDateTime loggedOutTime;
}
