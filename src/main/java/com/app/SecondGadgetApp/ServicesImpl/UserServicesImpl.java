package com.app.SecondGadgetApp.ServicesImpl;

import com.app.SecondGadgetApp.Dto.UsersDTO;
import com.app.SecondGadgetApp.Entity.Users;
import com.app.SecondGadgetApp.Repository.RoleRepo;
import com.app.SecondGadgetApp.Repository.UsersRepo;
import com.app.SecondGadgetApp.Repository.UsersRoleRepo;
import com.app.SecondGadgetApp.Service.UserServices;
import com.app.SecondGadgetApp.Status.ErrorDataResult;
import com.app.SecondGadgetApp.Status.ResultStatus;
import com.app.SecondGadgetApp.Status.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServicesImpl implements UserServices, UserDetailsService
{
    @Autowired
    private final UsersRepo usersRepo;

    @Autowired
    private final RoleRepo roleRepo;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UsersRoleRepo userRoleRepo;

    @Override
    public Users findByEmail(String email) {
        return  usersRepo.findByEmail(email);
    }

    @Override
    public ResultStatus saveUsers(UsersDTO usersDto) {
        Users saveUsers = new Users();
        saveUsers.setUsername(usersDto.getUsername());
        saveUsers.setFullName(usersDto.getFullName());
        saveUsers.setEmail(usersDto.getEmail());
        saveUsers.setPassword(bCryptPasswordEncoder.encode(usersDto.getPassword()));
        Users emailVal = usersRepo.findByEmail(usersDto.getEmail());
        Users nameVal = usersRepo.findByUsername(usersDto.getUsername());
        if (emailVal != null){
            return new ErrorDataResult("Email sudah digunakan!");
        }else if(nameVal != null){
            return new ErrorDataResult("Username sudah digunakan!");
        }else{
            Users saved = usersRepo.save(saveUsers);
            userRoleRepo.nativeInsert(saved.getUserId(),usersDto.getRoleId());
            return new SuccessDataResult(saved, "Pendaftaran Akun Berhasil");
        }
    }

    @Override
    public ResultStatus saveSeller(UsersDTO usersDto) {
        Users userExist= usersRepo.findByEmail(usersDto.getEmail());
        userExist.setDescription(usersDto.getDescription());
        userRoleRepo.nativeInsert(userExist.getUserId(),usersDto.getRoleId());
        return new SuccessDataResult(userExist, "Pendaftaran Penjual Berhasil!");
    }

    public ResultStatus getAllUsers() {
        List<Users> users = usersRepo.findAll();
        return new SuccessDataResult<>(users,"Berhasil mendapat semua data user");
    }

    @Override
    public ResultStatus getUserById(Long user_id) {
        Users users = usersRepo.findByUserId(user_id);
        return new SuccessDataResult<>(users,"Berhasil mendapat data user berdasarkan Id");
    }

    @Override
    public ResultStatus update_user(Long user_id, Users users) {
        Users usersRepos = this.usersRepo.findByUserId(user_id);
        usersRepos.setUsername(users.getUsername());
        usersRepos.setFullName(users.getFullName());
        usersRepos.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        usersRepos.setAddress(users.getAddress());
        usersRepos.setEmail(users.getEmail());
        usersRepos.setPhone(users.getPhone());
        usersRepos.setImg(users.getImg());
        usersRepos.setCity(users.getCity());
        Users saved = usersRepo.save(usersRepos);
        return new SuccessDataResult(saved, "Berhasil update user!");
    }

    @Override
    public ResultStatus getVerifiedUser(String username) {
        Users users = usersRepo.findByUsername(username);
        return new SuccessDataResult(users,"Akun Berhasil Diverifikasi!");
    }

    @Override
    public ResultStatus getUserByUsername(String username) {
        Users users = usersRepo.findByUsername((username));
        return new SuccessDataResult(users, "Detail Akun Berhasil Ditampilkan!");
    }

    @Override
    public ResultStatus deleteUser(Long user_id) {
        Users users = usersRepo.findByUserId(user_id);
        if (users != null){
            usersRepo.deleteById(user_id);
        }
        return new ResultStatus(200,"Berhasil menghapus user!");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepo.findByEmail(email);
        log.info("username users :" + email);
        if(users != null){
            log.info("Users found in the database : {}", email);
        }else{
            log.error("Users not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        users.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), authorities);
    }
}