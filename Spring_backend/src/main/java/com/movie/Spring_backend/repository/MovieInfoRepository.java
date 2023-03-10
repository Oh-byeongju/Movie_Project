package com.movie.Spring_backend.repository;

import com.movie.Spring_backend.entity.MovieEntity;
import com.movie.Spring_backend.entity.MovieInfoEntity;
import com.movie.Spring_backend.entity.MovieMemberEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MovieInfoRepository extends JpaRepository<MovieInfoEntity, Long> {

    @Query("SELECT mi From MovieInfoEntity as mi Group by mi.miday Order by mi.miday ASC")
    List<MovieInfoEntity> findAll();

    public List<MovieInfoEntity> findByMovie(MovieEntity id);

    @Query("SELECT mi From MovieInfoEntity as mi, MovieEntity as m Where mi.movie.mid= (:mid) " +
            "and mi.mistarttime >= function('addtime', now(), '0:30:00') Group by mi.miday Order by mi.miday ASC ")
    public List<MovieInfoEntity> findByMovieToDay(@Param("mid") Long mid);

    //in able을 위해
    @Query("SELECT mi From MovieInfoEntity as mi, CinemaEntity as c WHERE mi.cinema.cid IN (:cid) " +
            "and mi.mistarttime >= function('addtime', now(), '0:30:00')")
    public List<MovieInfoEntity> findByCinemaCidIn(@Param("cid") List<Long> cid);

    //notin able을 위해
    @Query("SELECT mi From MovieInfoEntity as mi, CinemaEntity as c WHERE mi.cinema.cid Not IN (:cid)")
    public List<MovieInfoEntity> findByCinemaCidNotIn(@Param("cid") List<Long> cid);

    //극장 영화로 day 검색
    public List<MovieInfoEntity> findByCinemaCidInAndMovieMid(List<Long> cid, Long mid);

    //miday로 영화or극장 검색하려고 만듬
    public List<MovieInfoEntity> findByMiday(Date miday);

    //mid를 구해야함
    public List<MovieInfoEntity> findByMidayAndCinemaCidIn(Date miday, List<Long> cid);

    public List<MovieInfoEntity> findByMidayAndMovieMid(Date miday, Long mid);

    @Query("Select mi From MovieInfoEntity as mi JOIN FETCH mi.cinema " +
            "JOIN FETCH mi.movie " +
            "Where mi.miday = (:miday) AND " +
            "mi.movie.mid= (:mid) AND "+
            "mi.cinema.cid IN (:cid) "+
            "ORDER BY mistarttime asc"
    )
    @EntityGraph(attributePaths = {"cinema.theater"})
    //페치조인을 해서 영화와 극장 정보까지 함께 보내기
    public List<MovieInfoEntity> findBySchedule(@Param("miday")Date miday,@Param("mid") Long mid, @Param("cid")List<Long> cid);

    /*
    @Query(value = "SELECT a.miid, COALESCE(b.CNT,0) AS cnt " +
            "FROM Movie_Information AS a " +
            "LEFT OUTER JOIN( " +
            "SELECT miid, COUNT(*) AS CNT " +
            "FROM Movie_infoseat  " +
            "GROUP BY miid " +
            ") AS b " +
            "ON a.miid = b.miid " +
            "WHERE a.miid IN (1,2,3,4) " +
            "ORDER BY a.miid ",nativeQuery = true)
    public List<MovieInfoEntity> findCount();*/

    // 특정 Movie id를 가지고 현재 예매가 가능한 영화 정보를 들고오는 메소드
    @Query(value = "SELECT mi FROM MovieInfoEntity as mi WHERE mi.movie = :movie AND " +
            "mi.mistarttime >= function('addtime', now(), '0:30:00')")
    List<MovieInfoEntity> findMovieScreen(@Param("movie") MovieEntity movie);

    // 상영이 끝난 특정 영화 정보를 들고오는 메소드
    @Query(value = "SELECT mi FROM MovieInfoEntity as mi " +
            "WHERE mi.movie = :movie AND mi.miendtime <= now()")
    List<MovieInfoEntity> findInfoBeforeToday(@Param("movie") MovieEntity movie);


    //영황 상영 시간표를 검색할 때 영화, 날짜, 지역으로 검색하는 메소드
    @Query(value = "SELECT mi FROM MovieInfoEntity as mi " +
            "where mid= :mid and mi.miday= :miday and mi.mistarttime >= function('addtime', now(), '0:30:00') and " +
            "mi.cinema.cid in (select c.cid from CinemaEntity as c " +
            "where tid in(select t.tid from TheaterEntity as t where t.tarea= :tarea)) ")
    List <MovieInfoEntity> findSchedule(@Param("mid") Long mid ,@Param("miday") Date miday ,@Param("tarea")String tarea);

    

    //영화, 날짜, 상영관으로 miid를 추출하는 메소드
    @Query(value = "SELECT mi FROM MovieInfoEntity as mi " +
            "where mid= :mid and mi.miday= :miday and " +
            "cid = :cid and mi.mistarttime >= function('addtime', now(), '0:30:00')"
    )
    List <MovieInfoEntity> findmiid(@Param("mid") Long mid ,@Param("miday") Date miday ,@Param("cid")Long cid);


    @Query(value = "SELECT mi FROM MovieInfoEntity as mi " +
            "where mi.miday= :miday and mi.mistarttime >= function('addtime', now(), '0:30:00') and " +
            "mi.cinema.cid in (select c.cid from CinemaEntity as c " +
            "where tid = :tid) ")
    List <MovieInfoEntity> findTimeTheater(@Param("miday") Date miday ,@Param("tid")Long tid);





}

