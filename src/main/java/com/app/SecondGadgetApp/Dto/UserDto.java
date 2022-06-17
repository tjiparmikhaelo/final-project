package com.app.SecondGadgetApp.Dto;

import com.app.SecondGadgetApp.Entity.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.util.List;

@Data
public class UserDto
{
    private Long userId;
    private String username;
    private String fullName;
    private String noTlp;
    private String address;
    private String email;
    private String password;
    private List<Role> role;
    private MultipartFile img;
}
