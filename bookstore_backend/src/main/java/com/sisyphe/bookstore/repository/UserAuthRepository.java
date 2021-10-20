package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {

    @Query(value = "from UserAuth where username=:username and password=:password")
    UserAuth checkUser(@Param("username") String username, @Param("password") String password);

    @Query(value = "from UserAuth where username=:username")
    UserAuth checkUsernameExist(@Param("username") String username);

}
