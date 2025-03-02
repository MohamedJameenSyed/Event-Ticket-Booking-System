package org.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventManager {
    private Connection connection;

    public EventManager() {
        connection = DatabaseConnection.getConnection();  // Establish DB connection
    }

    // Add event to the database
    public void addEvent(Scanner scanner) {
        try {
            System.out.print("Enter event name: ");
            String name = scanner.nextLine();

            System.out.print("Enter event date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.print("Enter location: ");
            String location = scanner.nextLine();

            System.out.print("Enter ticket price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();  // Consume the newline character

            String query = "INSERT INTO events (name, date, location, price) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, date);
            statement.setString(3, location);
            statement.setDouble(4, price);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Event added successfully!");
            } else {
                System.out.println("Failed to add the event.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch all events from the database
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        try {
            String query = "SELECT * FROM events";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = resultSet.getString("date");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");

                Event event = new Event(id, name, date, location, price);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
}

