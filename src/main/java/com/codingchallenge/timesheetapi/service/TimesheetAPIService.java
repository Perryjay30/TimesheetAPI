package com.codingchallenge.timesheetapi.service;

import com.codingchallenge.timesheetapi.data.dto.EmployeeRequest;
import com.codingchallenge.timesheetapi.data.dto.RegisterUserRequest;

public interface TimesheetAPIService {

    String registerManager(RegisterUserRequest request);

    String addEmployee(String userRole, EmployeeRequest addRequest);

    String modifyEmployee(String employeeId, String userRole, EmployeeRequest editRequest);

    String generatePaymentSlip(String employeeId, double paymentAmount);

    String clockIn(String employeeId, Boolean clockIn);

    String clockOut(String employeeId, Boolean clockOut);

    String startBreak(String employeeId, Boolean startBreak);

    String endBreak(String employeeId, Boolean endBreak);

    String loggedIn(String employeeId);

    String loggedOut(String employeeId);

}
