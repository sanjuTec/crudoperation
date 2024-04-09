package com.crudoparation.crudoperation.service;

import com.crudoparation.crudoperation.dto.EmployeeDTO;
import com.crudoparation.crudoperation.entity.Employee;
import com.crudoparation.crudoperation.repo.EmployeeRepo;
import com.crudoparation.crudoperation.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDTO employeeDTO){
        if(employeeRepo.existsById(employeeDTO.getEmployeeId())){
            return VarList.RSP_DUPLICATED;
        }else{
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }

    }
}
