/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.io.IOException;
import java.util.List;
import model.User;

public interface UserSerializerInterface {
    public void serialize(List<User> users, String filename) throws IOException;
    public List<User> deserialize(String filename) throws IOException, ClassNotFoundException;
}
