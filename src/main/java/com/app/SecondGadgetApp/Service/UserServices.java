package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.UsersDTO;
import com.app.SecondGadgetApp.Dto.UsersPassDTO;
import com.app.SecondGadgetApp.Entity.Users;
import com.app.SecondGadgetApp.Status.ResultStatus;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public interface UserServices {
    Users findByEmail(String email);
    ResultStatus saveUsers(UsersDTO usersDTO);
    ResultStatus saveSeller(UsersDTO usersDto);
    ResultStatus update_user(Long user_id, UsersDTO usersDto, Users users);
    ResultStatus update_admin(Long user_id, UsersDTO usersDto, Users users);
    ResultStatus getVerifiedUser(String username);
    ResultStatus getUserByUsername(String username);
    ResultStatus getUserById(Long user_id);
    ResultStatus deleteUser(Long user_id);
    ResultStatus update_password(Long user_id, UsersPassDTO usersPassDto);
    ResultStatus searchUsers(String fullName, Long id_city);
}
