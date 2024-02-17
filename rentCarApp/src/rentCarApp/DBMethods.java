package rentCarApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DBMethods {
	
    // Add a new car to the database
	public static void addCar(Connection connection, String model, String category, double dailyCost,int cubicCapacity, int seat) {
		try(PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO Car (category_id, model, daily_cost, cubic_capacity, seat) " +
                        "VALUES ((SELECT category_id FROM CarCategory WHERE name = ?), ?, ?, ?, ?)")) {
	statement.setString(1, category);
	statement.setString(2, model);
	statement.setDouble(3, dailyCost);
	statement.setInt(4, cubicCapacity);
	statement.setInt(5, seat);
	statement.executeUpdate();
	System.out.println("Car added succes");
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Error adding car");
	}
  }

	// Method to check if a car with the same model already exists
	 public static boolean carModelExists(String model) {
	        try {
	            // Get a connection to the database
	        	DBUtils dbUtils = new DBUtils(); // Create an instance of DBUtils
	            Connection connection = dbUtils.getConnection();

	            // Prepare the SQL statement
	            String query = "SELECT COUNT(*) AS count FROM Car WHERE model = ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setString(1, model);

	            // Execute the query
	            ResultSet resultSet = statement.executeQuery();

	            // Get the count of cars with the same model
	            if (resultSet.next()) {
	                int count = resultSet.getInt("count");
	                return count > 0; // Return true if count > 0, indicating that the model already exists
	            }

	            // Close the resources
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            // You might want to log the error or handle it appropriately
	        }
	        return false; // Return false by default or if an error occurs
	    }


// Add a new customer to the database
public static void addCustomer(Connection connection, String firstName, String lastName, String gender, String homeAddress, String email, String phone) {
    try (PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO Customer (first_name, last_name, gender, home_address, email, phone) " +
                    "VALUES (?, ?, ?, ?, ?, ?)")) {
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, gender);
        statement.setString(4, homeAddress);
        statement.setString(5, email);
        statement.setString(6, phone);
        statement.executeUpdate();
        System.out.println("Customer added successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error adding customer.");
    }
}
public static boolean customerEmailExists(String email) {
    DBUtils dbUtils = new DBUtils();
    boolean exists = false;
    
    try (Connection connection = dbUtils.getConnection();
         PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM Customer WHERE email = ?")) {

        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            exists = count > 0;
        }

        resultSet.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return exists;
}


// Rent a car to a customer
public static void rentCar(Connection connection, int customerId, int carId, int rentalDays) {
    try (PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO Rental (customer_id, car_id, rental_days) VALUES (?, ?, ?)")) {
        statement.setInt(1, customerId);
        statement.setInt(2, carId);
        statement.setInt(3, rentalDays);
        statement.executeUpdate();
        System.out.println("Car rented successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error renting car.");
    }
}
    public static String getRentedCars() {
    	DBUtils dbUtils = new DBUtils(); // Create an instance of DBUtils
        StringBuilder availableCars = new StringBuilder();
        try (Connection connection = dbUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT model FROM Car WHERE car_id IN (SELECT car_id FROM Rental)")) {
            
            while (resultSet.next()) {
                availableCars.append(resultSet.getString("model")).append("\n");
            }

            if (availableCars.length() == 0) {
                availableCars.append("No rented cars.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            availableCars.append("Error retrieving rented cars.");
        }
        return availableCars.toString();
 }
    
    public static String searchCustomers(String searchInput) {
    	DBUtils dbUtils = new DBUtils();
        StringBuilder searchResults = new StringBuilder();
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT c.first_name, c.last_name, GROUP_CONCAT(car.model SEPARATOR ', ') AS rented_cars " +
                             "FROM Customer c " +
                             "LEFT JOIN Rental r ON c.customer_id = r.customer_id " +
                             "LEFT JOIN Car car ON r.car_id = car.car_id " +
                             "WHERE c.first_name LIKE ? OR c.last_name LIKE ? " +
                             "GROUP BY c.customer_id")) {
            statement.setString(1, "%" + searchInput + "%");
            statement.setString(2, "%" + searchInput + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String rentedCars = resultSet.getString("rented_cars");

                searchResults.append("Customer: ").append(firstName).append(" ").append(lastName).append("\n");
                searchResults.append("Rented Cars: ").append(rentedCars).append("\n\n");
            }

            if (searchResults.length() == 0) {
                searchResults.append("No results found.");
            }

            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            searchResults.append("Error performing customer search.");
        }
        return searchResults.toString();
    }
    
    public static String searchCars(String searchInput) {
    	DBUtils dbUtils = new DBUtils();
        StringBuilder searchResults = new StringBuilder();
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT car.model, COUNT(r.customer_id) AS rental_count " +
                             "FROM Car car " +
                             "LEFT JOIN Rental r ON car.car_id = r.car_id " +
                             "WHERE car.model LIKE ? " +
                             "GROUP BY car.car_id")) {
            statement.setString(1, "%" + searchInput + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String model = resultSet.getString("model");
                int rentalCount = resultSet.getInt("rental_count");

                searchResults.append("Car: ").append(model).append("\n");
                searchResults.append("Rental Count: ").append(rentalCount).append("\n\n");
            }

            if (searchResults.length() == 0) {
                searchResults.append("No results found.");
            }

            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            searchResults.append("Error performing car search.");
        }
        return searchResults.toString();
    }

    public static boolean addCarFromGui(String model, String category, double dailyCost, int cubicCapacity, int seat) {
    	DBUtils dbUtils = new DBUtils();
        Connection connection = null;
        try {
            // Get connection
            connection = dbUtils.getConnection();
            
            // Call addCar method
            DBMethods.addCar(connection, model, category, dailyCost, cubicCapacity, seat);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close connection
            DBUtils.closeConnection(connection);
        }
		return true;
    }
    public static boolean addCustomerFromGui(String firstName, String lastName, String gender, String homeAddress, String email, String phone) {
    	DBUtils dbUtils = new DBUtils();
    	Connection connection = null;
        try {
            // Get connection
            connection = dbUtils.getConnection();
            
            // Call addCustomer method
            DBMethods.addCustomer(connection, firstName, lastName, gender, homeAddress, email, phone);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close connection
            DBUtils.closeConnection(connection);
        }
		return true;
    }
 // Method to load available cars for rent
    public static List<String> loadAvailableCars() {
        List<String> availableCars = new ArrayList<>();
        DBUtils dbUtils = new DBUtils();
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT car_id, model FROM Car WHERE car_id NOT IN (SELECT car_id FROM Rental)")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String model = resultSet.getString("model");
                availableCars.add("Car ID: " + carId + ", Model: " + model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading available cars.");
        }
        return availableCars;
    }

    // Method to rent a car to a customer
    public static void rentCarToCustomer(int customerId, int carId, int rentalDays) {
    	DBUtils dbUtils = new DBUtils();
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Rental (customer_id, car_id, rental_days) VALUES (?, ?, ?)")) {
            statement.setInt(1, customerId);
            statement.setInt(2, carId);
            statement.setInt(3, rentalDays);
            statement.executeUpdate();
            System.out.println("Car rented successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error renting car.");
        }
    }
    public static List<Customer> loadAvailableCustomers() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {
            connection = dbUtils.getConnection();
            String query = "SELECT * FROM Customer";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String homeAddress = resultSet.getString("home_address");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                // Create a Customer object and add it to the list
                Customer customer = new Customer(id, firstName, lastName, gender, homeAddress, email, phone);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeStatement(statement);
            DBUtils.closeConnection(connection);
        }

        return customers;
    }
    public static boolean deleteCustomerByName(String customerName) {
        String query = "DELETE FROM Customer WHERE first_name = ? OR last_name = ?";
        DBUtils dbUtils = new DBUtils();
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customerName);
            statement.setString(2, customerName);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCarByModel(String carModel) {
        String query = "DELETE FROM Car WHERE model = ?";
        DBUtils dbUtils = new DBUtils();
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, carModel);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
