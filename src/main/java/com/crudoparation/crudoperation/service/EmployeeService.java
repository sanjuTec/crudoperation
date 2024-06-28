package com.crudoparation.crudoperation.service;

import com.crudoparation.crudoperation.dto.EmployeeDTO;
import com.crudoparation.crudoperation.entity.Employee;
import com.crudoparation.crudoperation.repo.EmployeeRepo;
import com.crudoparation.crudoperation.util.VarList;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;

    private boolean isValidEmployeeData(EmployeeDTO employeeDTO) {
        return employeeDTO.getEmployeeName() != null &&
                !employeeDTO.getEmployeeName().isEmpty() &&
                employeeDTO.getEmployeeAdress()!= null  &&
                !employeeDTO.getEmployeeAdress().isEmpty() &&
                employeeDTO.getEmployeePnumber()!= null &&
                !employeeDTO.getEmployeePnumber().isEmpty();
    }
    public String saveEmployee(@Valid  EmployeeDTO employeeDTO){
        if(employeeRepo.existsById(employeeDTO.getEmployeeId())){
            return VarList.RSP_DUPLICATED;
        }else{
            try {
                if(isValidEmployeeData(employeeDTO)){
                    employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
                    return VarList.RSP_SUCCESS;
                }else{
                    return  VarList. RSP_DATA_NULL;
                }
            }catch (Exception e){
                e.printStackTrace();
                return VarList.RSP_ERROR;
            }


        }

    }

    public String updateEmployee(EmployeeDTO employeeDTO){
        if(employeeRepo.existsById(employeeDTO.getEmployeeId())){
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employeeList = employeeRepo.findAll();
        return  modelMapper.map(employeeList, new TypeToken<ArrayList<EmployeeDTO>>(){

        }.getType());
    }

    public EmployeeDTO searchEmployee(int empID){
        if(employeeRepo.existsById(empID)){
            Employee employee = employeeRepo.findById(empID).orElse(null);
            return modelMapper.map(employee, EmployeeDTO.class);
        }else{
            return null;
        }
    }

    public String deleteEmployee(int empID){
        if(employeeRepo.existsById(empID)){
            employeeRepo.deleteById(empID);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }


}
