package orukomm.data.repositories;

import java.sql.Date;
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

import orukomm.data.entities.Attachments;

import orukomm.data.entities.Category;

import orukomm.data.entities.Post;
import orukomm.data.entities.User;

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
        } finally {
            close(rs, ps, null);
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
        //finally {
        //    close(rs, ps, null);
        // }
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

    public void updatePost(int post_id, String title, String description) {
        PreparedStatement ps = null;

        String query = String.format("UPDATE posts SET title = ?, description = ? WHERE id = ?");

        try {

            ps = db.getConnection().prepareStatement(query);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, post_id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

    }

    public ArrayList<Post> fillCategoriesListInformal() {
        ArrayList<Post> postList = new ArrayList<>();
        Post post;

        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = String.format("select username, title, `date`, description, flow, posts.id from posts\n"
                + "join `user` on `user`.id = poster\n"
                + "join category on category.id = posts.category\n"
                + "where category.id not in(select user_category.category_id\n"
                + "from user_category where user_category.user_id = poster)\n"
                + "and flow = 1 ORDER BY date DESC;");

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
    public void deleteFile(int id) {
        PreparedStatement ps = null;

        String query = String.format("DELETE from attachments WHERE post_id = ?");

        try {

            ps = db.getConnection().prepareStatement(query);


            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

    }
    
    public void deleteSingleFile(int id, String name) {
        PreparedStatement ps = null;

        String query = String.format("DELETE from attachments WHERE post_id = ? AND name = ?");

        try {

            ps = db.getConnection().prepareStatement(query);


            ps.setInt(1, id);
            ps.setString(2, name);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

    }
    
    public void deletePicture(int id) {
        PreparedStatement ps = null;

        String query = String.format("DELETE from attachments WHERE post_id = ? AND Type = 1");

        try {

            ps = db.getConnection().prepareStatement(query);


            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

    }

            public void DeleteAllFiles(int id) {
        PreparedStatement ps = null;

        String query = String.format("DELETE from attachments WHERE post_id = ?");

        try {

            ps = db.getConnection().prepareStatement(query);


            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

    }
            
     public void DeletePost(int id) {
        PreparedStatement ps = null;

        String query = String.format("DELETE from posts WHERE id = ?");

        try {

            ps = db.getConnection().prepareStatement(query);


            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

    }



    public ArrayList<Post> fillCategoriesListFormal() {
        ArrayList<Post> postList = new ArrayList<>();
        Post post;

        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = String.format("select username, title, `date`, description, flow, posts.id from posts\n"
                + "join `user` on `user`.id = poster\n"
                + "join category on category.id = posts.category\n"
                + "where category.id not in(select user_category.category_id\n"
                + "from user_category where user_category.user_id = poster)\n"
                + "and flow = 0 ORDER BY date DESC;");

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

        return postList;
    }

    /*
     * Returns all posts, including poster and category, since @param date.
     */
    public ArrayList<Post> getPostsSince(String date) {
        ArrayList<Post> posts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = String.format("SELECT * FROM posts WHERE date > '%s'", date.toString());

        try {
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setPoster(rs.getInt("poster"));
                post.setTitle(rs.getString("title"));
                post.setDescription(rs.getString("description"));
                post.setCategory(rs.getInt("category"));
                post.setFlow(rs.getInt("flow"));
                post.setDate(rs.getString("date"));

                // Get poster object for post.
                String posterQuery = String.format("SELECT * FROM user WHERE id = %d", post.getPoster());
                PreparedStatement psPoster = null;
                ResultSet rsPoster = null;
                
                psPoster = db.getConnection().prepareStatement(posterQuery);
                rsPoster = psPoster.executeQuery();
                
                while (rsPoster.next()) {
                    User poster = new User();
                    // This method will only be called from a context (email cron job) where only the first name and
                    // surname will be used.
                    poster.setFirstName(rsPoster.getString("first_name"));
                    poster.setSurname(rsPoster.getString("surname"));
                    
                    post.setPosterUser(poster);
                }
                
                // Get category of post.
                PreparedStatement psCategory = null;
                ResultSet rsCategory = null;
                String queryCat = String.format("SELECT * from category WHERE id = %d", post.getCategory());

                psCategory = db.getConnection().prepareStatement(queryCat);
                rsCategory = psCategory.executeQuery();
                
                while (rsCategory.next()) {
                    Category category = new Category();
                    category.setId(rsCategory.getInt("id"));
                    category.setCategory(rsCategory.getString("category"));
                    
                    post.setCategoryCategory(category);
                }
                
                posts.add(post);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        return posts;
    }
    
    public ArrayList<Attachments> fillAttachments(int post_id)
    {
    ArrayList<Attachments> attachments = new ArrayList<>();
        Attachments attachment;

        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = String.format("SELECT type FROM attachments WHERE post_id = ?");

        try {
            ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, post_id);
            rs = ps.executeQuery();

            while (rs.next()) {

                attachment = new Attachments();
                attachment.setType(rs.getInt("type"));

                attachments.add(attachment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "databasfel");
        }
        
        return attachments;
    }
    
   public boolean hasPictureFile(int post_id)
   {
       boolean found = false;
       
       for (Attachments a : fillAttachments(post_id))
       {
        if (a.getType() == 1)
        {
            
         found = true;   
        }
           
       }
return found;
} 
}
