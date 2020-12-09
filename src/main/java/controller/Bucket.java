package controller;

import dbconnection.DBconnection;
import model.BucketModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bucket {
    Scanner scanner = new Scanner(System.in);

    public static String getTicketDetails() {
        Statement stmt = null;
        BucketModel bucketModel = new BucketModel();
        List<BucketModel> bucketList = new ArrayList<>();

        try {
            Connection conn = DBconnection.ConnectToDB();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String selectFromSQL;
            selectFromSQL = "SELECT * FROM ftv.ticket";
            ResultSet rs = stmt.executeQuery(selectFromSQL);

            while (rs.next()) {
                bucketModel.setPrice(rs.getFloat("price"));
                bucketModel.setTotalPerType(rs.getInt("total_per_type"));
                bucketModel.setAvailablePerType(rs.getInt("available_per_type"));

                //bucketList = bucketList.stream().collect(Collectors.toList(bucketModel));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bucketList.toString();
    }
}
