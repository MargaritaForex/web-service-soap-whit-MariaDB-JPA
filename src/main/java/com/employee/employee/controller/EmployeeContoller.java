package com.employee.employee.controller;

import com.employee.employee.entity.EmployeeDAO;
import com.employee.employee.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.employee.Employee;
import soap.employee.EmployeeRequest;
import soap.employee.EmployeeResponse;
import soap.employee.ServiceStatus;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Endpoint
public class EmployeeContoller {
    private static final String NAMESPACE_URI = "employee.soap";

    @Autowired
    IEmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "EmployeeRequest")
    @ResponsePayload
    public EmployeeResponse addEmployee(@RequestPayload EmployeeRequest requestEmployee){
        EmployeeResponse responseEmployee = new EmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.setFullName(requestEmployee.getFullName());
        employeeDAO.setLastName(requestEmployee.getLastName());
        employeeDAO.setSalary(requestEmployee.getSalary());
        employeeDAO.setRole(requestEmployee.getRole());
        employeeDAO.setDocumentType(requestEmployee.getDocumentType());
        employeeDAO.setDocumentNumber(requestEmployee.getDocumentNumber());
        employeeDAO.setBirthDate(employeeService.xmlGregoryCalendartoString(requestEmployee.getBirthDate()));
        employeeDAO.setDateEntryCompany(employeeService.xmlGregoryCalendartoString(requestEmployee.getDateEntryCompany()));
        boolean flag = employeeService.addEmployee(employeeDAO);
        if (flag == false) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            responseEmployee.setServiceStatus(serviceStatus);
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDAO, employee);
            employee.setBirthDate(requestEmployee.getBirthDate());
            employee.setDateEntryCompany(requestEmployee.getDateEntryCompany());
            responseEmployee.setEmployee(employee);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            responseEmployee.setServiceStatus(serviceStatus);
        }
        return responseEmployee;
    }
}
