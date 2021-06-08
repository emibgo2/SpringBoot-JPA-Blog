package com.cos.blog.model;

import lombok.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!
@Entity // User 클래스가 MySQL에 테이블이 생성.
//@DynamicInsert insert시 null인 필드를 제외 시킴
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;// 시퀀스, auto_increment

    @Column(nullable = false, length = 30,unique = true)
    private String username;// ID

    @Column(nullable = false, length = 100) // 해쉬 화를 위한 자리를 넉넉하게 배정
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("user")
    //DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role;// 정확하게는 Enum을 써야 좋다. ( 권한 배정 ) 도메인- 범위가 정해졌다.

    @CreationTimestamp
    private Timestamp createDate;



}
