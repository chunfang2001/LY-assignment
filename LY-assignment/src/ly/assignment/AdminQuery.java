/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ly.assignment;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author chunfang
 */
public class AdminQuery {
     public static void showRoomQuery(Database db){
         try{
             ResultSet rs = db.getConnection().prepareStatement("Select * from ROOM").executeQuery();
             int count = 1;
             System.out.printf("%3s %10s","bil","Room Id");
             System.out.println("");
             System.out.println("-----------------------------------");
             while(rs.next()){
                 System.out.printf("%3d %10s",count,rs.getString("roomID"));
                 System.out.println("");
                 count++;
             }
            System.out.println("-----------------------------------");

         }catch(Exception e){
             System.out.println(e.getMessage());
         }
     }
     public static boolean addRoomQuery(Database db, String roomID, int amountOfGuest,int amountOfBed, int price){
         try{
            db.getConnection().prepareStatement(String.format("INSERT into ROOM (`roomID`,`amountOfGuest`,`amountOfBed`,`price`) VALUES ('%s','%d','%d','%d')", roomID,amountOfGuest,amountOfBed,price)).executeUpdate();
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
         return true;
     }
     public static boolean editRoomDetail(Database db, String roomID){
         Scanner sc = new Scanner(System.in);
         try{
            ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from ROOM where roomID = '%s'",roomID)).executeQuery();
            if(rs.next()){
                System.out.println("RoomID: "+rs.getString("roomID"));
                System.out.println("amountOfGuest: "+rs.getInt("amountOfGuest"));
                System.out.println("amountOfBed: "+rs.getInt("amountOfBed"));
                System.out.println("price: "+rs.getInt("price"));
                
                System.out.println("Which one you want to edit: ");
                System.out.println("A. amountOfGuest");
                System.out.println("B. amountOfBed");
                System.out.println("C. price");
                
                System.out.print("What is your request now? Please select [A-C]: ");
                System.out.println("");
                char i = sc.nextLine().charAt(0);
                i = Character.toLowerCase(i);
                switch(i){
                    case 'a':
                        System.out.print("amountOfGuest: ");
                        int amountOfGuest = sc.nextInt();
                        try{
                            db.getConnection().prepareStatement(String.format("UPDATE ROOM SET `amountOfGuest` = '%d' WHERE roomID = '%s'",amountOfGuest,roomID)).executeUpdate();   
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 'b':
                        System.out.print("amountOfBed: ");
                        int amountOfBed = sc.nextInt();
                        try{
                            db.getConnection().prepareStatement(String.format("UPDATE ROOM SET `amountOfBed` = '%d' WHERE roomID = '%s'",amountOfBed,roomID)).executeUpdate();   
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 'c':
                        System.out.print("price: ");
                        int price = sc.nextInt();
                        try{
                            db.getConnection().prepareStatement(String.format("UPDATE ROOM SET `price` = '%d' WHERE roomID = '%s'",price,roomID)).executeUpdate();   
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    default :
                        return false;
                }
                return true;
            }else{
                return false;
            }
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
         return false;
     }
}
