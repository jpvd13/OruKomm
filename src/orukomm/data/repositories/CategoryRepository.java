package orukomm.data.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import orukomm.data.Database;
import static orukomm.data.Database.close;
import orukomm.data.entities.Category;

public class CategoryRepository implements Repository<Category> {

    private Database db;

    public CategoryRepository() {
        db = Database.getInstance();
    }

    @Override
    public void add(Category category) {
        PreparedStatement ps = null;
        String query = String.format("INSERT INTO category VALUES (null, ?)");

        try {
            ps = db.getConnection().prepareStatement(query);
            ps.setString(1, category.getCategory());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }
    }

    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String query = "SELECT * FROM category";
            ps = db.getConnection().prepareStatement(query);

            rs = ps.executeQuery();
            // Add users to array list.
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategory(rs.getString("category"));
                
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        return categories;
    }

    @Override
    public void remove(Category entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Category entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
