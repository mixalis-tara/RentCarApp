package rentCarApp;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCustomer {

    private JFrame frame;
    private JTextField textFieldFirstName;
    private JTextField textFieldLastName;
    private JTextField textFieldGender;
    private JTextField textFieldHomeAddress;
    private JTextField textFieldEmail;
    private JTextField textFieldPhone;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddCustomer window = new AddCustomer();
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
    public AddCustomer() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(30, 30, 80, 14);
        frame.getContentPane().add(lblFirstName);
        
        textFieldFirstName = new JTextField();
        textFieldFirstName.setBounds(120, 27, 200, 20);
        frame.getContentPane().add(textFieldFirstName);
        textFieldFirstName.setColumns(10);
        
        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(30, 60, 80, 14);
        frame.getContentPane().add(lblLastName);
        
        textFieldLastName = new JTextField();
        textFieldLastName.setBounds(120, 57, 200, 20);
        frame.getContentPane().add(textFieldLastName);
        textFieldLastName.setColumns(10);
        
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(30, 90, 80, 14);
        frame.getContentPane().add(lblGender);
        
        textFieldGender = new JTextField();
        textFieldGender.setBounds(120, 87, 200, 20);
        frame.getContentPane().add(textFieldGender);
        textFieldGender.setColumns(10);
        
        JLabel lblHomeAddress = new JLabel("Home Address:");
        lblHomeAddress.setBounds(30, 120, 100, 14);
        frame.getContentPane().add(lblHomeAddress);
        
        textFieldHomeAddress = new JTextField();
        textFieldHomeAddress.setBounds(120, 117, 200, 20);
        frame.getContentPane().add(textFieldHomeAddress);
        textFieldHomeAddress.setColumns(10);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 150, 80, 14);
        frame.getContentPane().add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(120, 147, 200, 20);
        frame.getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);
        
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(30, 180, 80, 14);
        frame.getContentPane().add(lblPhone);
        
        textFieldPhone = new JTextField();
        textFieldPhone.setBounds(120, 177, 200, 20);
        frame.getContentPane().add(textFieldPhone);
        textFieldPhone.setColumns(10);
        
        JButton btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.setBounds(81, 230, 147, 23);
        getFrame().getContentPane().add(btnAddCustomer);
        
        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get input data
                String firstName = textFieldFirstName.getText();
                String lastName = textFieldLastName.getText();
                String gender = textFieldGender.getText();
                String homeAddress = textFieldHomeAddress.getText();
                String email = textFieldEmail.getText();
                String phone = textFieldPhone.getText();
                
                // Check if customer email already exists
                if (DBMethods.customerEmailExists(email)) {
                    JOptionPane.showMessageDialog(frame, "A customer with the same email already exists.");
                    return; // Don't proceed further
                }
                
                // Call method in DBMethods to add customer to database
                boolean success = DBMethods.addCustomerFromGui(firstName, lastName, gender, homeAddress, email, phone);
                
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Customer added successfully.");
                    // Close the window after adding the customer
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add customer. Please try again.");
                }
            }
        });
        
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
            }
        });
        btnClose.setBounds(260, 230, 89, 23);
        frame.getContentPane().add(btnClose);
    }

    public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	
}
