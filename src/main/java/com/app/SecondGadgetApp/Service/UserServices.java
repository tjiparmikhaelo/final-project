package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.UsersDTO;
import com.app.SecondGadgetApp.Entity.Users;
import com.app.SecondGadgetApp.Status.ResultStatus;
import org.springframework.stereotype.Component;

@Component
public interface UserServices
{
    Users findByEmail(String email);
    ResultStatus saveUsers(UsersDTO usersDTO);
    ResultStatus saveSeller(UsersDTO usersDto);
    ResultStatus update_user(Long user_id, Users users);
    ResultStatus getVerifiedUser(String username);
    ResultStatus getUserByUsername(String username);
    ResultStatus getUserById(Long user_id);
    ResultStatus deleteUser(Long user_id);
}
