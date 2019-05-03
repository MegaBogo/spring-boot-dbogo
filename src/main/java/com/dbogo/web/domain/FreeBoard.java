package com.dbogo.web.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "replies")
@Entity
@Table(name = "FreeBoard")
@EqualsAndHashCode(of="seq")
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String title;
    private String regId;
    private String content;

    @CreationTimestamp
    private Timestamp regDate;
    @UpdateTimestamp
    private Timestamp updateDate;

    // mappedBy는 ~에 매이게 된다이므로 종속적인 클래스의 인스턴스 변수를 지정함.
    @OneToMany(mappedBy = "board"
             , cascade = CascadeType.ALL
             , fetch = FetchType.LAZY)
    private List<Comment> replies;


}
