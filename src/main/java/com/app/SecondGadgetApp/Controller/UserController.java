package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.UsersDTO;
import com.app.SecondGadgetApp.Dto.UsersPassDTO;
import com.app.SecondGadgetApp.Entity.Users;
import com.app.SecondGadgetApp.Repository.UsersRepo;
import com.app.SecondGadgetApp.Service.CloudinaryStorageServices;
import com.app.SecondGadgetApp.Service.UserServices;
import com.app.SecondGadgetApp.ServicesImpl.UserServicesImpl;
import com.app.SecondGadgetApp.Status.ResultStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserServices userService;

    @Autowired
    UsersRepo userRepo;

    @Autowired
    UserServicesImpl userServicesImpl;

    @Autowired
    private final CloudinaryStorageServices cloudinaryStorageServices;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/register")
    public ResponseEntity<?> saveUsers(@RequestBody UsersDTO usersDto) {
        return new ResponseEntity<>(userService.saveUsers(usersDto), HttpStatus.CREATED);
    }

    @PostMapping("/register-seller")
    public ResponseEntity<?> saveSeller2(@RequestBody UsersDTO usersDto) {
        return new ResponseEntity<>(userService.saveSeller(usersDto), HttpStatus.CREATED);
    }

    @GetMapping("/display-all")
    public ResponseEntity<ResultStatus> getAllUsers()
    {
        return new ResponseEntity<>(userServicesImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/display-by-id/{user_id}")
    public ResponseEntity<ResultStatus> getUsersById(@PathVariable("user_id") Long user_id)
    {
        return new ResponseEntity<>(userService.getUserById(user_id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<ResultStatus> getUserByName(@PathVariable("username") String username)
    {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.ACCEPTED);
    }

    @GetMapping("/verification")
    public ResponseEntity<ResultStatus> getVerifiedUser(Authentication token)
    {
        String username = token.getName();
        return new ResponseEntity<>(userService.getVerifiedUser(username), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/edit/{userId}"
            , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResultStatus> update_response(@PathVariable long userId , UsersDTO usersDto, Authentication authentication) throws Exception
    {
        if (usersDto.getImg()==null){
            Users users = new Users();
            users.setUsername(usersDto.getUsername());
            users.setFullName(usersDto.getFullName());
            users.setEmail(usersDto.getEmail());
            users.setAddress(usersDto.getAddress());
            users.setPhone(usersDto.getPhone());
            users.getCities();
            return new ResponseEntity<>(userService.update_user(userId, usersDto, users), HttpStatus.ACCEPTED);
        }else {
            Users users = new Users();
            users.setUsername(usersDto.getUsername());
            users.setFullName(usersDto.getFullName());
            users.setEmail(usersDto.getEmail());
            users.setAddress(usersDto.getAddress());
            users.setPhone(usersDto.getPhone());
            Map<?,?> uploaImage =(Map<?,?>) cloudinaryStorageServices.upload(usersDto.getImg()).getData();
            users.setImg(uploaImage.get("url").toString());
            users.getCities();
            return new ResponseEntity<>(userService.update_user(userId, usersDto, users), HttpStatus.ACCEPTED);
        }
    }

    @PutMapping("/edit/password/{userId}")
    public ResponseEntity<?> update_password(@PathVariable Long userId , UsersPassDTO usersDto) throws Exception
    {
        Users users = new Users();
        users.setPassword(bCryptPasswordEncoder.encode(usersDto.getPassword()));
        return new ResponseEntity<>(userService.update_password(userId, usersDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-user/{user_id}")
    public ResponseEntity<ResultStatus> delete_response(@PathVariable ("user_id") Long user_id)
    {
        return new ResponseEntity<>(userService.deleteUser(user_id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/search/ui")
    public ResponseEntity<?>search_usersParamwithId(@RequestParam(name = "fullName") String fullName, @RequestParam(name = "idCity") Long idCity){
        return new ResponseEntity<>(userService.searchUsersWithId(fullName,idCity),HttpStatus.OK);
    }

    @GetMapping("/search/u")
    public ResponseEntity<?>search_usersParam(@RequestParam(name = "fullName") String fullName){
        return new ResponseEntity<>(userService.searchUsers(fullName),HttpStatus.OK);
    }
}
