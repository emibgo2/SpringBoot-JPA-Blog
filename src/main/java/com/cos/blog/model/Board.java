package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM -> Java Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Many = Many, User =One
    @JoinColumn(name = "userId")
    private User user; // DB 는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
    // User 데이터가 One 이기 때문에 foreign key 로 만들어짐

    @CreationTimestamp
    private Timestamp createDate;
}

