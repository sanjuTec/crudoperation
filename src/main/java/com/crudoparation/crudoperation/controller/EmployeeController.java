package com.crudoparation.crudoperation.controller;

import com.crudoparation.crudoperation.dto.EmployeeDTO;
import com.crudoparation.crudoperation.dto.ResponseDTO;
import com.crudoparation.crudoperation.service.EmployeeService;
import com.crudoparation.crudoperation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;

@RestController
@RequestMapping(value = "api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseDTO responseDTO;
    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){

    try {
        String response = employeeService.saveEmployee(employeeDTO);
        if(response.equals("00")){
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("sucess");
            responseDTO.setContent(employeeDTO);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }else if(response.equals("06")){
            responseDTO.setCode(VarList.RSP_DUPLICATED);
            responseDTO.setMessage("Employee already registered");
            responseDTO.setContent(employeeDTO);
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
        }else{
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}
