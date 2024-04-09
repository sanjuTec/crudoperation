package com.crudoparation.crudoperation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private int employeeId;
    private String employeeName;
    private String employeeAdress;
    private String employeePnumber;
}
