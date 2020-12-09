package controller;

import dbconnection.DBconnection;
import model.TicketModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ticket {
    Scanner scanner = new Scanner(System.in);

    public static String getTicketDetails() {
        Statement stmt = null;
        TicketModel ticketModel = new TicketModel();
        List<TicketModel> ticketList = new ArrayList<>();

        try {
            Connection conn = DBconnection.ConnectToDB();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String selectFromSQL;
            selectFromSQL = "SELECT * FROM ftv.ticket";
            ResultSet rs = stmt.executeQuery(selectFromSQL);

            while (rs.next()) {
                ticketModel.setTicketId(rs.getInt("ticket_id"));
//                ticketModel.setTicketPrice(rs.getFloat("ticket_price"));
//                ticketModel.setTkUserId(rs.getInt("tk_user_id"));
//                ticketModel.tkFlightNumber(rs.getInt("tk_fl_number"));
//                ticketModel.tkFlightDepartureDate(rs.getDate("tk_fl_dep_date"));

            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ticketList.toString();
    }
}
