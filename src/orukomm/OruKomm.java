package orukomm;

import javax.swing.UIManager;
import orukomm.gui.MainWindow;
import orukomm.data.DataInitializer;

public class OruKomm {
	
	public static void main(String[] args) {
		new DataInitializer();

		try { 
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
		} catch (Exception ex) { 
			ex.printStackTrace(); 
		}
		
		// Create the main window.
		java.awt.EventQueue.invokeLater(() -> {
			new MainWindow();
		});
	}
}
