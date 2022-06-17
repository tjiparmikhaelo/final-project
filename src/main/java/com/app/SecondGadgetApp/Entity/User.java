package com.app.SecondGadgetApp.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "nomor_telpon")
    private String noTlp;

    @Column(name = "alamat")
    private String address;

    @Column(name = "user_email", length = 100)
    private String email;

    @Column(name = "user_password", length = 100)
    private String password;

    @Lob
    private byte[] img;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role;
}
