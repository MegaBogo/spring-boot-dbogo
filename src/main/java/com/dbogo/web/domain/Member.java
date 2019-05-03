package com.dbogo.web.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Member")
@EqualsAndHashCode(of="userId")
public class Member {

    @Id
    private String userId;
    private String pwd;
    private String nickname;
    private String email;
    private String photo;

}
