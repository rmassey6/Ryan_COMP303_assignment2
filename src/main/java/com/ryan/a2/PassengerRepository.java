package com.ryan.a2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
	@Query("SELECT u FROM Passenger u WHERE u.username = ?1")
	Passenger findByUsername(String username);
}
