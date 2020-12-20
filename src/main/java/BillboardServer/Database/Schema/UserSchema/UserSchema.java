package BillboardServer.Database.Schema.UserSchema;

import BillboardServer.Database.Database.DatabaseAbs;
import Utils.Hash.Hash;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represent user schema in the database
 */
public class UserSchema implements UserSchemaIF {
    private DatabaseAbs db;
    private Statement statement;
    public UserSchema(DatabaseAbs db){
        this.db = db;
        this.statement = db.stmt;
    }

    /**
     * Create a user
     * @param username username sent from client
     * @param hashedPassword Hashed password sent from client
     * @param permission user permission sent from client
     * @return Number of rows affected
     */
    @Override
    public int createUser(String username, String hashedPassword, String permission) {
        int rowAffected = 0;
        try{
            String salt = Hash.generateSalt();
            String hashedWithSalt = Hash.hashStringWithSalt(hashedPassword, salt); //Hash password one more time with salt
            PreparedStatement stmt = this.db.connection.prepareStatement(
                    "REPLACE INTO users " +
                    "SET username = ?," +
                    "password = ?," +
                    "salt = ?," +
                    "permission = ?");
            stmt.setString(1, username);
            stmt.setString(2, hashedWithSalt);
            stmt.setString(3, salt);
            stmt.setString(4, permission);
            rowAffected = stmt.executeUpdate();
            return rowAffected;
        }catch (SQLException | NoSuchAlgorithmException e){
            e.printStackTrace();
            return rowAffected;
        }
    }

    /**
     * Edit the user but with out editing password
     * @param user_id User id sent from client
     * @param username Username sent from client
     * @param permission User permission sent from client
     * @return Number of rows affected
     */
    @Override
    public int editUserNoPass(String user_id, String username, String permission) {
        int rowAffected = 0;
        try{
            PreparedStatement stmt = this.db.connection.prepareStatement("UPDATE users " +
                    "SET username = ?," +
                    "permission = ? " +
                    "WHERE user_id = ?");
            stmt.setString(1, username);
            stmt.setString(2, permission);
            stmt.setInt(3, Integer.parseInt(user_id));
            rowAffected = stmt.executeUpdate();
            return rowAffected;
        }catch (SQLException e){
            e.printStackTrace();
            return rowAffected;
        }
    }

    /**
     * Edit a user
     * @param user_id User ID sent from client
     * @param username Username sent from client
     * @param password Hashed password sent from client
     * @param permission User permission sent from client
     * @return
     */
    @Override
    public int updateUser(String user_id, String username, String password, String permission) {
        int rowAffected = 0;
        try{
            String salt = Hash.generateSalt();
            String hashedWithSalt = Hash.hashStringWithSalt(password, salt);
            PreparedStatement stmt = this.db.connection.prepareStatement("UPDATE users " +
                    "SET username = ?," +
                    "password = ?, " +
                    "salt = ?, " +
                    "permission = ? " +
                    "WHERE user_id = ?");
            stmt.setString(1, username);
            stmt.setString(2, hashedWithSalt);
            stmt.setString(3, salt);
            stmt.setString(4, permission);
            stmt.setInt(5, Integer.parseInt(user_id));
            rowAffected = stmt.executeUpdate();
            return rowAffected;
        }catch (SQLException | NoSuchAlgorithmException e){
            e.printStackTrace();
            return rowAffected;
        }
    }

    /**
     * Delete a user
     * @param user_id User ID sent from client
     * @return Number of rows affected
     */
    @Override
    public int deleteUser(String user_id) {
        int rowAffected = 0;
        String sql = "DELETE FROM users where user_id=" + user_id;
        try{
            rowAffected = this.statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
            return rowAffected;
        }
        return rowAffected;
    }

    /**
     * Get a user by username
     * @param username Username sent from client
     * @return Array of all entries
     */
    @Override
    public ArrayList<HashMap<String, String>> getUserByName(String username) {
        String sql = "SELECT user_id,username,password,salt,permission from users where username='" + username+ "'";
        ResultSet rs = null;
        ArrayList<HashMap<String, String>> user = new ArrayList<>();
        try{
            rs = this.statement.executeQuery(sql);
            if (rs.next()) {
                user.add(new HashMap<>());
                user.get(0).put("user_id", rs.getString("user_id"));
                user.get(0).put("username", rs.getString("username"));
                user.get(0).put("password", rs.getString("password"));
                user.get(0).put("salt", rs.getString("salt"));
                user.get(0).put("permission", rs.getString("permission"));
            }
            return user;
        }catch (SQLException e){
            e.printStackTrace();
            return user;
        }
    }

    /**
     * Get all users
     * @return Array of all entries
     */
    @Override
    public ArrayList<HashMap<String, String>> getAllUsers() {
        String sql = "SELECT user_id, username, permission, created_at, updated_at from users";
        ArrayList<HashMap<String, String>> users = new ArrayList<>();
        ResultSet rs;
        try{
            rs = this.statement.executeQuery(sql);
            int index = 0;
            while(rs.next()){
                users.add(new HashMap<>());
                users.get(index).put("user_id" , rs.getString("user_id"));
                users.get(index).put("username" , rs.getString("username"));
                users.get(index).put("permission" , rs.getString("permission"));
                users.get(index).put("created_at" , rs.getString("created_at"));
                users.get(index).put("updated_at" , rs.getString("updated_at"));
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
