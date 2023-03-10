/*
  23-02-07 MovieEntity를 dto로 매핑하기 위한 클래스 생성(오병주)
  23-02-14 상세영화 페이지 dto 매핑 메소드 생성(오병주)
 */

package com.movie.Spring_backend.mapper;

import com.movie.Spring_backend.dto.MovieDto;
import com.movie.Spring_backend.entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {

    // 전체 영화 페이지에 사용되는 mapping 메소드
    public List<MovieDto> toDtoAllORComingMovie(List<MovieEntity> ShowMovies, List<MovieEntity> NotShowMovies, Set<Long> MovieLikes, float AllReserveCnt) {

        List<MovieDto> result = new ArrayList<>();

        // 예매가 가능한 영화 List에 삽입
        for (MovieEntity m : ShowMovies) {
            result.add(MovieDto.builder()
                        .mid(m.getMid())
                        .mdir(m.getMdir())
                        .mtitle(m.getMtitle())
                        .mgenre(m.getMgenre())
                        .mtime(m.getMtime())
                        .mdate(m.getMdate())
                        .mrating(m.getMrating())
                        .mstory(m.getMstory())
                        .mimagepath(m.getMimagepath())
                        .mlikes(m.getCntMovieLike())
                        .mscore(m.getAvgScore())
                        .mlike(MovieLikes.contains(m.getMid()))
                        .reserve(true)
                        .reserveRate(m.getCntReserve() / AllReserveCnt * 100).build());
        }

        // 예매가 불가능한 영화 리스트에 삽입
        for (MovieEntity m : NotShowMovies) {
            result.add(MovieDto.builder()
                    .mid(m.getMid())
                    .mdir(m.getMdir())
                    .mtitle(m.getMtitle())
                    .mgenre(m.getMgenre())
                    .mtime(m.getMtime())
                    .mdate(m.getMdate())
                    .mrating(m.getMrating())
                    .mstory(m.getMstory())
                    .mimagepath(m.getMimagepath())
                    .mlikes(m.getCntMovieLike())
                    .mscore(m.getAvgScore())
                    .mlike(MovieLikes.contains(m.getMid()))
                    .reserve(false)
                    .reserveRate(m.getCntReserve() / AllReserveCnt * 100).build());
        }
        return result;
    }

    // 현재상영작 페이지에 사용되는 mapping 메소드
    public List<MovieDto> toDtoScreenMovie(List<MovieEntity> ShowMovies, Set<Long> MovieLikes, float AllReserveCnt) {

        List<MovieDto> result = new ArrayList<>();

        // 예매가 가능하고 개봉한 영화 List에 삽입
        for (MovieEntity m : ShowMovies) {
            result.add(MovieDto.builder()
                    .mid(m.getMid())
                    .mdir(m.getMdir())
                    .mtitle(m.getMtitle())
                    .mgenre(m.getMgenre())
                    .mtime(m.getMtime())
                    .mdate(m.getMdate())
                    .mrating(m.getMrating())
                    .mstory(m.getMstory())
                    .mimagepath(m.getMimagepath())
                    .mlikes(m.getCntMovieLike())
                    .mscore(m.getAvgScore())
                    .mlike(MovieLikes.contains(m.getMid()))
                    .reserve(true)
                    .reserveRate(m.getCntReserve() / AllReserveCnt * 100).build());
        }
        return result;
    }

    // 영화 상세페이지에 필요한 내용들을 mapping 해주는 메소드
    public MovieDto toDtoDetail(MovieEntity entity, boolean like, boolean Screen, List<String> Actors, float AllReserveCnt) {

        // 예외처리
        if (entity == null) {
            return null;
        }

        // 영화 예매가 가능할 경우 예매율까지 계산해서 전달, 아닐경우 예매율을 제외하고 전달
        if (Screen) {
            return MovieDto.builder()
                    .mid(entity.getMid())
                    .mdir(entity.getMdir())
                    .mtitle(entity.getMtitle())
                    .mgenre(entity.getMgenre())
                    .mtime(entity.getMtime())
                    .mdate(entity.getMdate())
                    .mrating(entity.getMrating())
                    .mstory(entity.getMstory())
                    .mimagepath(entity.getMimagepath())
                    .mlikes(entity.getCntMovieLike())
                    .mscore(entity.getAvgScore())
                    .mlike(like)
                    .actors(Actors)
                    .reserve(true)
                    .reserveRate(entity.getCntReserve() / AllReserveCnt * 100).build();
        }
        else {
            return MovieDto.builder()
                    .mid(entity.getMid())
                    .mdir(entity.getMdir())
                    .mtitle(entity.getMtitle())
                    .mgenre(entity.getMgenre())
                    .mtime(entity.getMtime())
                    .mdate(entity.getMdate())
                    .mrating(entity.getMrating())
                    .mstory(entity.getMstory())
                    .mimagepath(entity.getMimagepath())
                    .mlikes(entity.getCntMovieLike())
                    .mscore(entity.getAvgScore())
                    .mlike(like)
                    .actors(Actors)
                    .reserve(false).build();
        }
    }

    public MovieDto toAble(MovieEntity entity) {

        // 예외처리
        if (entity == null) {
            return null;
        }

        return MovieDto.builder()
                .mid(entity.getMid())
                .mdir(entity.getMdir())
                .mtitle(entity.getMtitle())
                .mgenre(entity.getMgenre())
                .mtime(entity.getMtime())
                .mdate(entity.getMdate())
                .mrating(entity.getMrating())
                .mstory(entity.getMstory())
                .mimagepath(entity.getMimagepath())
                .mlikes(entity.getCntMovieLike())
                .mscore(entity.getAvgScore())
                .able("able").build();
    }

    public MovieDto toDisable(MovieEntity entity) {

        // 예외처리
        if (entity == null) {
            return null;
        }

        return MovieDto.builder()
                .mid(entity.getMid())
                .mdir(entity.getMdir())
                .mtitle(entity.getMtitle())
                .mgenre(entity.getMgenre())
                .mtime(entity.getMtime())
                .mdate(entity.getMdate())
                .mrating(entity.getMrating())
                .mstory(entity.getMstory())
                .mimagepath(entity.getMimagepath())
                .mlikes(entity.getCntMovieLike())
                .mscore(entity.getAvgScore())
                .able("disable")
                .build();
    }
}
