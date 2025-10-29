package com.ryan.a2;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {
	@Id
	@Column(name="reservation_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reservation_id;
	@Column(name="passenger_id")
	private int passenger_id;
	@Column(name="flight_id")
	private int flight_id;
	@Column(name="booking_date")
	private LocalDate booking_date;
	@Column(name="departure_date")
	private LocalDate departure_date;
	@Column(name="no_of_passengers")
	private int no_of_passengers;
	@Column(name="total_price")
	private double total_price;
	
	public Reservation() {}
	
	public Reservation(int passenger_id, int flight_id, LocalDate booking_date, LocalDate departure_date, int no_of_passengers, double total_price) {
		super();
		this.flight_id = flight_id;
		this.booking_date = booking_date;
		this.passenger_id = passenger_id;
		this.departure_date = departure_date;
		this.no_of_passengers = no_of_passengers;
		this.total_price = total_price;
	}
	
	public int getReservation_id() {
		return reservation_id;
	}
	
	public int getPassenger_id() {
		return passenger_id;
	}
	public void setPassenger_id(int passenger_id) {
		this.passenger_id = passenger_id;
	}
	
	public int getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}
	
	public LocalDate getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(LocalDate booking_date) {
		this.booking_date = booking_date;
	}
	
	public LocalDate getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(LocalDate departure_date) {
		this.departure_date = departure_date;
	}
	
	public int getNo_of_passengers() {
		return no_of_passengers;
	}
	public void setNo_of_passengers(int no_of_passengers) {
		this.no_of_passengers = no_of_passengers;
	}
	
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_prices(double total_price) {
		this.total_price = total_price;
	}
	
}
