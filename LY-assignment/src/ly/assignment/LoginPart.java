/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ly.assignment;

import java.util.Scanner;
import static ly.assignment.LYAssignment.*;
import static ly.assignment.LoginQuery.*;

/**
 *
 * @author chunfang
 */
public class LoginPart {
    public static char welcome(Scanner sc){
        System.out.println("========== Welcome to Hotel ==========");
        System.out.println("");
        System.out.println("A. Register");
        System.out.println("B. Login as Admin");
        System.out.println("C. Login as Customer");
        System.out.println("D. Forget Password");
        System.out.print("What is your request now? Please select [A-D]: ");
        char i = sc.nextLine().charAt(0);
        i = Character.toLowerCase(i);
        System.out.println("");
        return i;
    }
    
    public static void customerRegister(Scanner sc){
        System.out.println("========== Customer Register Phase ==========");
        System.out.print("Please enter your email: ");
        String email = sc.nextLine();
        System.out.print("Please enter your password: ");
        String password = sc.nextLine();
        user = registerQuery(db,email,password);
        if(user==null){
            System.out.println("Something get wrong");
        }else{
            System.out.println("========== Welcome first time user ==========");
        }
    }
    
    public static void customerLogin(Scanner sc){
        System.out.println("========== Customer Login Phase ==========");
        System.out.print("Please enter your email: ");
        String email = sc.nextLine();
        System.out.print("Please enter your password: ");
        String password = sc.nextLine();
        user = loginQuery(db,email,password);
        if(user==null){
            System.out.println("Something get wrong");
        }else{
            System.out.println("========== Welcome back ==========");
            System.out.println("");
        }
    }
    
    public static void forgetPassword(Scanner sc){
        System.out.println("========== Reset Password Phase ==========");
        System.out.print("Please enter your email: ");
        String email = sc.nextLine();
        System.out.print("Please enter your new password: ");
        String password = sc.nextLine();
        boolean done = forgetPasswordQuery(db,email,password);
        if(done==false){
            System.out.println("Something get wrong");
        }else{
            System.out.println("The password changed successful");
        }
    }
    public static void adminLogin(Scanner sc){
        System.out.println("========== Admin Login Phase ==========");
        System.out.print("Please enter your email: ");
        String email = sc.nextLine();
        System.out.print("Please enter your password: ");
        String password = sc.nextLine();
        admin = loginQueryAdmin(db,email,password);
        if(admin==false){
            System.out.println("Something get wrong");
        }else{
            System.out.println("========== Welcome back Admin ==========");
            System.out.println("");
        }
    }
}
