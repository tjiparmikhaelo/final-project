package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsersRoleRepo extends JpaRepository<UsersRole, Long>
{
    @Modifying
    @Query(
            value = "insert into user_role (user_id , role_id) values (:userId,:roleId)",
            nativeQuery = true
    )
    public void nativeInsert(
            @Param("userId") Long userId,
            @Param("roleId") int roleId
    );
}
