/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.data.entities;

import java.io.File;
import orukomm.data.FileStorage;

/**
 *
 * @author Ludvig
 */
public class Post {
    private int id;
    private String title;
    private String username;
    private String date;
    private String description;
    private File attachedFile1;
    private File attachedFile2;
    private File attachedFile3;
    private FileStorage fs = new FileStorage();
    
    public void fileFiller(){
        
        
        
    }
    
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String titel) {
        this.title = titel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }
}
