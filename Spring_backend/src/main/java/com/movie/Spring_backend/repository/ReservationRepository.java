package com.movie.Spring_backend.repository;

import com.movie.Spring_backend.entity.MemberEntity;
import com.movie.Spring_backend.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

}
