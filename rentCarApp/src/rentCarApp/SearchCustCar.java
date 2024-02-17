package rentCarApp;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCustCar {

	private JFrame frame;
    private JTextField customerSearchField;
    private JTextField carSearchField;
    private JTextArea resultArea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCustCar window = new SearchCustCar();
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
	public SearchCustCar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        frame = new JFrame();
        frame.setTitle("Search Customers and Cars");
        frame.setBounds(100, 100, 538, 432);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCustomerSearch = new JLabel("Customer Name:");
        lblCustomerSearch.setBounds(20, 20, 120, 20);
        frame.getContentPane().add(lblCustomerSearch);

        customerSearchField = new JTextField();
        customerSearchField.setBounds(135, 20, 200, 20);
        frame.getContentPane().add(customerSearchField);

        JLabel lblCarSearch = new JLabel("Car Model:");
        lblCarSearch.setBounds(20, 50, 120, 20);
        frame.getContentPane().add(lblCarSearch);

        carSearchField = new JTextField();
        carSearchField.setBounds(135, 50, 200, 20);
        frame.getContentPane().add(carSearchField);

        JButton customerSearchButton = new JButton("Search Customers");
        customerSearchButton.setBounds(345, 20, 150, 20);
        frame.getContentPane().add(customerSearchButton);

        JButton carSearchButton = new JButton("Search Cars");
        carSearchButton.setBounds(345, 50, 150, 20);
        frame.getContentPane().add(carSearchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(20, 80, 460, 250);
        frame.getContentPane().add(scrollPane);

        customerSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchInput = customerSearchField.getText();
                // Call method to search customers by name from DBMethods
                String searchResult = DBMethods.searchCustomers(searchInput);
                resultArea.setText(searchResult);
            }
        });

        carSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchInput = carSearchField.getText();
                // Call method to search cars by model from DBMethods
                String searchResult = DBMethods.searchCars(searchInput);
                resultArea.setText(searchResult);
            }
        });
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
            }
        });
        btnClose.setBounds(175, 341, 120, 34);
        frame.getContentPane().add(btnClose);
    }

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
