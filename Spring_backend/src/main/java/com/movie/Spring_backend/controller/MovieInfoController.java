package com.movie.Spring_backend.controller;

import com.movie.Spring_backend.dto.CinemaDto;
import com.movie.Spring_backend.dto.MovieDto;
import com.movie.Spring_backend.dto.MovieInfoDto;
import com.movie.Spring_backend.dto.TheaterDto;
import com.movie.Spring_backend.entity.CinemaEntity;
import com.movie.Spring_backend.entity.MovieEntity;
import com.movie.Spring_backend.entity.MovieInfoEntity;
import com.movie.Spring_backend.entity.TheaterEntity;
import com.movie.Spring_backend.service.CinemaService;
import com.movie.Spring_backend.service.MovieInfoService;
import com.movie.Spring_backend.service.MovieService;
import com.movie.Spring_backend.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

//crossorigin 바꿔야함
import java.util.List;
@CrossOrigin(origins = "${spring.cors.origins}", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/infomovie")
public class MovieInfoController {

    private final MovieInfoService movieInfoService;
    private final CinemaService cinemaService;
    private final MovieService movieService;
     final TheaterService theaterService;
    @GetMapping("/normal/movieinfo")
    public List<MovieInfoDto> getData() {
        return movieInfoService.findAllMiday();
    }
    //현재오류 return movieInfoService.findByMovieToDay(id);
    @GetMapping("/normal/movieselect")
    public Set<String> findByMovie(@RequestParam Long id) {
        //영화 아이디로 영화 정보 추출 CID를 리스트로 추출
        List<Long> datas = movieInfoService.findByMovie(id);
        //여기선 taea를 추출해야함
        //여기서 같이 영화에 대한 상영정보 전달하

        return cinemaService.findByCidInTarea(datas);
    }

    @GetMapping("/normal/movieselectday")
    public List<MovieInfoDto> findByMovieToDay(@RequestParam Long id) {
        //영화 아이디로 영화 정보 추출 CID를 리스트로 추출
        //여기선 taea를 추출해야함
        //여기서 같이 영화에 대한 상영정보 전달하
      return movieInfoService.findByMovieToDay(id);
                //영화 검색 시 해당하는 상영 날짜 추출
    }
    @GetMapping("/normal/movietheater")
    //선택한 지역에 따라 지역 검색
    public List<TheaterDto> getMovieTheaetr(@RequestParam Long id, @RequestParam String area) {
        //mid로 인포서비스에서 cid 추출
        List<Long> datad = movieInfoService.findByMovie(id);
        //cid로 tid 추출
        List<Long> theaterId = cinemaService.findByCidInTid(datad);
        return theaterService.findByTidInAndTarea(theaterId, area);
    }

    @GetMapping("/normal/theaterday")
    public List<MovieInfoDto> getTheaterDay(@RequestParam Long id){
        //Long cid에 담겨있음
        List<Long> mappedcid= cinemaService.findByTheaterday(id);

        //메핑된 cid로 movieinfo 검색
        return movieInfoService.findByCinemaCidIn(mappedcid);

    }

    @GetMapping("/normal/movietheaterday")
    public List<MovieInfoDto> findByMovieTheaterDay(@RequestParam Long tid, @RequestParam Long mid){

        List<Long> mappedcid= cinemaService.findByTheaterday(tid);

        return movieInfoService.findByMovieTheaterDay(mappedcid,mid);
    }

}
