package com.ryan.a2;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PassengerController {
	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	
	@RequestMapping("/profile")
	public String profile(@CookieValue(value="passenger_id", defaultValue = "null") String passenger_id, Model model) {
		if (passenger_id.equals(null)) return "login";
		
		Passenger passenger = passengerRepository.findById(Integer.parseInt(passenger_id)).get();
		
		model.addAttribute("passenger", passenger);
		return "profile";
	}
	
	@PostMapping("/update-profile")
	public String updateProfile(@CookieValue(defaultValue = "null") String passenger_id, @RequestParam("usernameInput") String username, @RequestParam("passwordInput") String password, @RequestParam("fnameInput") String fname, @RequestParam("lnameInput") String lname, @RequestParam("addressInput") String address, @RequestParam("cityInput") String city, @RequestParam("postalCodeInput") String postalCode, Model model) {
		Passenger passenger = passengerRepository.findById(Integer.parseInt(passenger_id)).get();
		
		passenger.setUsername(username);
		passenger.setAddress(address);
		passenger.setCity(city);
		passenger.setFirstname(fname);
		passenger.setLastname(lname);
		passenger.setPassword(password);
		passenger.setPostalCode(postalCode);
		
		passengerRepository.save(passenger);
		
		model.addAttribute("passenger", passenger);
		return "profile";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("usernameInput") String username, @RequestParam("passwordInput") String password, Model model, HttpServletResponse response) {
		Passenger foundPassenger = passengerRepository.findByUsername(username);
		if (foundPassenger == null || !foundPassenger.getPassword().equals(password)) {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
		Cookie cookie = new Cookie("passenger_id", String.valueOf(foundPassenger.getPassenger_id()));
		response.addCookie(cookie);
		
		return "dashboard";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@RequestParam("usernameInput") String username, @RequestParam("passwordInput") String password, @RequestParam("fnameInput") String fname, @RequestParam("lnameInput") String lname, @RequestParam("addressInput") String address, @RequestParam("cityInput") String city, @RequestParam("postalCodeInput") String postalCode, Model model) {
		Passenger newPassenger = new Passenger(username, password, fname, lname, address, city, postalCode);
		passengerRepository.save(newPassenger);
		
		return "login";
	}
	
	@RequestMapping("/reservation")
	public String reservation(@CookieValue(value="passenger_id", defaultValue = "null") String passenger_id, Model model) {
		if (passenger_id.equals("null")) return "login";
		
		model.addAttribute("flights", flightRepository.findAll());
		return "reservation";
	}
	
	@PostMapping("/reserve")
	public String reserve(@CookieValue(value="passenger_id", defaultValue = "null") String passenger_id, @RequestParam("chosen_flight") String flight_id, @RequestParam("selected_departure_date") String departure_date, @RequestParam("num_of_passengers") int numOfPassengers, Model model) {
		Flight flight = flightRepository.findById(Integer.parseInt(flight_id)).get();
		
		Reservation reservation = new Reservation(Integer.parseInt(passenger_id), flight.getFlight_id(), LocalDate.now(), LocalDate.parse(departure_date), numOfPassengers, flight.getPrice());
		
		reservationRepository.save(reservation);
		
		model.addAttribute(reservation);
		model.addAttribute("paid", false);
		return "payment";
	}
	
	@PostMapping("delete-reservation/{id}")
	public String deleteAppointment(@CookieValue(value="passenger_id", defaultValue = "null") String passenger_id, @PathVariable String id, Model model) {
		if (passenger_id.equals("null")) return "login";
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.now();
		String dateText = date.format(dtf);
		
		int reservation_id = Integer.parseInt(id);
		Reservation reservation = reservationRepository.findById(reservation_id).get();
		
		Period period = Period.between(date, reservation.getDeparture_date());
	    int diff = Math.abs(period.getDays());
	    
	    if (diff > 10 ) {
	    	reservationRepository.deleteById(reservation_id);
	    	return "deleted";
	    } else {
	    	List<Reservation> reservations = reservationRepository.findByPassengerId(Integer.parseInt(passenger_id));

			model.addAttribute("reservations", reservations);
	    	return "dashboard";
	    }
	}
	
	@PostMapping("/payment")
	public String pay(Model model) {
		model.addAttribute(new Reservation());
		model.addAttribute("paid", true);
		return "payment";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(@CookieValue(value="passenger_id", defaultValue = "null") String passenger_id, Model model) {
		if (passenger_id.equals("null")) return "login";
		List<Reservation> reservations = reservationRepository.findByPassengerId(Integer.parseInt(passenger_id));

		model.addAttribute("reservations", reservations);
		return "dashboard";
	}
	
	@RequestMapping("/edit-reservation/{id}")
	public String modifyReservation(@PathVariable String id, Model model) {
		Reservation reservation = reservationRepository.findById(Integer.parseInt(id)).get();
		
		model.addAttribute("flights", flightRepository.findAll());
		model.addAttribute("reservation", reservation);
		return "modifyReservation";
	}
	
	@PostMapping("/edit-reservation/{id}")
	public String updateReservation(@CookieValue(value="passenger_id", defaultValue = "null") String passenger_id, @PathVariable String id, @RequestParam("chosen_flight") String flight_id, @RequestParam("selected_departure_date") String departure_date, @RequestParam("num_of_passengers") int numOfPassengers, Model model) {
		Reservation reservation = reservationRepository.findById(Integer.parseInt(id)).get();
		Flight flight = flightRepository.findById(Integer.parseInt(flight_id)).get();
		
		reservation.setDeparture_date(LocalDate.parse(departure_date));
		reservation.setFlight_id(Integer.parseInt(flight_id));
		reservation.setNo_of_passengers(numOfPassengers);
		reservation.setTotal_prices(flight.getPrice());
		
		reservationRepository.save(reservation);
		
		if (passenger_id.equals("null")) return "login";
		List<Reservation> reservations = reservationRepository.findByPassengerId(Integer.parseInt(passenger_id));

		model.addAttribute("reservations", reservations);
		
		return "dashboard";
	}
}
