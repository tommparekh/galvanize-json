package com.cts.galvanize.Json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Flight {

    @JsonProperty("Departs")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departs;

    @JsonProperty("Tickets")
    private List<Ticket> tickets;

    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    static class Ticket {
        @JsonProperty("Passenger")
        private Person passenger;

        @JsonProperty("Price")
        private int price;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Person getPassenger() {
            return passenger;
        }

        public void setPassenger(Person passenger) {
            this.passenger = passenger;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    static class Person {
        @JsonProperty("FirstName")
        private String firstName;

        @JsonProperty("LastName")
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
