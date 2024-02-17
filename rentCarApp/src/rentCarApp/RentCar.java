package rentCarApp;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class RentCar {

    private JFrame frame;
    private JComboBox<String> comboBoxCars;
    private JComboBox<Customer> comboBoxCustomers;
    private JSpinner spinnerRentalDays;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RentCar window = new RentCar();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public RentCar() {
        initialize();
        loadAvailableCars();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblSelectCar = new JLabel("Select Car:");
        lblSelectCar.setBounds(30, 30, 80, 14);
        frame.getContentPane().add(lblSelectCar);
        
        comboBoxCars = new JComboBox<>();
        comboBoxCars.setBounds(120, 27, 200, 20);
        frame.getContentPane().add(comboBoxCars);
        
        comboBoxCustomers = new JComboBox<>();
        comboBoxCustomers.setBounds(120, 55, 200, 20);
        frame.getContentPane().add(comboBoxCustomers);
        loadAvailableCustomers();
        
        JButton btnRentCar = new JButton("Rent Car");
        btnRentCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rentCarToCustomer();
            }
        });
        btnRentCar.setBounds(87, 114, 147, 23);
        frame.getContentPane().add(btnRentCar);
        
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
            }
        });
        btnClose.setBounds(261, 114, 89, 23);
        
        frame.getContentPane().add(btnClose);
        

        spinnerRentalDays = new JSpinner();
        spinnerRentalDays.setModel(new SpinnerNumberModel(1, 1, 30, 1)); // Range from 1 to 30
        spinnerRentalDays.setBounds(120, 90, 50, 20);
        frame.getContentPane().add(spinnerRentalDays);
    }

    // Method to load available cars for rent
    private void loadAvailableCars() {
        List<String> availableCars = DBMethods.loadAvailableCars();
        for (String car : availableCars) {
            comboBoxCars.addItem(car);
        }
    }
 // Inside loadAvailableCustomers() method
    private void loadAvailableCustomers() {
        List<Customer> availableCustomers = DBMethods.loadAvailableCustomers();
        for (Customer customer : availableCustomers) {
            comboBoxCustomers.addItem(customer);
        }
    }

    // Method to rent a car to a customer
    private void rentCarToCustomer() {
        Customer selectedCustomer = (Customer) comboBoxCustomers.getSelectedItem();
        int rentalDays = (int) spinnerRentalDays.getValue();
        if (selectedCustomer != null) {
            String selectedCar = (String) comboBoxCars.getSelectedItem();
            String[] parts = selectedCar.split(", ");
            int carId = Integer.parseInt(parts[0].split(": ")[1]);
            int customerId = selectedCustomer.getId(); // Get customer ID from selectedCustomer object
            DBMethods.rentCarToCustomer(customerId, carId, rentalDays);
            JOptionPane.showMessageDialog(frame, "Car rented successfully.");
            frame.dispose(); // Close the window after renting the car
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a customer.");
        }
    }

    public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
    }
