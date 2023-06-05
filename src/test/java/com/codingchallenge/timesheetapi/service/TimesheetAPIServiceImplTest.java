package com.codingchallenge.timesheetapi.service;

import com.codingchallenge.timesheetapi.data.dto.EmployeeRequest;
import com.codingchallenge.timesheetapi.data.dto.RegisterUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimesheetAPIServiceImplTest {

    @Autowired
    private TimesheetAPIService timesheetAPIService;


    @Test
    void testThatManagerCanBeRegistered() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("chickensoup@gmail.com");
        request.setPassword("Turkey@419");
        request.setUsername("Bento Cucurella");
        String theBody = timesheetAPIService.registerManager(request);
        assertEquals("Registration successful", theBody);
    }


    @Test
    void testThatManagerCanAddEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Boy Spice");
        employeeRequest.setPosition("Musician");
        String userRole = "Admin";
        String addedEmployee = timesheetAPIService.addEmployee(userRole, employeeRequest);
        assertEquals("Employee added successfully", addedEmployee);
    }

    @Test
    void testThatManagerCanAddEmployeeThrowsAnException() {
        String userRole = "USER";
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Joy Stance");
        employeeRequest.setPosition("Software Developer");
        assertThrows(RuntimeException.class, () -> timesheetAPIService.addEmployee(userRole, employeeRequest));
    }

    @Test
    void testThatEmployeeDetailsCanBeUpdatedByTheManager() {
        String adminId = "647da4b274ffa15f55b2ecc8";
        String userRole = "Admin";
        EmployeeRequest editRequest = new EmployeeRequest();
        editRequest.setName("Perry Oluwapelumi");
        editRequest.setPosition("Frontend Engineer");
        String updateEmployee = timesheetAPIService.modifyEmployee(adminId, userRole, editRequest);
        assertEquals("Employee details updated successfully", updateEmployee);
    }

    @Test
    void testThatEmployeeDetailsCanBeUpdatedByTheManagerThrowsException() {
        String adminId = "647ccdfd367d1f0f8dc";
        String userRole = "ADMIN";
        EmployeeRequest editRequest = new EmployeeRequest();
        editRequest.setName("Perry Oluwapelumi");
        editRequest.setPosition("Backend Engineer");
        assertThrows(RuntimeException.class, () -> timesheetAPIService.modifyEmployee
                (adminId, userRole, editRequest));
    }

    @Test
    void testThatPaymentSlipCanBeGenerated() {
        String employeeId = "647cd9de58f21c33edb57712";
        double paymentAmount = 1000.00;
        String paymentSlip = timesheetAPIService.generatePaymentSlip(employeeId, paymentAmount);
        assertEquals("Payment slip generated successfully", paymentSlip);
        File paymentSlipFile = new File("payment_slip.xlsx");
        assertTrue(paymentSlipFile.exists(), "Payment slip file should be generated");
    }

    @Test
    void testThatEmployeeCanClockIn() {
        String employeeId = "647da4b274ffa15f55b2ecc8";
        Boolean clockIn = true;
        String timeIn = timesheetAPIService.clockIn(employeeId, clockIn);
        assertEquals("Employee has clocked In successfully", timeIn);
    }

    @Test
    void testThatEmployeeCanClockOut() {
        String employeeId = "647da4b274ffa15f55b2ecc8";
        Boolean clockOut = true;
        String timeOut = timesheetAPIService.clockOut(employeeId, clockOut);
        assertEquals("Employee has clockedOut successfully", timeOut);
    }

    @Test
    void testThatEmployeeCanStartBreak() {
        String employeeId = "647da4b274ffa15f55b2ecc8";
        Boolean startBreak = true;
        String startBreakMethod = timesheetAPIService.startBreak(employeeId, startBreak);
        assertEquals("Employee has started break", startBreakMethod);
    }

    @Test
    void testThatEmployeeCanEndBreak() {
        String employeeId = "647da4b274ffa15f55b2ecc8";
        Boolean endBreak = true;
        String endBreakMethod = timesheetAPIService.endBreak(employeeId, endBreak);
        assertEquals("Employee has ended break", endBreakMethod);
    }

    @Test
    void testThatEmployeeCanLogin() {
        String employeeId = "647da4b274ffa15f55b2ecc8";
        String login = timesheetAPIService.loggedIn(employeeId);
        assertEquals("Login successful", login);
    }

    @Test
    void testThatEmployeeCanLogOut() {
        String employeeId = "647da4b274ffa15f55b2ecc8";
        String logOut = timesheetAPIService.loggedOut(employeeId);
        assertEquals("Logout successful", logOut);
    }


}