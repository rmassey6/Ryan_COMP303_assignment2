package com.ryan.a2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	@Query("SELECT u FROM Reservation u WHERE u.passenger_id = ?1")
	List<Reservation> findByPassengerId(int passenger_id);
}
