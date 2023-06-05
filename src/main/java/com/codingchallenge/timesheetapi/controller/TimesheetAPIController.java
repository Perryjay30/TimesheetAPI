package com.codingchallenge.timesheetapi.controller;

import com.codingchallenge.timesheetapi.data.dto.EmployeeRequest;
import com.codingchallenge.timesheetapi.data.dto.RegisterUserRequest;
import com.codingchallenge.timesheetapi.service.TimesheetAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timesheetAPI")
@RequiredArgsConstructor
public class TimesheetAPIController {

    private final TimesheetAPIService timesheetAPIService;

    @PostMapping("/registerManager")
    public ResponseEntity<String> registerManager(@RequestBody RegisterUserRequest request) {
        String register = timesheetAPIService.registerManager(request);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/addEmployee/{userRole}")
    public ResponseEntity<String> addEmployee(@PathVariable String userRole, @RequestBody EmployeeRequest employeeRequest) {
        String includeEmployee = timesheetAPIService.addEmployee(userRole, employeeRequest);
        return ResponseEntity.ok(includeEmployee);
    }

    @PostMapping("/modifyEmployee/{employeeId}/{userRole}")
    public ResponseEntity<String> modifyEmployee(@PathVariable String employeeId, @PathVariable String userRole, @RequestBody EmployeeRequest editRequest) {
        String updateEmployee = timesheetAPIService.modifyEmployee(employeeId, userRole, editRequest);
        return ResponseEntity.ok(updateEmployee);
    }

    @PostMapping("/generatePayment/{employeeId}/{paymentAmount}")
    public ResponseEntity<String> generatePaymentSlip(@PathVariable String employeeId, @PathVariable double paymentAmount) {
        String generatePayment = timesheetAPIService.generatePaymentSlip(employeeId, paymentAmount);
        return ResponseEntity.ok(generatePayment);
    }

    @PostMapping("/clockIn/{employeeId}/{clockIn}")
    public ResponseEntity<String> clockIn(@PathVariable String employeeId, @PathVariable Boolean clockIn) {
        String clockInEmployee = timesheetAPIService.clockIn(employeeId, clockIn);
        return ResponseEntity.ok(clockInEmployee);
    }

    @PostMapping("/clockOut/{employeeId}/{clockOut}")
    public ResponseEntity<String> clockOut(@PathVariable String employeeId, @PathVariable Boolean clockOut) {
        String clockOutEmployee = timesheetAPIService.clockOut(employeeId, clockOut);
        return ResponseEntity.ok(clockOutEmployee);
    }

    @PostMapping("/startBreak/{employeeId}/{startBreak}")
    public ResponseEntity<String> startBreak(@PathVariable String employeeId, @PathVariable Boolean startBreak) {
        String breakTime = timesheetAPIService.startBreak(employeeId, startBreak);
        return ResponseEntity.ok(breakTime);
    }

    @PostMapping("/endBreak/{employeeId}/{endBreak}")
    public ResponseEntity<String> endBreak(@PathVariable String employeeId, @PathVariable Boolean endBreak) {
        String breakOver = timesheetAPIService.endBreak(employeeId, endBreak);
        return ResponseEntity.ok(breakOver);
    }

    @PostMapping("/loggedIn/{employeeId}")
    public ResponseEntity<String> loggedIn(@PathVariable String employeeId) {
        String login = timesheetAPIService.loggedIn(employeeId);
        return ResponseEntity.ok(login);
    }

    @PostMapping("/loggedOut/{employeeId}")
    public ResponseEntity<String> loggedOut(@PathVariable String employeeId) {
        String logout = timesheetAPIService.loggedOut(employeeId);
        return ResponseEntity.ok(logout);
    }






}
