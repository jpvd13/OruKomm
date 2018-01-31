/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.data.entities;

/**
 *
 * @author Ludvig
 */
public class Post {
    private int id;
    private String title;
    private String username;
    private String date;
    
    

    public String getTitle() {
        return title;
    }

    public void setTitel(String titel) {
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
