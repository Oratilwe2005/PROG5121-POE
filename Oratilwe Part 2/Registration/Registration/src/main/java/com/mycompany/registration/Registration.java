package com.mycompany.registration;

import java.util.Scanner;


public class Registration {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        Login reg = new Login();
        MessageStorage storage = new MessageStorage();
        // --- REGISTRATION PHASE ---
        System.out.println("--- REGISTRATION ---");
        System.out.print("Enter First Name: ");
        reg.Name = input.next();
        System.out.print("Enter Last Name: ");
        reg.Surname = input.next();

        // Initial input
        System.out.print("Create Username: ");
        reg.registeredUsername = input.next();
        System.out.print("Create Password: ");
        reg.registeredPassword = input.next();
        System.out.print("Enter Cell Phone Number (e.g., +27...): ");
        reg.registeredCellNumber = input.next();

        // While ANY of the conditions are false, keep asking
        while (!reg.checkUsername() || !reg.checkPasswordComplexity() || !reg.checkCellPhoneNumber()) {
            System.out.println("\n" + reg.registerUser()); // Show specific errors
            System.out.println("---- Please try registration again ----");
            
            System.out.print("Create Username: ");
            reg.registeredUsername = input.next();
            System.out.print("Create Password: ");
            reg.registeredPassword = input.next();
            System.out.print("Enter Cell Phone Number: ");
            reg.registeredCellNumber = input.next();
        }

        // Final success message for registration
        System.out.println("\n" + reg.registerUser());

        // --- LOGIN PHASE ---
        System.out.println("\n--- LOGIN ---");
        System.out.print("Enter Username: ");
        reg.loginUsername = input.next();
        System.out.print("Enter Password: ");
        reg.loginPassword = input.next();
        System.out.print("Enter Cell Phone Number: ");
        reg.loginCellNumber = input.next();

        // While login credentials don't match registration
        while (!reg.loginUser()) {
            System.out.println("\n" + reg.returnLoginStatus()); // Show failure message
            System.out.println("--- Please try login again ---");
            
            System.out.print("Enter Username: ");
            reg.loginUsername = input.next();
            System.out.print("Enter Password: ");
            reg.loginPassword = input.next();
            System.out.print("Enter Cell Phone Number: ");
            reg.loginCellNumber = input.next();
        }

        System.out.println("\n" + reg.returnLoginStatus());
    

        // ── Menu ────────────────────────────────────
        System.out.println("Welcome to QuickChat.");
        System.out.println("How many messages would you like to send?");
        int total = input.nextInt();
        int count = 0;
        while (true) {
             System.out.println("""
                Please select an option:
                1) Send Messages
                2) Show Recently Sent Messages
                3) Quit
            """);
             String menu = input.nextLine();

            switch (menu) {
                case "1" -> {
                    if (count >= total) {
                        System.out.println("Message limit reached.");
                        break;
                    }
                    System.out.println("Enter recipient number (include + country code):");
                    String recipient =input.nextLine();
                    if (!Message.isValidRecipient(recipient)) {
                        System.out.println("Invalid recipient format.");
                        break;
                    }

                    System.out.println("Enter message (max 250 chars):");
                    String msg =input.nextLine();
                    if (!Message.isValidMessage(msg)) {
                        System.out.println("Message too long.");
                        break;
                    }

                    Message m = new Message(recipient, msg);
                    System.out.println("""
                                                                                        Choose message option:
                                                                                        1) Send
                                                                                        2) Disregard
                                                                                        3) Store for later
                                                                                    """);
                    String action = input.nextLine();
                    switch (action) {
                        case "1" -> {
                            m.send();
                            storage.addMessage(m);
                            count++;
                            System.out.println("Message sent!\n" + m.getMessageDetails());
                        }
                        case "2" -> {
                            m.disregard();
                            System.out.println("Message disregarded.");
                        }
                        case "3" -> {
                            m.store();
                            storage.addMessage(m);
                            count++;
                            System.out.println("Message stored.\n" + m.getMessageDetails());
                        }
                        default -> System.out.println( "Invalid action.");
                    }
                }

                case "2" -> System.out.println("Coming Soon.");

                case "3" -> {
                  System.out.println("Exiting. Total messages: " + storage.getTotalMessages());
                    storage.saveMessagesToJson("messages.json");
                    return;
                }

                default -> System.out.println("Invalid menu option.");
            }
        }
    }
}