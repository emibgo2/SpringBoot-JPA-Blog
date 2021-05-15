package com.cos.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // User 클래스가 MySQL에 테이블이 생성.
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;// 시퀀스, auto_increment

    @Column(nullable = false, length = 30)
    private String username;// ID

    @Column(nullable = false, length = 100) // 해쉬 화를 위한 자리를 넉넉하게 배정
    private String password;

    @Column(nullable = false, length = 50)
    private String email;


    @ColumnDefault("'user'")

    private String role;


    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;


}
