/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import interfaces.UserSerializerInterface;
import model.User;

/**
 *
 * @author maria
 */
public class XMLSerializer implements UserSerializerInterface{

    @Override
    public void serialize(List<User> users, String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }

        if (file.canWrite()) {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(file, users);
        }
    }

    @Override
    public List<User> deserialize(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (file.canRead()) {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        }
        return new ArrayList<>();
    }
    
}
