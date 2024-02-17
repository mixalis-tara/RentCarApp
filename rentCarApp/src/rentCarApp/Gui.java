package rentCarApp;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class Gui extends JFrame {
    public Gui() {
        initialize();
    }

    private void initialize() {
        setTitle("Car Rental Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 517, 334);
        getContentPane().setLayout(null);
        
        JLabel lblWelcomeToCar = new JLabel("Welcome to Car Rental Application");
        lblWelcomeToCar.setBounds(116, 11, 210, 14);
        getContentPane().add(lblWelcomeToCar);
        
        JButton btnRenetdCars = new JButton("Rented Cars");
        btnRenetdCars.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showRentedCarsWindow();
        	}
        });
        btnRenetdCars.setBounds(156, 124, 147, 23);
        getContentPane().add(btnRenetdCars);
        
        JButton btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
     // Open AddCustomer window
        AddCustomer addCustomer = new AddCustomer();
        addCustomer.getFrame().setVisible(true);
            }
       });
        
        btnAddCustomer.setBounds(156, 79, 147, 23);
        getContentPane().add(btnAddCustomer);
        
        JButton btnAddCar = new JButton("Add Car");
        btnAddCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open AddCar window
                AddCar addCarWindow = new AddCar();
                addCarWindow.getFrame().setVisible(true);
            }
        });
        btnAddCar.setBounds(156, 36, 147, 23);
        getContentPane().add(btnAddCar);
        
        JButton btnRentCar = new JButton("Rent Car");
        btnRentCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open rentCar window
            	RentCar rentCar = new RentCar();
            	rentCar.getFrame().setVisible(true);
            }
        });
        btnRentCar.setBounds(156, 158, 147, 23);
        getContentPane().add(btnRentCar);
        
        JButton btnSearch = new JButton("Search Customer/Car");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                // Open rentCar window
            	SearchCustCar searchCustCar = new SearchCustCar();
            	searchCustCar.getFrame().setVisible(true);
            }
        });
        btnSearch.setBounds(144, 203, 167, 23);
        getContentPane().add(btnSearch);
    
    
    JButton btnDeleteCustCar = new JButton("Delete Customer/Car");
    btnDeleteCustCar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Open DeleteCustCar window
            DeleteCustCar deleteCustCar = new DeleteCustCar();
            deleteCustCar.getFrame().setVisible(true);
        }
    });
    btnDeleteCustCar.setBounds(144, 248, 167, 23);
    getContentPane().add(btnDeleteCustCar);
}
    
    private void showRentedCarsWindow() {
    	RentedCarsWindow window = new RentedCarsWindow();
    	window.setVisible(true);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Gui frame = new Gui();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
