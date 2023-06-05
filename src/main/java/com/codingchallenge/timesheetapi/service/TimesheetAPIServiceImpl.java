package com.codingchallenge.timesheetapi.service;

import com.codingchallenge.timesheetapi.data.dto.EmployeeRequest;
import com.codingchallenge.timesheetapi.data.dto.RegisterUserRequest;
import com.codingchallenge.timesheetapi.data.model.Manager;
import com.codingchallenge.timesheetapi.data.model.Employee;
import com.codingchallenge.timesheetapi.data.repository.ManagerRepository;
import com.codingchallenge.timesheetapi.data.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class TimesheetAPIServiceImpl implements  TimesheetAPIService {

    private final ManagerRepository managerRepository;

    private final EmployeeRepository employeeRepository;
    @Override
    public String registerManager(RegisterUserRequest request) {
        Manager newManager = new Manager();
        newManager.setEmail(request.getEmail());
        newManager.setPassword(request.getPassword());
        newManager.setUsername(request.getUsername());
        newManager.setRole("Admin");
        managerRepository.save(newManager);
        return "Registration successful";
    }

    @Override
    public String addEmployee(String userRole, EmployeeRequest addRequest) {
            findAdmin(userRole);
            Employee employee = Employee.builder()
                    .name(addRequest.getName())
                    .position(addRequest.getPosition())
                    .build();
            employeeRepository.save(employee);
            return "Employee added successfully";
    }


    @Override
    public String modifyEmployee(String employeeId, String userRole, EmployeeRequest editRequest) {
        findAdmin(userRole);
        Employee existingEmployee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new RuntimeException("Employee not available"));
        existingEmployee.setName(editRequest.getName() != null && !editRequest.getName()
                .equals("") ? editRequest.getName() : existingEmployee.getName());
        existingEmployee.setPosition(editRequest.getPosition() != null && !editRequest.getPosition()
                .equals("") ? editRequest.getPosition() : existingEmployee.getPosition());
        employeeRepository.save(existingEmployee);
        return "Employee details updated successfully";
    }


    @Override
    public String generatePaymentSlip(String employeeId, double paymentAmount) {
        Employee employee = getEmployee(employeeId);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = getRows(paymentAmount, employee, workbook);
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream("payment_slip.xlsx")) {
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to generate payment slip";
        }
        return "Payment slip generated successfully";
    }


    @Override
    public String clockIn(String employeeId, Boolean clockIn) {
        Employee employee = getEmployee(employeeId);
        if(employee.getClockIn())
            return "Employee has already clockedIn";
        else {
            employee.setClockIn(clockIn);
            employee.setClockInTime(LocalDateTime.now());
            employee.setClockOut(false);
            employee.setClockOutTime(null);
            employeeRepository.save(employee);
            return "Employee has clocked In successfully";
        }
    }

    @Override
    public String clockOut(String employeeId, Boolean clockOut) {
        Employee clockedInEmployee = getEmployee(employeeId);
        if(clockedInEmployee.getClockOut())
            return "Employee has clocked out";
        else {
            clockedInEmployee.setClockOut(clockOut);
            clockedInEmployee.setClockOutTime(LocalDateTime.now());
            clockedInEmployee.setClockIn(false);
            clockedInEmployee.setClockInTime(null);
            employeeRepository.save(clockedInEmployee);
            return "Employee has clockedOut successfully";
        }
    }

    @Override
    public String startBreak(String employeeId, Boolean startBreak) {
        Employee existingEmployee = getEmployee(employeeId);
        if(existingEmployee.getStartBreak())
            return "Employee with ID:" + existingEmployee.getId() + "already started a break";
        else {
            existingEmployee.setStartBreak(startBreak);
            existingEmployee.setStartBreakTime(LocalDateTime.now());
            existingEmployee.setEndBreak(false);
            existingEmployee.setEndBreakTime(null);
            employeeRepository.save(existingEmployee);
            return "Employee has started break";
        }
    }

    @Override
    public String endBreak(String employeeId, Boolean endBreak) {
        Employee existingEmployee = getEmployee(employeeId);
        if(existingEmployee.getEndBreak())
            return "Employee with ID:" + existingEmployee.getId() + "already ended break";
        else {
            existingEmployee.setEndBreak(endBreak);
            existingEmployee.setEndBreakTime(LocalDateTime.now());
            existingEmployee.setStartBreak(false);
            existingEmployee.setStartBreakTime(null);
            employeeRepository.save(existingEmployee);
            return "Employee has ended break";
        }
    }

    @Override
    public String loggedIn(String employeeId) {
        Employee loggedInEmployee = getEmployee(employeeId);
        if(loggedInEmployee.getLoggedIn())
            return "Employee with ID:" + loggedInEmployee.getId() + "already logged in";
        else {
            loggedInEmployee.setLoggedIn(true);
            loggedInEmployee.setLoggedInTime(LocalDateTime.now());
            loggedInEmployee.setLoggedOut(false);
            loggedInEmployee.setLoggedOutTime(null);
            employeeRepository.save(loggedInEmployee);
            return "Login successful";
        }
    }

    @Override
    public String loggedOut(String employeeId) {
        Employee loggedOutEmployee = getEmployee(employeeId);
        if(loggedOutEmployee.getLoggedOut())
            return "Employee with ID:" + loggedOutEmployee.getId() + "already logged out";
        else {
            loggedOutEmployee.setLoggedOut(true);
            loggedOutEmployee.setLoggedOutTime(LocalDateTime.now());
            loggedOutEmployee.setLoggedIn(false);
            loggedOutEmployee.setLoggedInTime(null);
            employeeRepository.save(loggedOutEmployee);
            return "Logout successful";
        }
    }

    private Employee getEmployee(String employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    private Sheet getRows(double paymentAmount, Employee employee, Workbook workbook) {
        Sheet sheet = workbook.createSheet("Payment Slip");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Employee ID");
        headerRow.createCell(1).setCellValue("Employee Name");
        headerRow.createCell(2).setCellValue("Payment Amount");
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(employee.getId());
        dataRow.createCell(1).setCellValue(employee.getName());
        dataRow.createCell(2).setCellValue(paymentAmount);
        return sheet;
    }


    private void findAdmin(String userRole) {
        managerRepository.findUserByRole(userRole)
                .orElseThrow(() -> new RuntimeException("Admin not found, You're probably not " +
                        "inputting the word properly...It should be - 'Admin'"));
    }



}
