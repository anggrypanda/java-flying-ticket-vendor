package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightModel {
    private Integer flightNumber;
    private Date departureDate;
    private String departureLocation;
    private String destinationLocation;
    private int totalSeats;

    @Override
    public String toString() {
        return "FlightModel{" +
                "flightNumber =" + flightNumber +
                ", departureDate =" + departureDate +
                ", departureLocation ='" + departureLocation + '\'' +
                ", destinationLocation ='" + destinationLocation + '\'' +
                ", totalSeats =" + totalSeats +
                '}';
    }
}
