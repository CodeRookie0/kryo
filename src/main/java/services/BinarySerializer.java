/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import interfaces.UserSerializerInterface;
import model.User;

/**
 *
 * @author maria
 */
public class BinarySerializer implements UserSerializerInterface{

    @Override
    public void serialize(List<User> users, String filename) throws IOException {
        File file = new File(filename);
        if(!file.exists()) {file.createNewFile();}
        
        if(file.canWrite()){
            try(ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(file));)
            {
                objectStream.writeObject(users);
            }
        }
    }

    @Override
    public List<User> deserialize(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if(file.canRead()){
            try(ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(file));)
            {
                Object o = objectStream.readObject();
            
                if(o != null && o.getClass()==ArrayList.class)
                {
                    return (List<User>)o;
                }
            }
        }
        return new ArrayList();
    }
    
}
