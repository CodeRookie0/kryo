/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import interfaces.UserSerializerInterface;
import model.User;

/**
 *
 * @author maria
 */
public class CSVSerializer implements UserSerializerInterface{

    @Override
    public void serialize(List<User> users, String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (User user : users) {
                writer.write(String.format("%s;%s;%d;%s", user.getFirstName(), user.getLastName(), user.getAge(), user.getEmail()));
                writer.newLine();
            }
        }
    }

    @Override
    public List<User> deserialize(String filename) throws IOException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        File file = new File(filename);
        if (file.canRead()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(";");
                    if (fields.length == 4) {
                        String firstName = fields[0];
                        String lastName = fields[1];
                        int age = Integer.parseInt(fields[2]);
                        String email = fields[3];
                        users.add(new User(firstName, lastName, age, email));
                    }
                }
            }
        }
        return users;
    }
    
}
