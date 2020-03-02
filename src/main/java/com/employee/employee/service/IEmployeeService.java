package com.employee.employee.service;

import com.employee.employee.entity.EmployeeDAO;

import javax.xml.datatype.XMLGregorianCalendar;

public interface IEmployeeService {

    String xmlGregoryCalendartoString(XMLGregorianCalendar date);

    boolean addEmployee(EmployeeDAO employeeDAO);
}
