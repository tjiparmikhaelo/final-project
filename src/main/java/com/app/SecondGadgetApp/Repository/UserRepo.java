package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long>
{
    List<User> findAll();
    User findByUserId(Long user_id);
}
