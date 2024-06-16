/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package javausercatalog;

import services.BinarySerializer;
import services.JSONSerializer;
import services.CSVSerializer;
import services.XMLSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import interfaces.UserSerializerInterface;
import model.User;

public class JavaUserCatalog {
    private List<User> users;
    private UserSerializerInterface csvSerializer = new CSVSerializer();
    private UserSerializerInterface binarySerializer = new BinarySerializer();
    private UserSerializerInterface jsonSerializer = new JSONSerializer();
    private UserSerializerInterface xmlSerializer = new XMLSerializer();


    public JavaUserCatalog() {
        this.users = new ArrayList<>();
    }
    
    public void addUser(String firstName, String lastName, int age, String email) {
        users.add(new User(firstName, lastName, age, email));
    }
    
    public void removeUser(String email) {
        boolean userRemoved = users.removeIf(user -> user.getEmail().equals(email));
        if (userRemoved) {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("User with email '" + email + "' removed successfully.");
            System.out.println("------------------------------------------------------------------------");
        } else {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("User with email '" + email + "' not found.");
            System.out.println("------------------------------------------------------------------------");
        }
    }
    
    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
    
    public void saveToFile(String format, String filename) throws IOException {
        switch (format.toLowerCase()) {
            case "csv" -> csvSerializer.serialize(users, filename);
            case "bin" -> binarySerializer.serialize(users, filename);
            case "json" -> jsonSerializer.serialize(users, filename);
            case "xml" -> xmlSerializer.serialize(users, filename);
            default -> {
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Unsupported format '"+format.toLowerCase()+"'. Failed to save users" );
                System.out.println("------------------------------------------------------------------------");
                return;
            }
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Users saved successfully.");
        System.out.println("------------------------------------------------------------------------");
    }
    
    public void loadFromFile(String format, String filename) throws IOException, ClassNotFoundException {
        switch (format.toLowerCase()) {
            case "csv" -> users = csvSerializer.deserialize(filename);
            case "bin" -> users = binarySerializer.deserialize(filename);
            case "json" -> users = jsonSerializer.deserialize(filename);
            case "xml" -> users = xmlSerializer.deserialize(filename);
            default -> {
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Unsupported format '"+format.toLowerCase()+"'. Failed to load users");
                System.out.println("------------------------------------------------------------------------");
                return;
            }
        }
        if (users.isEmpty()) {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("No users were loaded from the file.");
            System.out.println("------------------------------------------------------------------------");
        } else {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Users loaded successfully.");
            System.out.println("------------------------------------------------------------------------");
        }
    }
    
    public static void main(String[] args) {
        JavaUserCatalog userCatalog = new JavaUserCatalog();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Please choose an option:");
            System.out.println("1. add      - Add a new user");
            System.out.println("2. remove   - Remove an existing user by email");
            System.out.println("3. list     - List all users");
            System.out.println("4. save     - Save users to a file");
            System.out.println("5. load     - Load users from a file");
            System.out.println("6. exit     - Exit the application");
            System.out.print("\nEnter your choice: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1","add" -> {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Enter first name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter last name:");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter age:");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    try {
                        userCatalog.addUser(firstName, lastName, age, email);
                    System.out.println("------------------------------------------------------------------------");
                        System.out.println("User added successfully.");
                    System.out.println("------------------------------------------------------------------------");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
                case "2","remove" -> {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Enter email of the user to remove:");
                    String email = scanner.nextLine();
                    userCatalog.removeUser(email);
                }
                case "3","list" ->{
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Users : ");
                    userCatalog.listUsers();
                    System.out.println("------------------------------------------------------------------------");
                }
                case "4","save" -> {
                    System.out.println("Enter format : csv, bin, json, xml");
                    String format = scanner.nextLine();
                    System.out.println("Enter filename to save users to:");
                    String filename = scanner.nextLine();
                    try {
                        userCatalog.saveToFile(format, filename);
                    } catch (IOException e) {
                       System.out.println("------------------------------------------------------------------------");
                       System.err.println("Failed to save users: " + e.getMessage());
                       System.out.println("------------------------------------------------------------------------");
                    }
                }
                case "5","load" -> {
                    System.out.println("Enter format : csv, bin, json, xml");
                    String format = scanner.nextLine();
                    System.out.println("Enter filename to load users from:");
                    String filename = scanner.nextLine();
                    try {
                        userCatalog.loadFromFile(format, filename);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("------------------------------------------------------------------------");
                        System.err.println("Failed to load users: " + e.getMessage());
                        System.out.println("------------------------------------------------------------------------");
                    }
                }
                case "6","exit" -> {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Exiting...");
                    return;
                }
                default -> {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Invalid option");
                    System.out.println("------------------------------------------------------------------------");
                }
            }
        }
    }
}
