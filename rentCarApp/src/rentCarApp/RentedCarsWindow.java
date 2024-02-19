package rentCarApp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class RentedCarsWindow extends JFrame {

	public RentedCarsWindow() {
		 setTitle("Rented Cars");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setSize(300, 200);
	        getContentPane().setLayout(null);
	        
	        JLabel lblAvailableCars = new JLabel("Rented Cars");
	        lblAvailableCars.setFont(new Font("Tahoma", Font.BOLD, 14));
	        lblAvailableCars.setBounds(102, 11, 117, 17);
	        getContentPane().add(lblAvailableCars);
	        
	        JTextArea textArea = new JTextArea();
	        textArea.setEditable(false);
	        
	        
	        textArea.setText(DBMethods.getRentedCars());
	        
	        JScrollPane scrollPane = new JScrollPane(textArea);
	        scrollPane.setBounds(24, 39, 250, 111);
	        getContentPane().add(scrollPane);
	        
	        
	}
}
