package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository  extends JpaRepository<User,Integer> {

}
