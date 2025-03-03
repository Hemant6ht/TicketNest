package com.TicketNest.CinemaService.repository;

import com.TicketNest.CinemaService.Entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema, Integer> {
}
