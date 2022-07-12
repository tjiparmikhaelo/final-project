package com.app.SecondGadgetApp.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
@Setter
@Getter
public class UsersRole
{
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private int roleId;
}
