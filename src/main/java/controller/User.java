package controller;

import dbconnection.DBconnection;
import model.UserModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);

    public static String getUserDetails() {
        Statement stmt = null;
        UserModel userModel = new UserModel();
        Map users = new HashMap();

        try {
            Connection conn = DBconnection.ConnectToDB();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String selectFromSQL;
            selectFromSQL = "SELECT * FROM ftv.user";
            ResultSet rs = stmt.executeQuery(selectFromSQL);

            while (rs.next()) {
                userModel.setUserName(rs.getString("user_name"));
                userModel.setUserId(rs.getInt("user_id"));
                users.putIfAbsent(userModel.getUserId(), userModel.getUserName());
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users.toString();
    }

//    public void addUser() {
//        System.out.println("Add user");
//        UserModel userModel = new UserModel();
//
//        userModel.setUserId(scanner.nextInt());
//        userModel.setUserName(scanner.nextLine());
//
//    }

}
