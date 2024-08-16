package com.sidgupta.tests.flightreservation.model;

public record PassengerDetail(String fname,
                              String lname,
                              String email,
                              String password,
                              String street,
                              String city,
                              String state,
                              String zip,
                              String price,
                              String noOfPassengers) {

}
