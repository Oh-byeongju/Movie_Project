package com.movie.Spring_backend.repository;
import com.movie.Spring_backend.entity.CinemaEntity;
import com.movie.Spring_backend.entity.MovieInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity,Long>  {
    //JpaRepository<Entity클래스, PK값>
    //엔티티의 CRUD 기능 사용할 수 있게 해준다.
     public List<CinemaEntity> findByCid(Long id);
}
//디비접근

