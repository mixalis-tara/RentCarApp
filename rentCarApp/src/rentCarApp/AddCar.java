package rentCarApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCar {

    private JFrame frame;
    private JTextField textFieldModel;
    private JTextField textFieldCategory;
    private JTextField textFieldDailyCost;
    private JTextField textFieldCubicCapacity;
    private JTextField textFieldSeat;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddCar window = new AddCar();
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AddCar() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setFrame(new JFrame());
        getFrame().setBounds(100, 100, 450, 300);
        getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getFrame().getContentPane().setLayout(null);
        
        JLabel lblModel = new JLabel("Model:");
        lblModel.setBounds(30, 30, 60, 14);
        getFrame().getContentPane().add(lblModel);
        
        textFieldModel = new JTextField();
        textFieldModel.setBounds(100, 27, 150, 20);
        getFrame().getContentPane().add(textFieldModel);
        textFieldModel.setColumns(10);
        
        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setBounds(30, 70, 60, 14);
        getFrame().getContentPane().add(lblCategory);
        
        textFieldCategory = new JTextField();
        textFieldCategory.setBounds(100, 67, 150, 20);
        getFrame().getContentPane().add(textFieldCategory);
        textFieldCategory.setColumns(10);
        
        JLabel lblDailyCost = new JLabel("Daily Cost:");
        lblDailyCost.setBounds(30, 110, 60, 14);
        getFrame().getContentPane().add(lblDailyCost);
        
        textFieldDailyCost = new JTextField();
        textFieldDailyCost.setBounds(100, 107, 150, 20);
        getFrame().getContentPane().add(textFieldDailyCost);
        textFieldDailyCost.setColumns(10);
        
        JLabel lblCubicCapacity = new JLabel("Cubic Capacity:");
        lblCubicCapacity.setBounds(30, 150, 90, 14);
        getFrame().getContentPane().add(lblCubicCapacity);
        
        textFieldCubicCapacity = new JTextField();
        textFieldCubicCapacity.setBounds(130, 147, 120, 20);
        getFrame().getContentPane().add(textFieldCubicCapacity);
        textFieldCubicCapacity.setColumns(10);
        
        JLabel lblSeat = new JLabel("Seat:");
        lblSeat.setBounds(30, 190, 60, 14);
        getFrame().getContentPane().add(lblSeat);
        
        textFieldSeat = new JTextField();
        textFieldSeat.setBounds(100, 187, 150, 20);
        getFrame().getContentPane().add(textFieldSeat);
        textFieldSeat.setColumns(10);
        
        JButton btnAddCar = new JButton("Add Car");
        btnAddCar.setBounds(100, 230, 89, 23);
        getFrame().getContentPane().add(btnAddCar);
        
        // Add action listener to the button
        btnAddCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call method to add car with input data
                String model = textFieldModel.getText();
                String category = textFieldCategory.getText();
                double dailyCost = Double.parseDouble(textFieldDailyCost.getText());
                int cubicCapacity = Integer.parseInt(textFieldCubicCapacity.getText());
                int seat = Integer.parseInt(textFieldSeat.getText());
                
                // Check if a car with the same model already exists
                if (DBMethods.carModelExists(model)) {
                    JOptionPane.showMessageDialog(frame, "A car with the same model already exists. Please enter a different model.");
                    return; // Stop further execution
                }
                
                // Call method in DBMethods to add car to database
                boolean success = DBMethods.addCarFromGui(model, category, dailyCost, cubicCapacity, seat);
                
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Car added successfully.");
                    // Close the window after adding the car
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add car. Please try again.");
                }
            }
        });
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
            }
        });
        btnClose.setBounds(200, 230, 89, 23);
        frame.getContentPane().add(btnClose);
    }
    

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
