package BillboardServer.Database.Schema.BillboardSchema;

import BillboardServer.Database.Database.DatabaseAbs;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represent billboard schema in the database
 */
public class BillboardSchema implements BillboardSchemaIF {
    private DatabaseAbs db;
    private Statement statement;
    public BillboardSchema(DatabaseAbs db){
        this.db = db;
        this.statement = db.stmt;
    }
    /**
     * Get all billboards from the database
     * @return Array of all billboards
     */
    @Override
    public ArrayList<HashMap<String, String>> getAllBillboards()  {
        ArrayList<HashMap<String, String>> billboards = new ArrayList<>();
        String sql = "select * from billboards";
        ResultSet rs;
        try{
            rs = this.statement.executeQuery(sql);
            int index = 0;
            //Store entries in an array
            while (rs.next()) {
                billboards.add(new HashMap<>());
                billboards.get(index).put("billboard_id", rs.getString("billboard_id"));
                billboards.get(index).put("user_id", rs.getString("user_id"));
                billboards.get(index).put("message", rs.getString("message"));
                billboards.get(index).put("information", rs.getString("information"));
                billboards.get(index).put("background_color", rs.getString("background_color"));
                billboards.get(index).put("message_color", rs.getString("message_color"));
                billboards.get(index).put("created_at", rs.getString("created_at"));
                billboards.get(index).put("updated_at", rs.getString("updated_at"));
                billboards.get(index).put("picture", rs.getString("picture"));
                billboards.get(index).put("info_color", rs.getString("info_color"));
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return billboards;
        }
        return billboards;
    }

    /**
     * Get a billboard by its id
     * @param billboard_id Billboard Id sent from client
     * @return Array of all entries
     */
    @Override
    public ArrayList<HashMap<String, String>> getBillboardById(String billboard_id){
        String sql = "SELECT * FROM billboards WHERE billboard_id = " + billboard_id;
        ResultSet rs;
        ArrayList<HashMap<String, String>> billboard = new ArrayList<>();
        int index = 0;
        try{
            rs = this.statement.executeQuery(sql);
            while(rs.next()){
                billboard.add(new HashMap<>());
                billboard.get(index).put("billboard_id", rs.getString("billboard_id"));
                billboard.get(index).put("user_id", rs.getString("user_id"));
                billboard.get(index).put("message", rs.getString("message"));
                billboard.get(index).put("information", rs.getString("information"));
                billboard.get(index).put("background_color", rs.getString("background_color"));
                billboard.get(index).put("message_color", rs.getString("message_color"));
                billboard.get(index).put("created_at", rs.getString("created_at"));
                billboard.get(index).put("updated_at", rs.getString("updated_at"));
                billboard.get(index).put("picture", rs.getString("picture"));
                billboard.get(index).put("info_color", rs.getString("info_color"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return billboard;
        }
        return billboard;
    }

    /**
     * Create one billboard
     * @param user_id User Id
     * @param message Billboard message
     * @param information Billboard information
     * @param backgroundColor Billboard background color
     * @param messageColor  Billboard message color
     * @param picture Billboard background picture
     * @return Number of rows affected
     */
    @Override
    public int createBillboard(String user_id, String message, String information, String backgroundColor, String messageColor, String picture, String infoColor) {
        int rowAffected = 0;
        try{
            PreparedStatement stmt = this.db.connection.prepareStatement("INSERT INTO billboards (user_id, " +
                    "message, " +
                    "information, " +
                    "background_color, " +
                    "message_color, " +
                    "picture, info_color) VALUES (?,?,?,?,?,?,?)");
            stmt.setInt(1, Integer.parseInt(user_id));
            stmt.setString(2, message.equals("null") ? null : message);
            stmt.setString(3, information.equals("null") ? null : information);
            stmt.setString(4, backgroundColor);
            stmt.setString(5, messageColor);
            Blob blob = this.db.connection.createBlob();
            blob.setBytes(1, picture.getBytes());
            stmt.setBlob(6, picture.equals("null") ? null : blob);
            stmt.setString(7, infoColor);
            rowAffected = stmt.executeUpdate();
            return rowAffected;
        } catch (SQLException e) {
            return rowAffected;
        }
    }

    /**
     * Edit one billboard
     * @param billboard_id Billboard Id
     * @param message Billboard message
     * @param information Billboard information
     * @param backgroundColor Billboard background color
     * @param messageColor  Billboard message color
     * @param picture Billboard background picture
     * @return Number of rows affected
     */
    @Override
    public int editBillboard(String billboard_id, String message, String information, String backgroundColor, String messageColor, String picture, String infoColor) {
        int rowAffected = 0;
        try{
            PreparedStatement stmt = this.db.connection.prepareStatement("UPDATE billboards " +
                    "SET " +
                    "message = ?, " +
                    "information = ?, " +
                    "background_color = ?, " +
                    "message_color = ?, " +
                    "info_color = ?, " +
                    "picture = ? " +
                    "WHERE billboard_id = ?");
            stmt.setString(1, message.equals("null") ? null : message);
            stmt.setString(2, information.equals("null") ? null : information);
            stmt.setString(3, backgroundColor);
            stmt.setString(4, messageColor);
            stmt.setString(5, infoColor);
            Blob blob = this.db.connection.createBlob();
            blob.setBytes(1, picture.getBytes());
            stmt.setBlob(6, picture.equals("null") ? null : blob);
            stmt.setInt(7, Integer.parseInt(billboard_id));
            rowAffected = stmt.executeUpdate();
            return rowAffected;
        }catch (SQLException e){
            return rowAffected;
        }
    }

    /**
     * Delete one billboard
     * @param billboard_id Billboard id
     * @return Number of rows affected
     */
    @Override
    public int deleteBillboard(String billboard_id) {
        String sql = "DELETE FROM billboards WHERE billboard_id =" + billboard_id;
        int rowAffected = 0;
        try{
            rowAffected = this.statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
            return rowAffected;
        }
        return rowAffected;
    }
}
