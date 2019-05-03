package com.dbogo.web.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "member")
@Entity
@Table(name = "Profile")
@EqualsAndHashCode(of = "seq")
public class Profile {

    /*
    GenerationType.AUTO와 GenerationType.IDENTITY
    엔티티의 식별키를 처리하는 여러 방식 중에서도 AUTO는 DB에 맞게 자동으로
    식별키를 처리하는 방식으로 동작합니다.

    MySQL의 경우 Spring boot 1.5.4 version을 이용할 때 AUTO로 지정하면 칼럼이 auto_increment로 지정되었습니다.

    반면에 2.0.0M1 버전의 경우 AUTO로 지정하면 hibernate_sequence라는 테이블을 생성하고
    번호를 유지하는 방식으로 변경되었습니다.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String name;
    private boolean current;

    @ManyToOne
    private Member member;

}
