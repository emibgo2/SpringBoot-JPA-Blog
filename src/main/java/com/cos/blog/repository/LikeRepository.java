package com.cos.blog.repository;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Reply,Integer> {

    @Modifying
    @Query(value = "INSERT INTO boardlike(userId,boardId) VALUES(?1,?2)",nativeQuery = true)
    int lSave(int userId, int boardId) ;

}
