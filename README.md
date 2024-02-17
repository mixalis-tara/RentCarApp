# RentCarApp
Car Rental Application

The Car Rental Application is a Java-based desktop application designed to manage car rental services. It provides a user-friendly interface for both customers and car rental administrators to perform various tasks efficiently.
## Features:

- Add New Cars: Easily add new cars to the database with details such as model, category, daily cost, cubic capacity, and seating capacity.

- Add New Customers: Add new customers with their personal details including first name, last name, gender, home address, email, and phone number. The application prevents duplicate entries for customers with the same email address.

- View Rented Cars: View a list of cars currently rented out, along with details about the customers who have rented them.

- Rent a Car: Rent a car from the available inventory by selecting the desired car model, specifying the rental duration (up to 30 days), and choosing the customer renting the car. The application ensures that only available cars can be rented.

- Search Customers and Cars: Search for customers by their first name or last name to view their rental history, including details of the cars they have rented. Similarly, search for cars by model to see how many times they have been rented out.

- Delete Customers and Cars: Remove customers and cars from the database. Deleting a customer removes all associated rental history, and deleting a car removes all records of its rentals.

## Technologies Used:

- Java
- MySQL
- Swing for GUI
- JDBC for database connectivity

## Future Improvements:

- Implement user authentication and authorization for secure login.
- Enhance search functionality with advanced filters and sorting options.
- Introduce notifications for overdue rentals or upcoming reservations.
- Implement graphical reports and analytics for better insights into rental trends.

How to Run:

- Clone the repository to your local machine.
- Set up the MySQL database using the provided SQL schema.
- Import the project into your favorite Java IDE.
- Ensure all dependencies are resolved and run the Gui.java file to launch the application.
