package com.employee.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name="employee")
public class EmployeeDAO {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private Integer id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="document_type")
    private String documentType;

    @Column(name="document_number")
    private String documentNumber;

    @Column(name="role")
    private String role;

    @Column(name="birth_date")
    private String birthDate;

    @Column(name="date_entry_company")
    private String dateEntryCompany;

    @Column(name="salary")
    private Double salary;

}

