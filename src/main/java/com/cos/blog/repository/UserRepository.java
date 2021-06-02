package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


// JSP로 치면 DAO
// 자동으로 bean 들고이 된다.
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> {

}
