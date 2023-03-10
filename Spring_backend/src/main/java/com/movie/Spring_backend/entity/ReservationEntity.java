package com.movie.Spring_backend.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name="movie_reservation")
@Getter
@NoArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @Column(nullable = false)
    private Date rdate;

    @Column(nullable = false)
    private Integer rprice;

    @Column(nullable= false)
    private String rpeople;

    @Column(nullable = false)
    private String rtoken;

    @Column(nullable = false)
    private String rpayid;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="miid")
    private MovieInfoEntity movieInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="uid")
    private MemberEntity member;

    @Builder
    public ReservationEntity(Long rid ,Date rdate, Integer rprice,String rpeople,String rtoken,String rpayid, MovieInfoEntity movieInfo, MemberEntity member) {
        this.rid=rid;
        this.rdate=rdate;
        this.rprice=rprice;
        this.rpeople=rpeople;
        this.rtoken=rtoken;
        this.rpayid=rpayid;
        this.movieInfo=movieInfo;
        this.member=member;
    }
}