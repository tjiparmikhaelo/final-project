package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.UserDto;
import com.app.SecondGadgetApp.Entity.User;
import com.app.SecondGadgetApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("user/")
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping("add-user")
    public ResponseEntity<User> add_response(UserDto userDto,@RequestParam("img") MultipartFile file) throws Exception
    {
        return new ResponseEntity<User>(userService.add_user(userDto), HttpStatus.CREATED);
    }

    @GetMapping("display-all")
    public ResponseEntity<List<User>> display_all_response()
    {
        return new ResponseEntity<List<User>>(userService.display_all(), HttpStatus.ACCEPTED);
    }

    @GetMapping("display-by-id/{user_id}")
    public ResponseEntity<User> display_by_id_response(@PathVariable("user_id") Long user_id)
    {
        return new ResponseEntity<User>(userService.display_by_id(user_id), HttpStatus.ACCEPTED);
    }

    @PutMapping("update-user")
    public ResponseEntity<UserDto> update_response(UserDto userDto, @RequestParam("img") MultipartFile file) throws Exception
    {
        if(userService.update_user(userDto))
        {
            return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
        }
        else
        {
            return new ResponseEntity<UserDto>(userDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete-user/{user_id}")
    public ResponseEntity<User> delete_response(@PathVariable ("user_id") Long user_id)
    {
        User user = userService.display_by_id(user_id);
        if(userService.delete_user(user_id))
        {
            return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
        }
        else
        {
            return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }
    }
}
