/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class JSONSerializer implements UserSerializerInterface{

    @Override
    public void serialize(List<User> users, String filename) throws IOException {
        File file = new File(filename);
       
       if(!file.exists()){
           file.createNewFile();
       }
       if(file.canWrite()){
           ObjectMapper mapper = new ObjectMapper();
           mapper.writeValue(file,users);
       } 
    }

    @Override
    public List<User> deserialize(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        
        if(file.canRead()){
            var mapper = new ObjectMapper();
            return mapper.readValue(file,new TypeReference<List<User>>() {});
        }
        return new ArrayList();
    }   
}
