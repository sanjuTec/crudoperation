package com.crudoparation.crudoperation.controller;

import com.crudoparation.crudoperation.dto.EmployeeDTO;
import com.crudoparation.crudoperation.dto.ResponseDTO;
import com.crudoparation.crudoperation.entity.Employee;
import com.crudoparation.crudoperation.service.EmployeeService;
import com.crudoparation.crudoperation.util.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/employee")
@Validated // Enables validation for the controller
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){

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
        }else if(response.equals("08")){
            responseDTO.setCode(VarList. RSP_DATA_NULL);
            responseDTO.setMessage("Data can not be null");
            responseDTO.setContent(employeeDTO);
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
        }else{
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("Error in Save Employee");
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

    //update Employee
    @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        try {
            String response = employeeService.updateEmployee(employeeDTO);

            if(response.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if(response.equals("01")){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not a Registered Employee");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error in update Employee");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // fetch all employee data
    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployee(){
        try {
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployee();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(employeeDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchEmployee/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID){
        try {
            EmployeeDTO employeeDTO = employeeService.searchEmployee(empID);
            if(employeeDTO != null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available for this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployee/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID){
        try {
            String response = employeeService.deleteEmployee(empID);
            if(Objects.equals(response, "00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(response);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available for this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
