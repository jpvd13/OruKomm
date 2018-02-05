package orukomm.data.entities;

public class Category implements Entity {

    private int id;
    private int postId;
    private String category;
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    @Override
    public String toString() {
        return category;
    }
    
}
