package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.UserDto;
import com.app.SecondGadgetApp.Entity.User;
import com.app.SecondGadgetApp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    UserRepo userRepo;

    public User add_user(UserDto userDto)
    {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepo.save(user);

        return user;
    }

    public List<User> display_all()
    {
        return userRepo.findAll();
    }

    public User display_by_id(Long user_id)
    {
        return userRepo.findByUserId(user_id);
    }

    public Boolean update_user(UserDto userDto)
    {
        if(userRepo.existsById(userDto.getUserId()))
        {
            User user = new User();
            user.setUserId(userDto.getUserId());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            userRepo.save(user);
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean delete_user(Long user_id)
    {
        if(userRepo.existsById(user_id))
        {
            userRepo.deleteById(user_id);
            return true;
        }
        else
        {
            return false;
        }
    }
}
