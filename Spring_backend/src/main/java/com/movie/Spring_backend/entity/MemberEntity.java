package com.movie.Spring_backend.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Member")     // 디비의 테이블명과 클래스 명이 다를 경우
public class MemberEntity {

    @Id
    private String u_id;

    @Column(nullable = false)
    private String u_pw;

    @Column(nullable = false)
    private String u_name;

    @Column(nullable = false)
    private String u_email;

    @Column(nullable = false)
    private String u_tel;

    @Column(nullable = false)
    private String u_addr;

    @Column(nullable = false)
    private Date u_birth;

    @Enumerated(EnumType.STRING)
    private Authority u_authority;

//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }

//    public void setPassword(String password) { this.password = password; }


    @Builder
    public MemberEntity(String u_id, String u_pw, String u_name, String u_email, String u_tel, String u_addr, Date u_birth, Authority u_authority) {
        this.u_id = u_id;
        this.u_pw = u_pw;
        this.u_name = u_name;
        this.u_email = u_email;
        this.u_tel = u_tel;
        this.u_addr = u_addr;
        this.u_birth = u_birth;
        this.u_authority = u_authority;

    }
}
