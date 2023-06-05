package com.codingchallenge.timesheetapi;

import com.codingchallenge.timesheetapi.data.dto.EmployeeRequest;
import com.codingchallenge.timesheetapi.data.dto.RegisterUserRequest;
import com.codingchallenge.timesheetapi.service.TimesheetAPIServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TimesheetAPIServiceImplMockTest {

    private TimesheetAPIServiceImpl timesheetService;

    @BeforeEach
    public void setup() {
        timesheetService = mock(TimesheetAPIServiceImpl.class);
    }

    @Test
    void testRegisterManager_Success() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("Amber");
        request.setPassword("Washington");
        request.setEmail("ambelli@gmail.com");
        String expectedMessage = "Registration successful";
        when(timesheetService.registerManager(request)).thenReturn(expectedMessage);
        String result = timesheetService.registerManager(request);
        assertEquals(expectedMessage, result);
        verify(timesheetService, times(1)).registerManager(request);
    }

    @Test
    void testAddEmployee_Success() {
        String userRole = "Admin";
        EmployeeRequest addRequest = new EmployeeRequest();
        addRequest.setName("Jonathan Damian");
        addRequest.setPosition("Developer");
        String expectedMessage = "Employee added successfully";
        when(timesheetService.addEmployee(userRole, addRequest)).thenReturn(expectedMessage);
        String result = timesheetService.addEmployee(userRole, addRequest);
        assertEquals(expectedMessage, result);
        verify(timesheetService, times(1)).addEmployee(userRole, addRequest);
    }

    @Test
    void testThatEmployeeDetailsCanBeModified() {
        EmployeeRequest editRequest = new EmployeeRequest();
        String employeeId = "54436321ed";
        String userRole = "Admin";
        editRequest.setName("Henderson");
        editRequest.setPosition("Quality Tester");
        String actualMessage = "Employee details updated successfully";
        when(timesheetService.modifyEmployee(employeeId, userRole, editRequest)).thenReturn(actualMessage);
        String expectedResult = timesheetService.modifyEmployee(employeeId, userRole, editRequest);
        assertEquals(actualMessage, expectedResult);
        verify(timesheetService, times(1)).modifyEmployee(employeeId, userRole, editRequest);
    }

    @Test
    void testThatPaymentSlipCanBeGenerated() {
        String employeeId = "rgderteu355dfvjdw2e8fbs";
        double paymentAmount = 10000.00;
        String actualMessage = "Payment slip generated successfully";
        when(timesheetService.generatePaymentSlip(employeeId, paymentAmount)).thenReturn(actualMessage);
        String expectedResult = timesheetService.generatePaymentSlip(employeeId, paymentAmount);
        assertEquals(actualMessage, expectedResult);
        verify(timesheetService, times(1)).generatePaymentSlip(employeeId, paymentAmount);
    }

    @Test
    void testThatEmployeeCanClockIn() {
        String employeeId = "3ewrgtw54t9ege7fjujvds";
        Boolean clockIn = true;
        String expectedMessage = "Employee has clocked In successfully";
        when(timesheetService.clockIn(employeeId, clockIn)).thenReturn(expectedMessage);
        String actualResult = timesheetService.clockIn(employeeId, clockIn);
        assertEquals(expectedMessage, actualResult);
        verify(timesheetService, times(1)).clockIn(employeeId, clockIn);
    }

    @Test
    void testThatEmployeeCanClockOut() {
        String employeeId = "3ewrgtw54t9ege7fjujvds";
        Boolean clockOut = true;
        String expectedMessage = "Employee has clocked out successfully";
        when(timesheetService.clockOut(employeeId, clockOut)).thenReturn(expectedMessage);
        String actualResult = timesheetService.clockOut(employeeId, clockOut);
        assertEquals(expectedMessage, actualResult);
        verify(timesheetService, times(1)).clockOut(employeeId, clockOut);
    }

    @Test
    void testThatEmployeeCanStartBreak() {
        String employeeId = "3ewrgtw54t9ege7fjujvds";
        Boolean startBreak = true;
        String expectedMessage = "Employee has started break";
        when(timesheetService.startBreak(employeeId, startBreak)).thenReturn(expectedMessage);
        String actualResult = timesheetService.startBreak(employeeId, startBreak);
        assertEquals(expectedMessage, actualResult);
        verify(timesheetService, times(1)).startBreak(employeeId, startBreak);
    }

    @Test
    void testThatEmployeeCanEndBreak() {
        String employeeId = "3ewrgtw54t9ege7fjujvds";
        Boolean endBreak = true;
        String expectedMessage = "Employee has ended break";
        when(timesheetService.endBreak(employeeId, endBreak)).thenReturn(expectedMessage);
        String actualResult = timesheetService.endBreak(employeeId, endBreak);
        assertEquals(expectedMessage, actualResult);
        verify(timesheetService, times(1)).endBreak(employeeId, endBreak);
    }

    @Test
    void testThatEmployeeCanLogin() {
        String employeeId = "3ewrgtw54t9ege7fjujvds";
        String expectedMessage = "Login successful";
        when(timesheetService.loggedIn(employeeId)).thenReturn(expectedMessage);
        String actualResult = timesheetService.loggedIn(employeeId);
        assertEquals(expectedMessage, actualResult);
        verify(timesheetService, times(1)).loggedIn(employeeId);
    }

    @Test
    void testThatEmployeeCanLogout() {
        String employeeId = "3ewrgtw54t9ege7fjujvds";
        String expectedMessage = "Logout successful";
        when(timesheetService.loggedOut(employeeId)).thenReturn(expectedMessage);
        String actualResult = timesheetService.loggedOut(employeeId);
        assertEquals(expectedMessage, actualResult);
        verify(timesheetService, times(1)).loggedOut(employeeId);
    }





}
