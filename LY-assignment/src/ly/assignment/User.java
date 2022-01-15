/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ly.assignment;

/**
 *
 * @author chunfang
 */
public class User {
    private String email;
    private int id;
    
    public User(){
        
    }
    public User(String email, int id){
        this.email = email;
        this.id = id;
    }
    
   
    public String getEmail(){
        return email;
    }
    public int getId(){
        return id;
    }
}
