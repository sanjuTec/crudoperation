package com.crudoparation.crudoperation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private int employeeId;

    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @NotBlank(message = "Employee address is required")
    private String employeeAdress;

    @NotBlank(message = "Employee phone number is required")
    @Pattern(regexp = "\\+?[0-9]+", message = "Phone number must contain only digits and may start with '+'")
    private String employeePnumber;
}
