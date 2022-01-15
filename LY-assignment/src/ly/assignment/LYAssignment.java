/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ly.assignment;

import java.util.HashMap;
import java.util.Scanner;
import static ly.assignment.AdminPart.*;
import static ly.assignment.AdminQuery.*;
import static ly.assignment.LoginPart.*;
import static ly.assignment.UserQuery.*;

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
                char choice = user_choice(sc);
                switch(choice){
                    case 'a':
                        makeBooking();
                        break;
                    case 'b':
                        showBooking();
                        break;
                    case 'c':
                        break;
                    case 'd':
                        user = null;
                        System.out.println("log out successfully");
                        break;
                    case 'e':
                        changePassword(sc);
                        break;
                    default:
                        break;
                }
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
    public static char user_choice(Scanner sc){
        System.out.println("A. Make Booking");
        System.out.println("B. Booking List");
        System.out.println("C. Transaction History");
        System.out.println("D. Logout");
        System.out.println("E. Reset Password");
        System.out.print("What is your request now? Please select [A-D]: ");
        char i = sc.nextLine().charAt(0);
        i = Character.toLowerCase(i);
        System.out.println("");
        return i;
    }
    public static void changePassword(Scanner sc){
        System.out.println("========== change Password Phase ==========");
        System.out.print("Please enter your new password: ");
        String password = sc.nextLine();
        boolean done = changePasswordQuery(db,user.getEmail(),password);
        if(done==false){
            System.out.println("Something get wrong");
        }else{
            System.out.println("The password changed successful");
        }
    }
    public static void showBooking(){
        getBooking(db);
    }
    public static void makeBooking(){
        Scanner sc = new Scanner(System.in);
        System.out.println("List of rooms");
        System.out.println("");
        HashMap<Integer,String> map = getRoomIDQuery(db);
        System.out.print("pick the number in bil to make booking: ");
        int c = sc.nextInt();
        if(map.get(c)==null){
            System.out.println("Something wents wrong");
        }else{
            boolean a = makeBookingQuery(db,map.get(c));
            if(a){
                System.out.println("booking made successfully");
            }else{
                System.out.println("booking fails to be made");
            }
        } 
    }
}
