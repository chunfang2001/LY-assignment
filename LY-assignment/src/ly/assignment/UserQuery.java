/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ly.assignment;

import java.sql.*;
import java.util.HashMap;
import static ly.assignment.LYAssignment.db;
import static ly.assignment.LYAssignment.user;
/**
 *
 * @author chunfang
 */
public class UserQuery {
    public static boolean changePasswordQuery(Database db, String email,String password){
        try{
            ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from USER where email = '%s'",email,password)).executeQuery();
            if(rs.next()){
                db.getConnection().prepareStatement(String.format("UPDATE USER SET `password` = '%s' WHERE email = '%s'",password,email)).executeUpdate();
                return true;
            }       
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void getBooking(Database db){
        try{
            ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from BOOKING INNER JOIN ROOM ON BOOKING.roomID = ROOM.roomID where userId = '%s'",user.getId())).executeQuery();
            System.out.printf("%3s %10s %12s %12s %5s","id","Room Id","amountOfGuest","amountOfBed","price");
            System.out.println("");
            System.out.println("-------------------------------------------------------");
            while(rs.next()){
                System.out.printf("%3s %10s %12s %12s %5s",rs.getString("id"),rs.getString("roomID"),rs.getString("amountOfGuest"),rs.getString("amountOfBed"),rs.getString("price"));
                System.out.println("");
            }
            System.out.println("-------------------------------------------------------");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static HashMap<Integer,String> getRoomIDQuery(Database db){
        HashMap<Integer,String> map = new HashMap<>();
        try{
            ResultSet rs = db.getConnection().prepareStatement("Select * from ROOM").executeQuery();
            int count = 1;
            System.out.printf("%3s %10s","bil","Room Id");
            System.out.println("");
            System.out.println("-----------------------------------");
            while(rs.next()){
                map.put(count,rs.getString("roomID"));
                System.out.printf("%3d %10s",count,rs.getString("roomID"));
                System.out.println("");
                count++;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return map;
    }
    public static boolean makeBookingQuery(Database db,String roomID){
        try{
            db.getConnection().prepareStatement(String.format("INSERT INTO BOOKING(`roomID`,`userID`) values('%s','%s')",roomID,user.getId())).executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}
