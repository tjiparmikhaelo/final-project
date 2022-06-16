package com.app.SecondGadgetApp.Dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto
{
    private long userId;
    private String name;
    private String email;
    private String password;
}
