package com.app.SecondGadgetApp.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;
}
