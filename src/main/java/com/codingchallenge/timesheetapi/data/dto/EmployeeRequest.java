package com.codingchallenge.timesheetapi.data.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeRequest {
    private String name;
    private String position;
}
