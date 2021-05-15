package com.cts.galvanize.Json;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class FlightController {

    @GetMapping("/flights/flight")
    public Flight getFlightDetails() throws ParseException {
        List<Flight> flights = getFlights(1);
        return flights.get(0);
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() throws ParseException {
        List<Flight> flights = getFlights(2);
        return flights;
    }

    private List<Flight> getFlights(int count) throws ParseException {
        List<Flight> flights = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (count >= 1) {
            Flight flight = getFlight1(formatter);
            flights.add(flight);
        }

        if (count >= 2) {
            Flight flight = getFlight2(formatter);
            flights.add(flight);
        }

        return flights;
    }

    private Flight getFlight1(SimpleDateFormat formatter) throws ParseException {
        Flight flight = new Flight();
        Date date = formatter.parse("2017-04-21 14:34");
        flight.setDeparts(date);
        flight.setTickets(Arrays.asList(getTicket1()));
        return flight;
    }

    private Flight getFlight2(SimpleDateFormat formatter) throws ParseException {
        Flight flight = new Flight();
        Date date = formatter.parse("2018-06-26 14:34");
        flight.setDeparts(date);
        flight.setTickets(Arrays.asList(getTicket2()));
        return flight;
    }


    private Flight.Ticket getTicket1() {
        Flight.Person passenger = new Flight.Person();
        passenger.setFirstName("John");
        passenger.setLastName("Dwaight");

        Flight.Ticket ticket = new Flight.Ticket();
        ticket.setPrice(200);
        ticket.setPassenger(passenger);
        return ticket;
    }

    private Flight.Ticket getTicket2() {
        Flight.Person passenger = new Flight.Person();
        passenger.setFirstName("Alice");
        passenger.setLastName(null);

        Flight.Ticket ticket = new Flight.Ticket();
        ticket.setPrice(400);
        ticket.setPassenger(passenger);
        return ticket;
    }

//    public static void main(String[] args) {
//        SpringApplication.run(FlightController.class, args);
//    }
}
