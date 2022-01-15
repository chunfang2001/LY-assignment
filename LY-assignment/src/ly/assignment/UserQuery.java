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
    public static boolean giveReviewQuery(Database db, String roomID, String comment, double rating){
        try{
            db.getConnection().prepareStatement(String.format("INSERT INTO REVIEW(`roomID`,`userid`,`comment`,`rating`) values('%s','%s','%s','%f')",roomID,user.getId(),comment,rating)).executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    public static boolean checkExist(Database db, String roomID){
        try{
           ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from ROOM where roomID = '%s'",roomID)).executeQuery();
           if(rs.next()){
               return true;
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void getAllDetail(Database db, String roomID){
        try{
            ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from ROOM where roomID = '%s'",roomID)).executeQuery();
            if(rs.next()){
                System.out.println("RoomID: "+rs.getString("roomID"));
                System.out.println("amountOfGuest: "+rs.getInt("amountOfGuest"));
                System.out.println("amountOfBed: "+rs.getInt("amountOfBed"));
                System.out.println("price: "+rs.getInt("price"));
                System.out.println("");
            }
            ResultSet rs2 = db.getConnection().prepareStatement(String.format("Select * from REVIEW where roomID = '%s'",roomID)).executeQuery();
            System.out.println("List of Review");
            System.out.println("");
            double sum =0;
            int count = 0;
            while(rs2.next()){
                System.out.println("Review "+(count+1));
                System.out.println("Rating "+(count+1)+": "+rs2.getDouble("rating"));
                System.out.println("Comment "+(count+1)+": "+rs2.getString("comment"));
                sum+= rs2.getDouble("rating");
                System.out.println("");
                count++;
            }
            System.out.println("Average Rating: "+sum/count);
            System.out.println("");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static boolean makeTransactionQuery(Database db,int days,String roomID){
        try{
            ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from ROOM where roomID = '%s'",roomID)).executeQuery();
            if(rs.next()){
                int price = rs.getInt("price");
                try{
                    db.getConnection().prepareStatement(String.format("Insert into TRANSACTION(`days`,`amountPaid`,`roomPrice`,`successful`,`userID`) values('%d','%d','%d','%d','%d')",days,days*price,price,1,user.getId())).executeUpdate();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static void getTransactionQuery(Database db){
        try{
            ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from TRANSACTION where userID='%d'",user.getId())).executeQuery();
            int count = 1;
            while(rs.next()){
                System.out.println("Transaction "+count);
                System.out.println("Room Price: "+rs.getString("roomPrice"));
                System.out.println("Days: "+rs.getString("days"));
                System.out.println("Amount Paid: "+rs.getString("amountPaid"));
                System.out.println("");
                count++;
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
