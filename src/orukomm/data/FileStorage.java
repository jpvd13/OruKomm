package orukomm.data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Math.toIntExact;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import orukomm.data.entities.User;
import orukomm.data.repositories.PostRepository;

public class FileStorage {

    private Connection connection;
    private final String dbDriver = "com.mysql.jdbc.Driver";
    private final String connectionString = "jdbc:mysql://localhost:3306/oru_komm?autoReconnect=true&useSSL=false";

    private final String dbUser = "root";
    private final String dbPassword = "masterkey";

    public User loggedInUser = new User();
    private PostRepository pr = new PostRepository();
    
   
    int postId;

    public Connection connect() {
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;

    }

    /**
     * Read the file and returns the byte array
     *
     * @param file
     * @return the bytes of the file
     */
    public byte[] readFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }

    /**
     * Update picture for a specific material
     *
     * @param postId
     * @param filename
     */
    public int selectMax() {
        String query = "SELECT MAX(id) FROM posts";
        try (Connection conn = connect();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            if (Database.fetchedRows(rs) == 1) {
                postId = rs.getInt("MAX(id)");
                System.out.println(postId);

            }
        } catch (SQLException ex) {
            Logger.getLogger(FileStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return postId;
    }

    public void insertFile(String file) {
        // update sql
        
        String fileName = file.substring(file.lastIndexOf("\\") + 1);
        selectMax();
        String updateSQL = "INSERT INTO attachments values(null, ?, ?, ?)";

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setInt(1, postId);
            pstmt.setBytes(2, FileStorage.this.readFile(file));
            pstmt.setString(3, fileName);

            pstmt.executeUpdate();
            System.out.println("Stored file as "+fileName+" in attachments column.");            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * read the file and insert into the attachments master table
     *
     * @param post_id
     * @param fileName
     */
    public void selectFile(int post_id, String url) {
        // insert into db
        String selectSQL = "SELECT file, name FROM attachments WHERE post_id=?";
        ResultSet rs = null;
        FileOutputStream fos = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        File file = null;

        try {
            conn = connect();
            pstmt = conn.prepareStatement(selectSQL);
            pstmt.setInt(1, post_id);
            
            rs = pstmt.executeQuery();
            rs.next();
            Blob size = rs.getBlob("file");
            long blob = size.length();
            int blobSize = toIntExact(blob);
        
            String fileName = rs.getString("name");
            System.out.println(fileName);
            
            rs.previous();
            // write binary stream into file
            file = new File(url + fileName);
            fos = new FileOutputStream(file);           
            
            System.out.println(blobSize);

            System.out.println("Writing BLOB to file " + file.getAbsolutePath());
            while (rs.next()) {
                InputStream input = rs.getBinaryStream("file");
                byte[] buffer = new byte[blobSize];
                while (input.read(buffer) > 0) {
                    fos.write(buffer);
                    
                    
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
                if (fos != null) {
                    fos.close();
                }

            } catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

    /* public void getBlobImage(int attachmentId) throws SQLException, IOException {
        String selectSQL = "SELECT file FROM attachments WHERE id=?";

        PreparedStatement pstmt = connection.prepareStatement(selectSQL);
        ResultSet rs = pstmt.executeQuery(selectSQL);
        pstmt.setInt(1, attachmentId);

        java.sql.Blob blob = rs.getBlob("files");

        InputStream in = blob.getBinaryStream();

        BufferedImage image = ImageIO.read(in);

    } */
    public void insertPost(int userId, String title, String content, int category, int flow, String date) {

        String query = "INSERT INTO posts values(null, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, title);
            pstmt.setString(3, content);
            pstmt.setInt(4, category);
            pstmt.setInt(5, flow);
            pstmt.setString(6, date);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FileStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void chooseDirectory() {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose destination");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            selectFile(2, chooser.getSelectedFile().toString()+"\\");
        } else {
            System.out.println("No Selection ");
        }
    }

    public ArrayList<String> getFileName() {
        FileStorage fs = new FileStorage();
                    

        ArrayList<String> fileNames = new ArrayList<String>();

        String selectSQL = ("SELECT name FROM attachments WHERE post_id = ?");
        try (Connection conn = fs.connect();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, 2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){

                fileNames.add(rs.getString("name"));               
                return fileNames;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return fileNames;
    }

}
