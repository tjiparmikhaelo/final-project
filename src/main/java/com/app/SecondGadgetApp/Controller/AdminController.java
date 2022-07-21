package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.UsersDTO;
import com.app.SecondGadgetApp.Dto.UsersPassDTO;
import com.app.SecondGadgetApp.Entity.Users;
import com.app.SecondGadgetApp.Service.CloudinaryStorageServices;
import com.app.SecondGadgetApp.Service.UserServices;
import com.app.SecondGadgetApp.ServicesImpl.UserServicesImpl;
import com.app.SecondGadgetApp.Status.ResultStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserServices userService;

    @Autowired
    UserServicesImpl userServicesImpl;

    @Autowired
    private final CloudinaryStorageServices cloudinaryStorageServices;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/verification")
    public ResponseEntity<ResultStatus> getVerifiedUser(Authentication token)
    {
        String username = token.getName();
        return new ResponseEntity<>(userService.getVerifiedUser(username), HttpStatus.ACCEPTED);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<ResultStatus> update_dataAdmin(@PathVariable long userId , UsersDTO usersDto, Authentication authentication) throws Exception
    {
        Users users = new Users();
        users.setFullName(usersDto.getFullName());
        users.setEmail(usersDto.getEmail());
        Map<?,?> uploaImage =(Map<?,?>) cloudinaryStorageServices.upload(usersDto.getImg()).getData();
        users.setImg(uploaImage.get("url").toString());
        return new ResponseEntity<>(userService.update_admin(userId, usersDto, users), HttpStatus.ACCEPTED);
    }

    @PutMapping("/edit/password/{userId}")
    public ResponseEntity<?> update_passwordAdmin(@PathVariable Long userId , UsersPassDTO usersDto) throws Exception
    {
        Users users = new Users();
        users.setPassword(bCryptPasswordEncoder.encode(usersDto.getPassword()));
        return new ResponseEntity<>(userService.update_password(userId, usersDto), HttpStatus.ACCEPTED);
    }
}