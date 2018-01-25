package orukomm;

import orukomm.data.DataInitializer;

public class OruKomm {

    public static void main(String[] args) {
		DataInitializer di = new DataInitializer();
        
		// Create and display the main window.
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
	}
    
}
