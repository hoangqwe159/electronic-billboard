package BillboardServer.Database.Schema.UserSchema;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public interface UserSchemaIF {
    int createUser(String username, String hashedPassword, String permission) throws NoSuchAlgorithmException;
    int editUserNoPass(String user_id, String username, String permission);
    int updateUser(String user_id, String username, String password, String permission) throws NoSuchAlgorithmException;
    int deleteUser(String user_id);
    ArrayList<HashMap<String,String>> getUserByName(String username);
    ArrayList<HashMap<String,String>> getAllUsers();
}
