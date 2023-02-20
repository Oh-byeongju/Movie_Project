package com.movie.Spring_backend.service;

import com.movie.Spring_backend.dto.MovieInfoSeatDto;
import com.movie.Spring_backend.entity.MovieInfoSeatEntity;
import com.movie.Spring_backend.repository.MovieInfoSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieInfoSeatService {
    private final MovieInfoSeatRepository movieInfoSeatRepository;

    @Transactional
    public List<MovieInfoSeatDto> findByInfoMovie(Long miid) {
        List<MovieInfoSeatEntity> datas = movieInfoSeatRepository.findByInfoMovie( miid);
        return datas.stream().map(data -> MovieInfoSeatDto.builder().misid(data.getMisid()).seat(data.getSeat()).info(data.getInfo()).build()).collect(Collectors.toList());
    }
}
