/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;
import java.util.regex.Pattern;

public class User implements Serializable{
    public String firstName;
    public String lastName;
    public int age;
    public String email;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
        Pattern.CASE_INSENSITIVE
    );
    public static boolean isValidAge(int age) {
        return age > 0 && age < 150;
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    public User() {
    }
    
    public User(String firstName, String lastName, int age, String email) {
        if (!isValidAge(age)) {
            System.out.println("------------------------------------------------------------------------");
            throw new IllegalArgumentException("Invalid age: " + age);
        }
        if (!isValidEmail(email)) {
            System.out.println("------------------------------------------------------------------------");
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", Age: " + age + ", Email: " + email;
    }
    
    
}
