/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ly.assignment;

import java.util.Scanner;
import static ly.assignment.AdminQuery.addRoomQuery;
import static ly.assignment.AdminQuery.editRoomDetail;
import static ly.assignment.AdminQuery.showRoomQuery;
import static ly.assignment.LYAssignment.db;

/**
 *
 * @author chunfang
 */
public class AdminPart {
    public static char admin_choice(Scanner sc){
        System.out.println("A. Add new room");
        System.out.println("B. Edit room details");
        System.out.println("C. Show all rooms");
        System.out.print("What is your request now? Please select [A-C]: ");
        char i = sc.nextLine().charAt(0);
        i = Character.toLowerCase(i);
        System.out.println("");
        return i;
    }
    
    public static void addRoom(Scanner sc){
        System.out.print("RoomID:");
        String roomID = sc.nextLine();
        System.out.print("amount of guest:");
        int amountOfGuest = sc.nextInt();
        System.out.print("amount of bed:");
        int amountOfBed = sc.nextInt();
        System.out.print("Price: ");
        int price = sc.nextInt();
        sc.nextLine();
        addRoomQuery(db, roomID, amountOfGuest,amountOfBed,price);
    }
    
    public static void editRoom(Scanner sc){
        showRoomQuery(db);
        System.out.print("Room ID:");
        String roomID = sc.nextLine();
        editRoomDetail(db,roomID);
    }
}
