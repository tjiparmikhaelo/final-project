package com.app.SecondGadgetApp.ServicesImpl;

import com.app.SecondGadgetApp.Dto.UsersDTO;
import com.app.SecondGadgetApp.Dto.UsersPassDTO;
import com.app.SecondGadgetApp.Entity.City;
import com.app.SecondGadgetApp.Entity.Users;
import com.app.SecondGadgetApp.Repository.CityRepo;
import com.app.SecondGadgetApp.Repository.UsersRepo;
import com.app.SecondGadgetApp.Repository.UsersRoleRepo;
import com.app.SecondGadgetApp.Service.UserServices;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServicesImpl implements UserServices, UserDetailsService {
    @Autowired
    private final UsersRepo userRepo;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UsersRoleRepo userRoleRepo;

    @Autowired
    CityRepo cityRepo;

    @Override
    public Users findByEmail(String email) {
        return  userRepo.findByEmail(email);
    }

    @Override
    public ResultStatus saveUsers(UsersDTO usersDto) {
        Users saveUsers = new Users();
        saveUsers.setUsername(usersDto.getUsername());
        saveUsers.setFullName(usersDto.getFullName());
        saveUsers.setEmail(usersDto.getEmail());
        saveUsers.setPassword(bCryptPasswordEncoder.encode(usersDto.getPassword()));
        Users emailVal = userRepo.findByEmail(usersDto.getEmail());
        Users nameVal = userRepo.findByUsername(usersDto.getUsername());
        if (emailVal != null){
            return new ResultStatus(411,"Email sudah digunakan!");
        }else if(nameVal != null){
            return new ResultStatus(412, "Username sudah digunakan!");
        }else{
            Users saved = userRepo.save(saveUsers);
            userRoleRepo.nativeInsert(saved.getUserId(),usersDto.getRoleId());
            return new SuccessDataResult(saved, "Pendaftaran Akun Berhasil");
        }
    }

    @Override
    public ResultStatus saveSeller(UsersDTO usersDto) {
        Users userExist= userRepo.findByEmail(usersDto.getEmail());
        userExist.setDescription(usersDto.getDescription());
        userRoleRepo.nativeInsert(userExist.getUserId(),usersDto.getRoleId());
        return new SuccessDataResult(userExist, "Pendaftaran Penjual Berhasil!");
    }

    public ResultStatus getAllUsers() {
        List<Users> users =userRepo.findAll();
        return new SuccessDataResult<>(users,"Berhasil mendapat semua data user");
    }

    @Override
    public ResultStatus getUserById(Long user_id) {
        Users users = userRepo.findByUserId(user_id);
        return new SuccessDataResult<>(users,"Berhasil mendapat data user berdasarkan Id");
    }

    @Override
    public ResultStatus update_user(Long user_id, UsersDTO usersDto, Users users) {
        Users usersRepos = this.userRepo.findByUserId(user_id);
        if (usersDto.getImg()==null){
            Optional<City> cityOptional = cityRepo.findById(usersDto.getIdCity());
            usersRepos.setUsername(usersDto.getUsername());
            usersRepos.setFullName(usersDto.getFullName());
            usersRepos.setAddress(usersDto.getAddress());
            usersRepos.setEmail(usersDto.getEmail());
            usersRepos.setPhone(usersDto.getPhone());
            usersRepos.setCities(cityOptional.get());
            Users saved = userRepo.save(usersRepos);
            return new SuccessDataResult(saved, "Akun Berhasil Diperbarui!");
        }else {
            Optional<City> cityOptional = cityRepo.findById(usersDto.getIdCity());
            usersRepos.setUsername(usersDto.getUsername());
            usersRepos.setFullName(usersDto.getFullName());
            usersRepos.setAddress(usersDto.getAddress());
            usersRepos.setEmail(usersDto.getEmail());
            usersRepos.setPhone(usersDto.getPhone());
            usersRepos.setImg(users.getImg());
            usersRepos.setCities(cityOptional.get());
            Users saved = userRepo.save(usersRepos);
            return new SuccessDataResult(saved, "Akun Berhasil Diperbarui!");
        }
    }

    @Override
    public ResultStatus update_admin(Long user_id, UsersDTO usersDto, Users users) {
        Users usersRepos = this.userRepo.findByUserId(user_id);
//        Users usersVal =  new Users();
        if (usersDto.getImg()==null){
            usersRepos.setFullName(usersDto.getFullName());
            usersRepos.setEmail(usersDto.getEmail());
            Users saved = userRepo.save(usersRepos);
            return new SuccessDataResult(saved, "Akun Berhasil Diperbarui!");
        }else {
            usersRepos.setFullName(usersDto.getFullName());
            usersRepos.setEmail(usersDto.getEmail());
            usersRepos.setImg(users.getImg());
            Users saved = userRepo.save(usersRepos);
            return new SuccessDataResult(saved, "Akun Berhasil Diperbarui!");
        }
    }

    @Override
    public ResultStatus getVerifiedUser(String username) {
        Users users = userRepo.findByUsername(username);
        return new SuccessDataResult(users,"Akun Berhasil Diverifikasi!");
    }

    @Override
    public ResultStatus getUserByUsername(String username) {
        Users users = userRepo.findByUsername((username));
        return new SuccessDataResult(users, "Detail Akun Berhasil Ditampilkan!");
    }

    @Override
    public ResultStatus deleteUser(Long user_id) {
        Users users = userRepo.findByUserId(user_id);
        if (users != null){
            userRepo.deleteById(user_id);
        }
        return new ResultStatus(200,"Berhasil menghapus user!");
    }

    @Override
    public ResultStatus update_password(Long user_id, UsersPassDTO usersPassDto) {
        Users usersRepos = this.userRepo.findByUserId(user_id);
        usersRepos.setPassword(bCryptPasswordEncoder.encode(usersPassDto.getPassword()));
        Users saved = userRepo.save(usersRepos);
        return new SuccessDataResult(saved,"Kata Sandi Berhasil Diperbarui!");
    }

    @Override
    public ResultStatus searchUsersWithId(String fullName, Long id_city) {
        List<Users> users = userRepo.searchUsersWithId(fullName, id_city);
        return new SuccessDataResult(users,"Pengguna Berhasil Ditampilkan!");
    }

    @Override
    public ResultStatus searchUsers(String fullName) {
        List<Users> users = userRepo.searchUsers(fullName);
        return new SuccessDataResult(users,"Pengguna Berhasil Ditampilkan!");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepo.findByEmail(email);
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
