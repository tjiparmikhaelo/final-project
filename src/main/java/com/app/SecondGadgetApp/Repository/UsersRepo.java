package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
//
@Repository
@Transactional
public interface UsersRepo extends JpaRepository<Users, Long>
{
    List<Users> findAll();
    Users findByUserId(Long user_id);
    Users findByUsername(String username);
    Users findByEmail(String email);

    @Query(value = "select * from users where full_name like CONCAT('%', :fullName, '%') and city_id = :idCity", nativeQuery = true)
    List<Users> searchUsersWithId(String fullName, Long idCity);

    @Query(value = "select * from users where users.full_name like CONCAT('%', :fullName, '%') ", nativeQuery = true)
    List<Users> searchUsers(String fullName);
}