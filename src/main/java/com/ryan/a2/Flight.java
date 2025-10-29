package com.ryan.a2;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="flight")
public class Flight {
	@Id
	@Column(name="flight_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int flight_id;
	@Column(name="airline_name")
	private String airline_name;
	@Column(name="departure_time")
	private Time departure_time;
	@Column(name="arrival_time")
	private Time arrival_time;
	@Column(name="origin")
	private String origin;
	@Column(name="destination")
	private String destination;
	@Column(name="price")
	private Float price;
	
	public Flight() {}
	
	public Flight(int flight_id, String airline_name, Time departure_time, Time arrival_time, String origin, String destination, Float price) {
		super();
		this.flight_id = flight_id;
		this.airline_name = airline_name;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
		this.origin = origin;
		this.destination = destination;
		this.price = price;
	}
	
	public int getFlight_id() {
		return flight_id;
	}
	
	public String getAirline_name() {
		return airline_name;
	}
	public void setAirline_name(String airline_name) {
		this.airline_name = airline_name;
	}
	
	public Time getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Time departure_time) {
		this.departure_time = departure_time;
	}
	
	public Time getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(Time arrival_time) {
		this.arrival_time = arrival_time;
	}
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Float getPrice() {
		return price;
	}
	public void setCity(Float price) {
		this.price = price;
	}
}
