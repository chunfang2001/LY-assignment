/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ly.assignment;

import java.util.HashMap;
import java.util.Scanner;
import static ly.assignment.LYAssignment.db;
import static ly.assignment.LYAssignment.user;
import static ly.assignment.UserQuery.changePasswordQuery;
import static ly.assignment.UserQuery.checkExist;
import static ly.assignment.UserQuery.getAllDetail;
import static ly.assignment.UserQuery.getBooking;
import static ly.assignment.UserQuery.getRoomIDQuery;
import static ly.assignment.UserQuery.getTransactionQuery;
import static ly.assignment.UserQuery.giveReviewQuery;
import static ly.assignment.UserQuery.makeBookingQuery;
import static ly.assignment.UserQuery.makeTransactionQuery;

/**
 *
 * @author chunfang
 */
public class UserPart {
    public static char user_choice(Scanner sc){
        System.out.println("A. Make Booking");
        System.out.println("B. Booking List");
        System.out.println("C. Transaction History");
        System.out.println("D. Give Review");
        System.out.println("E. Logout");
        System.out.println("F. Reset Password");
        System.out.println("G. View room detail");
        System.out.println("H. Make transaction");
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
    public static void giveReview(Scanner sc){
        System.out.println("Give Review to Room");
        System.out.print("Enter room ID: ");
        String roomID = sc.nextLine();
        if(checkExist(db,roomID)){
            System.out.print("Enter Comment: ");
            String comment = sc.nextLine();
            System.out.print("Enter Rating[0-5]: ");
            double rating = sc.nextDouble();
            sc.nextLine();
            giveReviewQuery(db,roomID,comment,rating);
        }else{
            System.out.println("Invalid room ID");
        }
    }
    
    public static void viewRoomDetail(Scanner sc){
        System.out.println("List of rooms");
        System.out.println("");
        HashMap<Integer,String> map = getRoomIDQuery(db);
        System.out.print("pick the number that you want to know: ");
        int c = sc.nextInt();
        sc.nextLine();
        if(map.get(c)==null){
            System.out.println("Something wents wrong");
        }else{
            getAllDetail(db,map.get(c));
        } 
    }
    
    public static void makeTransaction(Scanner sc){
        System.out.println("Make transaction ");
        System.out.print("Please enter your roomID: ");
        String roomID= sc.nextLine();
        if(checkExist(db,roomID)){
            System.out.print("Please enter the number of days: ");
            int number = sc.nextInt();
            sc.nextLine();
            if(makeTransactionQuery(db,number,roomID)){
                System.out.println("transaction successful");
            }else{
                System.out.println("transaction failed");
            }
        }else{
            System.out.println("invalid room id");
        }
    }
    public static void getTransaction(Scanner sc){
        System.out.println("Transaction history");
        System.out.println("");
        getTransactionQuery(db);
    }
}
