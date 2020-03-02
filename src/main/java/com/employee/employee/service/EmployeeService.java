package com.employee.employee.service;

import com.employee.employee.entity.EmployeeDAO;
import com.employee.employee.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    IEmployeeRepository employeeRepository;

    @Override
    public String xmlGregoryCalendartoString(XMLGregorianCalendar date) {
        if(date == null) {
            return null;
        }
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return formatter.format(date.toGregorianCalendar().getTime());
    }

    @Override
    public boolean addEmployee(EmployeeDAO employeeDAO) {
        employeeDAO = employeeRepository.save(employeeDAO);
        return true;
    }
}
