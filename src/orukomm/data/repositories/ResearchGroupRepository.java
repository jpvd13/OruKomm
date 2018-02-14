/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.data.repositories;
import java.sql.PreparedStatement;
import orukomm.data.Database;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static orukomm.data.Database.close;
import orukomm.data.entities.ResearchGroup;



public class ResearchGroupRepository {
    
    private Database db;
    
    public ResearchGroupRepository() {
        db = Database.getInstance();
    }
    
    public void addResearchGroup(ResearchGroup researchGroup){
        
      PreparedStatement ps = null;
        String query = String.format("INSERT INTO research_group (name, group_admin) VALUES (?, 1)");

        try {
            ps = db.getConnection().prepareStatement(query);
            ps.setString(1, researchGroup.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ResearchGroupRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
    }
        
    }
}
    
    
        
        
   
        
  
