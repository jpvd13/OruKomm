package orukomm.data.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
