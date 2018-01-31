/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import orukomm.data.entities.User;

/**
 *
 * @author Johan
 */
public class FileStorage {

    private Connection connection;
    private final String dbDriver = "com.mysql.jdbc.Driver";
    private final String connectionString = "jdbc:mysql://localhost:3306/oru_komm?autoReconnect=true&useSSL=false";

    private final String dbUser = "root";
    private final String dbPassword = "admin";

    public User loggedInUser = new User();

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
    public void insertFile(int postId, String filename) {
        // update sql
        String updateSQL = "INSERT INTO attachments values(?, ?, ?)";

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            // set parameters
            int id_autoUp = 1;
            postId = 1;

            pstmt.setInt(1, id_autoUp);
            pstmt.setInt(2, postId);
            pstmt.setBytes(3, FileStorage.this.readFile(filename));
            pstmt.executeUpdate();
            System.out.println("Stored file as BLOB in attachments column.");

            id_autoUp++;
            postId++; //Ska bytas ut

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * read the picture file and insert into the attachments master table
     *
     * @param post_id
     * @param fileName
     */
    public void selectFile(int post_id, String fileName) {
        // insert into db
        String selectSQL = "SELECT file FROM attachments WHERE id=?";
        ResultSet rs = null;
        FileOutputStream fos = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connect();
            pstmt = conn.prepareStatement(selectSQL);
            pstmt.setInt(1, post_id);
            rs = pstmt.executeQuery();

            // write binary stream into file
            File file = new File(fileName);
            fos = new FileOutputStream(file);

            System.out.println("Writing BLOB to file " + file.getAbsolutePath());
            while (rs.next()) {
                InputStream input = rs.getBinaryStream("picture");
                byte[] buffer = new byte[1024];
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
    

    public void getBlob(int attachmentId) throws SQLException, IOException {
        String selectSQL = "SELECT file FROM attachments WHERE id=?";
        
        PreparedStatement pstmt = connection.prepareStatement(selectSQL);
        ResultSet rs = pstmt.executeQuery(selectSQL);
        pstmt.setInt(1, attachmentId);
        
        java.sql.Blob blob = rs.getBlob("files");
        
        InputStream in = blob.getBinaryStream();
        
        BufferedImage image = ImageIO.read(in);
        
    }

}
