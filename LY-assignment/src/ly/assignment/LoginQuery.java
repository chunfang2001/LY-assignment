/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ly.assignment;

import java.sql.*;
/**
 *
 * @author chunfang
 */
public class LoginQuery {
    public static User registerQuery(Database db, String email,String password){
        try{
            ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from USER where email = '%s'",email)).executeQuery();
            if(rs.next()){
                return null;
            }else{
                db.getConnection().prepareStatement(String.format("Insert into USER(`email`,`password`) VALUES ('%s','%s')",email,password)).executeUpdate();
            }
            rs = db.getConnection().prepareStatement(String.format("Select * from USER where email = '%s'",email)).executeQuery();
            if(rs.next()){
                User user = new User(email,rs.getInt("id"));
                return user;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static User loginQuery(Database db, String email,String password){
        try{
            ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from USER where email = '%s' AND password ='%s'",email,password)).executeQuery();
            if(rs.next()){
                User user = new User(email,rs.getInt("id"));
                return user;
            }       
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static boolean forgetPasswordQuery(Database db, String email,String password){
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
    public static boolean loginQueryAdmin(Database db, String email,String password){
        try{
            ResultSet rs = db.getConnection().prepareStatement(String.format("Select * from admin where email = '%s' AND password ='%s'",email,password)).executeQuery();
            if(rs.next()){
                return true;
            }       
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
