package com.crudoparation.crudoperation.service;

import com.crudoparation.crudoperation.dto.UserDTO;
import com.crudoparation.crudoperation.entity.User;
import com.crudoparation.crudoperation.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo user_repo;

    @Autowired
    private ModelMapper modelMapper;
    public UserDTO saveUser(UserDTO user_dto){
        user_repo.save(modelMapper.map(user_dto, User.class));
        return  user_dto;
    }

    public List<UserDTO> retriveUsers(){
        List<User> userList =  user_repo.findAll();
        return  modelMapper.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO getUserByUserId(int userId){
        User user = user_repo.getUserByuserId(userId);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO updateUserById(int userId, UserDTO updatedUserDTO){
        User exsitingUser = user_repo.getUserByuserId(userId);
        if(exsitingUser != null){
            exsitingUser.setName(updatedUserDTO.getName());
            exsitingUser.setEmail(updatedUserDTO.getEmail());
            user_repo.save(exsitingUser);
            return modelMapper.map(exsitingUser, UserDTO.class);
        }else{
            return null;
        }
    }

    public UserDTO getUserByUserIdNemail(int userId,String email){
        User user = user_repo.getUserByuserIdNemail(userId,email);
        return  modelMapper.map(user, UserDTO.class);
    }
}
