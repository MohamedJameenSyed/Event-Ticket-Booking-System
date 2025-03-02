package org.application;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class TicketBooking {
    private EventManager eventManager;

    public TicketBooking(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void bookTicket(Scanner scanner) {
        try {
            List<Event> events = eventManager.getEvents();

            // If no events are available
            if (events.isEmpty()) {
                System.out.println("No events available for booking.");
                return;
            }

            // Show the list of available events
            System.out.println("Available events:");
            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + ". " + events.get(i));
            }

            // Ask the user to select an event
            System.out.print("Enter the number of the event you want to book tickets for: ");
            int eventIndex = scanner.nextInt() - 1;  // Subtract 1 for zero-based index
            scanner.nextLine();  // Consume the newline character

            if (eventIndex < 0 || eventIndex >= events.size()) {
                System.out.println("Invalid event selection.");
                return;
            }

            Event selectedEvent = events.get(eventIndex);
            System.out.print("Enter your name for ticket booking: ");
            String name = scanner.nextLine();

            System.out.print("Enter number of tickets: ");
            int numTickets = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after reading number of tickets

            // Book the ticket in the database
            String query = "INSERT INTO bookings (event_id, name, num_tickets) VALUES (?, ?, ?)";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query);
            statement.setInt(1, selectedEvent.getId());  // Assuming you have an `id` field in your Event class and DB
            statement.setString(2, name);
            statement.setInt(3, numTickets);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking confirmed!");
            } else {
                System.out.println("Failed to book the ticket.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
