package controller;

import dbconnection.DBconnection;
import model.FlightModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Flight {
    Scanner scanner = new Scanner(System.in);
    static List<FlightModel> flightList = new ArrayList<>();

    public static List<FlightModel> getFlightDetails() {
        Statement stmt = null;
        FlightModel flightModel = new FlightModel();

        try {
            Connection conn = DBconnection.ConnectToDB();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String selectFromSQL;
            selectFromSQL = "SELECT * FROM ftv.flight";
            ResultSet rs = stmt.executeQuery(selectFromSQL);

            while (rs.next()) {
                flightModel = new FlightModel();
                flightModel.setFlightNumber(rs.getInt("flight_number"));
                flightModel.setDepartureDate(rs.getDate("departure_date"));
                flightModel.setDepartureLocation(rs.getString("departure_location"));
                flightModel.setDestinationLocation(rs.getString("destination_location"));
                flightModel.setTotalSeats(rs.getInt("total_seats"));
                flightList.add(flightModel);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return flightList;
    }

    public static void addFlight() {
        Statement stmt = null;

        try {
            Connection conn = DBconnection.ConnectToDB();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String selectFromSQL;
            selectFromSQL = "SELECT * FROM ftv.flight";
            ResultSet rs = stmt.executeQuery(selectFromSQL);

            stmt.executeUpdate("INSERT INTO ftv.flight " + "VALUES (1111, '2020-12-20', 'Oradea', 'Budapest', 180)");

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateFlight() {
        Statement stmt = null;

        try {
            Connection conn = DBconnection.ConnectToDB();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String selectFromSQL;
            selectFromSQL = "SELECT * FROM ftv.flight";
            ResultSet rs = stmt.executeQuery(selectFromSQL);

            stmt.executeUpdate("UPDATE ftv.flight SET departure_location = 'Venice' WHERE flight_number = 1113");

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteFlight() {
        Statement stmt = null;

        try {
            Connection conn = DBconnection.ConnectToDB();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String selectFromSQL;
            selectFromSQL = "SELECT * FROM ftv.flight";
            ResultSet rs = stmt.executeQuery(selectFromSQL);

            stmt.executeUpdate("DELETE FROM ftv.flight WHERE flight_number = 0000 AND departure_date = '2020-12-05'");

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void searchFlight() {
        Scanner scanner = new Scanner(System.in);
        Statement stmt = null;

        try {
            List<FlightModel> otherFlightsList = new ArrayList<FlightModel>();
            FlightModel selectedFlight = new FlightModel();
            Connection conn = DBconnection.ConnectToDB();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String selectFromSQL;
            selectFromSQL = "SELECT * FROM ftv.flight";
            ResultSet rs = stmt.executeQuery(selectFromSQL);

            System.out.println("Please specify the departure location: ");
            String fromScanner = scanner.next();
            System.out.println("Please specify the destination location: ");
            String toScanner = scanner.next();
            System.out.println("Please specify the departure date: ");
            String depDateScanner = scanner.next("\\d{4}-\\d{2}-\\d{2}");
//            System.out.println("Please specify the return date or -n- if there's no return date: ");
//            String retDateScanner = scanner.next("(\\d{4}-\\d{2}-\\d{2}|n)");
            System.out.println("Type -y- to see more flights or type -n- to skip: ");
            String otherFlightsScanner = scanner.next("(y|n)");

            while (rs.next()) {
                FlightModel otherFlight = new FlightModel();
                
                String from = rs.getString("departure_location");
                String to = rs.getString("destination_location");
                Date departureDate = rs.getDate("departure_date");
//                Date returnDate = rs.getDate("departure_date");

//        If there are no more seats available, the “sold out” message will be showed.

                if (fromScanner.equalsIgnoreCase(from) && toScanner.equalsIgnoreCase(to)) {
                    if (depDateScanner.equals(departureDate.toString())) {
//                        && (retDateScanner.equals(returnDate.toString()) || retDateScanner.equals("n")))

                        selectedFlight.setFlightNumber(rs.getInt("flight_number"));
                        selectedFlight.setDepartureLocation(rs.getString("departure_location"));
                        selectedFlight.setDestinationLocation(rs.getString("destination_location"));
                        selectedFlight.setDepartureDate(rs.getDate("departure_date"));
                        selectedFlight.setTotalSeats(rs.getInt("total_seats"));

                    } else {
                        otherFlight.setFlightNumber(rs.getInt("flight_number"));
                        otherFlight.setDepartureLocation(rs.getString("departure_location"));
                        otherFlight.setDestinationLocation(rs.getString("destination_location"));
                        otherFlight.setDepartureDate(rs.getDate("departure_date"));
                        otherFlight.setTotalSeats(rs.getInt("total_seats"));

                        otherFlightsList.add(otherFlight);
                    }
                }
            }
            System.out.println("The selected flight:\n" + selectedFlight);

            switch (otherFlightsScanner) {
                case "y":
                    System.out.println("More flights:\n" + otherFlightsList);
                    break;
                case "n":
                    break;
                default:
                    System.out.println("Choose -y- or -n-");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}