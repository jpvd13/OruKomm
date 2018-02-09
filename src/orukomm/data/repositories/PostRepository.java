package orukomm.data.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


import orukomm.data.DataInitializer;
import orukomm.data.Database;
import static orukomm.data.Database.close;
import orukomm.data.entities.Post;

public class PostRepository {
    private static Database db;

    public PostRepository() {

        db = Database.getInstance();

    }

    public Post getById(int id) {
        Post post = new Post();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = String.format("SELECT * FROM posts WHERE id = '%d'", id); // måste ändras när tabellen är skapad. 
                                                                                //ändrade från user till posts. 

        try {
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (Database.fetchedRows(rs) == 1) {
                // Username exists.
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("titel"));
                post.setUsername(rs.getString("poster"));
                post.setDate(rs.getString("date"));
                
                System.out.println(rs.getInt("titel"));                
                System.out.println(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        //} finally {
        //    close(rs, ps, null);
        }

        return post;
    }

    public ArrayList<Post> fillListFormal() {
        ArrayList<Post> postList = new ArrayList<>();
        Post post;

        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = String.format("SELECT username, title, date, description, flow, posts.id  FROM posts, user WHERE user.id = poster AND flow = 0 ORDER BY date DESC");

        try {
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
 
            while (rs.next()) {
                
                post = new Post();
                post.setUsername(rs.getString("username"));
                post.setTitle(rs.getString("title"));                
                post.setDate(rs.getString("date"));
                post.setDescription(rs.getString("description"));
                post.setFlow(rs.getInt("flow"));
                post.setId(rs.getInt("id"));
                
                        
                
                postList.add(post);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "databasfel");
        }
        finally {
            close(rs, ps, null);
        }
        return postList;

    }
        public ArrayList<Post> fillListInformal() {
        ArrayList<Post> postList = new ArrayList<>();
        Post post;

        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = String.format("SELECT username, title, date, description, flow, posts.id  FROM posts, user WHERE user.id = poster AND flow = 1 ORDER BY date DESC");

        try {
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
 
            while (rs.next()) {
                
                post = new Post();
                post.setUsername(rs.getString("username"));
                post.setTitle(rs.getString("title"));                
                post.setDate(rs.getString("date"));
                post.setDescription(rs.getString("description"));
                post.setFlow(rs.getInt("flow"));
                post.setId(rs.getInt("id"));
                
                        
                
                postList.add(post);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "databasfel");
        }
        //finally {
        //    close(rs, ps, null);
       // }
        return postList;

    }
        
    public void updatePost(int id, String title, String description)
        {
            PreparedStatement ps = null;
            
            String query = String.format("UPDATE posts SET title = ?, description = ? WHERE id = ?");
            
            try {
                
            ps = db.getConnection().prepareStatement(query);
            
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, id);
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }
            
        }
        
}
