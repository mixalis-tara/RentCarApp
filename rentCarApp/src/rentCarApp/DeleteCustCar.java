package rentCarApp;

import javax.swing.*;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCustCar {
    private JFrame frame;
    private JTextField customerNameField;
    private JTextField carModelField;

    public DeleteCustCar() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Delete Customers and Cars");
        frame.setBounds(100, 100, 500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCustomerName = new JLabel("Customer Name:");
        lblCustomerName.setBounds(20, 20, 120, 20);
        frame.getContentPane().add(lblCustomerName);

        customerNameField = new JTextField();
        customerNameField.setBounds(150, 20, 200, 20);
        frame.getContentPane().add(customerNameField);

        JButton deleteCustomerButton = new JButton("Delete Customer");
        deleteCustomerButton.setBounds(360, 20, 120, 20);
        frame.getContentPane().add(deleteCustomerButton);

        JLabel lblCarModel = new JLabel("Car Model:");
        lblCarModel.setBounds(20, 50, 120, 20);
        frame.getContentPane().add(lblCarModel);

        carModelField = new JTextField();
        carModelField.setBounds(150, 50, 200, 20);
        frame.getContentPane().add(carModelField);

        JButton deleteCarButton = new JButton("Delete Car");
        deleteCarButton.setBounds(360, 50, 120, 20);
        frame.getContentPane().add(deleteCarButton);

        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = customerNameField.getText();
                // Call method to delete customer by name
                boolean success = DBMethods.deleteCustomerByName(customerName);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Customer deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to delete customer. Please try again.");
                }
            }
        });

        deleteCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carModel = carModelField.getText();
                // Call method to delete car by model
                boolean success = DBMethods.deleteCarByModel(carModel);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Car deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to delete car. Please try again.");
                }
            }
        });

        frame.setVisible(true);
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
            }
        });
        btnClose.setBounds(175, 98, 120, 31);
        frame.getContentPane().add(btnClose);
    
    }

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
