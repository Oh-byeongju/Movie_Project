package com.movie.Spring_backend.exceptionlist;

import com.movie.Spring_backend.error.exception.EntityNotFoundException;
import com.movie.Spring_backend.error.exception.ErrorCode;
import org.springframework.http.HttpStatus;

//
public class MovieNotFoundException extends EntityNotFoundException {
    //영화를 검색했을 때 값이 NULL일 때 사용하는 예외처리
    public MovieNotFoundException(String message) {super(message, ErrorCode.SELECT_IS_NONE);
}}

