/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ly.assignment;

import java.util.Scanner;
import static ly.assignment.AdminQuery.*;
import static ly.assignment.LoginPart.*;

/**
 *
 * @author chunfang
 */
public class LYAssignment {
    public static Database db;
    public static User user;
    public static boolean admin = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        init();
        while(true){
            if(!admin&&user==null){
                char welcome_choice = welcome(sc);
                    switch(welcome_choice){
                        case 'a':
                            customerRegister(sc);
                            break;
                        case 'b':
                            adminLogin(sc);
                            break;
                        case 'c':
                            customerLogin(sc);
                            break;
                        case 'd':
                            forgetPassword(sc);
                            break;
                        default:
                            break;
                    }
            }
            if(admin){
                char choice = admin_choice(sc);
                switch(choice){
                        case 'a':
                            addRoom(sc);
                            break;
                        case 'b':
                            editRoom(sc);
                            break;
                        case 'c':
                            showRoomQuery(db);
                            break;
                        default:
                            break;
                    }
            }
            if(user!=null){
                
            }
        }
    }
    
    public static void init(){
        try{
            db = new Database();
            db.connect();
        }catch(Exception e){
            System.out.println("connection error");
        }
        
    }
    
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
