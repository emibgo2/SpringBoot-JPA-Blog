package com.cos.blog.repository;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// JSP로 치면 DAO
// 자동으로 bean 들고이 된다.
// @Repository 생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {


}
