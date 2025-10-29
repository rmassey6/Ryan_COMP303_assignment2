package com.ryan.a2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="passenger")
public class Passenger {
	@Id
	@Column(name="passenger_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int passenger_id;
	@Column(name="username", length=2, nullable=false)
	@NotBlank(message = "Username is mandatory")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="firstname")
	private String firstname;
	@Column(name="lastname")
	private String lastname;
	@Column(name="address")
	private String address;
	@Column(name="city")
	private String city;
	@Column(name="postal_code")
	private String postalCode;
	
	public Passenger() {}
	
	public Passenger(String username, String password, String firstname, String lastname, String address, String city, String postalCode) {
		super();
		this.address = address;
		this.city = city;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.postalCode = postalCode;
		this.username = username;
	}
	
	public int getPassenger_id() {
		return passenger_id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
