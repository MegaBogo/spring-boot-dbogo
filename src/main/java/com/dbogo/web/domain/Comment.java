package com.dbogo.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
@Table(name="Comment")
@EqualsAndHashCode(of = "seq")
@ToString(exclude = "FreeBoard")
public class Comment {

    /*
    @Index 특정한 게시물 번호에 영향을 받기 때문에 게시물 번호에 대한 인덱스를 생성해 두면
    데이터가 많을 때 성능의 향상을 기대.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String content;
    private String regId;

    @CreationTimestamp
    private Timestamp regDate;
    @UpdateTimestamp
    private Timestamp updateDate;

    //JSON으로 변환되지 않도록.
    @JsonIgnore
    //양쪽 테이블을 조회하지 않도록 지연 로딩
    @ManyToOne(fetch = FetchType.LAZY)
    private FreeBoard freeBoard;
}
