package org.application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create EventManager and TicketBooking instances
        EventManager eventManager = new EventManager();
        TicketBooking ticketBooking = new TicketBooking(eventManager);

        while (true) {
            try {
                // Print menu options
                System.out.println("1. Add Event (Admin)");
                System.out.println("2. Book Ticket (User)");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                
                // Read integer input for menu choice
                int choice = scanner.nextInt();  
                scanner.nextLine();  // Consume the newline character after reading the menu choice

                switch (choice) {
                    case 1:
                        eventManager.addEvent(scanner);  // Call addEvent for admin, passing the scanner
                        break;
                    case 2:
                        ticketBooking.bookTicket(scanner);  // Call bookTicket for user, passing the scanner
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();  // Close scanner to release resources
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine();  // Consume invalid input and continue
            }
        }
    }
}
