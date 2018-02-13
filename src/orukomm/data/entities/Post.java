package orukomm.data.entities;

import java.io.File;
import orukomm.data.FileStorage;

public class Post {
    private int id;
    private int poster;
    private User posterUser;
    private Category categoryCategory;
    private int category;
    private String title;
    private String username;
    private int flow;
    private String date;
    private String description;
    private File attachedFile1;
    private File attachedFile2;
    private File attachedFile3;
    private FileStorage fs = new FileStorage();
    
    public void fileFiller(){
        
        
        
    }
    
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    
    public User getPosterUser() {
        return posterUser;
    }

    public void setPosterUser(User posterUser) {
        this.posterUser = posterUser;
    }
    
    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
    
    public Category getCategoryCategory() {
        return categoryCategory;
    }

    public void setCategoryCategory(Category categoryCategory) {
        this.categoryCategory = categoryCategory;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
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
