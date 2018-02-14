/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.data.entities;


/**
 *
 * @author frida
 */

public class ResearchGroup {
    
    private int id;
    private String name;
    private int groupAdmin;
    
    public int getId() {
        
        return this.id;
        
    }
     
    public void setId() {
        
        this.id = id;
    }
    
    public String getName(){
        
        return this.name;
        
    }
    
    public void setName(String name){
        
        this.name = name;
    }
    
    public int getGroupAdmin() {
        
        return this.groupAdmin;
    }
    
    public void setGroupAdmin(){
        
        this.groupAdmin = groupAdmin;
    }

    
}
