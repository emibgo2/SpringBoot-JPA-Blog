package com.cos.blog.model;

import com.cos.blog.dto.ReplySaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name ="userId")
    private User user;




}
