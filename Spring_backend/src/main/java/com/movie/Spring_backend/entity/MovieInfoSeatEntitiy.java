package com.movie.Spring_backend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Table(name="Movie_infoseat")
@Entity
@Getter
@NoArgsConstructor
public class MovieInfoSeatEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long misid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sid") //조인할 컬럼 이름
    private SeatEntity seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="miid") //조인할 컬럼 이름
    private MovieInfoEntity info;


    @Builder
    public MovieInfoSeatEntitiy(Long misid,SeatEntity seat,MovieInfoEntity info) {
        this.misid= misid;
        this.seat=seat;
        this.info=info;
    }

}
