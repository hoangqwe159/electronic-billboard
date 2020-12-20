package BillboardServer.Database.Schema.ScheduleSchema;

import BillboardServer.Database.Database.DatabaseAbs;
import Utils.DateFormatter.DateFormatter;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represent schedule table in the database
 */
public class ScheduleSchema implements ScheduleSchemaIF{
    private DatabaseAbs db;
    private Statement statement;
    public ScheduleSchema(DatabaseAbs db){
        this.db = db;
        this.statement = db.stmt;
    }

    /**
     * Get all schedules from the database
     * @return An array of all entries
     */
    @Override
    public ArrayList<HashMap<String, String>> getAllSchedules() {
        String sql = "SELECT * FROM schedules";
        ArrayList<HashMap<String, String>> schedules = new ArrayList<>();
        ResultSet rs;
        try{
            rs = this.statement.executeQuery(sql);
            int index = 0;
            while(rs.next()){
                schedules.add(new HashMap<>());
                schedules.get(index).put("schedule_id", rs.getString("schedule_id"));
                schedules.get(index).put("billboard_id", rs.getString("billboard_id"));
                schedules.get(index).put("time_limit", rs.getString("time_limit"));
                schedules.get(index).put("next_run", rs.getString("next_run"));
                schedules.get(index).put("start_time", rs.getString("start_time"));
                schedules.get(index).put("created_at", rs.getString("created_at"));
                schedules.get(index).put("updated_at", rs.getString("updated_at"));

                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    /**
     * Delete a schedule by its billboard_id
     * @param billboard_id billboard id sent from client
     * @return A number of rows affected
     */
    @Override
    public int deleteScheduleByBillboard(String billboard_id) {
        String sql = "DELETE FROM schedules WHERE billboard_id =" + billboard_id;
        int rowAffected = 0;
        try{
            rowAffected = this.statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
            return rowAffected;
        }
        return rowAffected;
    }

    /**
     * Create a new schedule
     * @param billboard_id billboard id sent from client
     * @param startTime Date time to start the schedule
     * @param timeLimit How long will the schedule last
     * @param nextRun How long will it start again
     * @return A number of rows affected
     */
    @Override
    public int createSchedule(String billboard_id, String startTime, String timeLimit, String nextRun) {
        int rowAffected = 0;
        try{
            PreparedStatement stmt = this.db.connection.prepareStatement("INSERT INTO schedules " +
                    "(billboard_id, start_time, time_limit, next_run) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, Integer.parseInt(billboard_id));
            stmt.setTimestamp(2, Timestamp.valueOf(startTime));
            stmt.setInt(3, Integer.parseInt(timeLimit));
            stmt.setInt(4, Integer.parseInt(nextRun));
            rowAffected = stmt.executeUpdate();
            return rowAffected;
        }catch (SQLException e){
            return rowAffected;
        }
    }

    /**
     * Edit a schedule
     * @param schedule_id Schedule id sent from client
     * @param startTime Date time to start the schedule
     * @param timeLimit How long will the schedule last
     * @param nextRun How long will it start again
     * @return Number of rows affected
     */
    @Override
    public int editSchedule(String schedule_id, String startTime, String timeLimit, String nextRun) {
        int rowAffected = 0;
        try{
            PreparedStatement stmt = this.db.connection.prepareStatement("UPDATE schedules " +
                    "SET start_time = ?, time_limit = ?, "
                    + "next_run = ? WHERE schedule_id = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(startTime));
            stmt.setInt(2, Integer.parseInt(timeLimit));
            stmt.setInt(3, Integer.parseInt(nextRun));
            stmt.setInt(4, Integer.parseInt(schedule_id));
            rowAffected = stmt.executeUpdate();
            return rowAffected;
        }catch (SQLException e){
            e.printStackTrace();
            return rowAffected;
        }
    }

    /**
     * Delete a schedule
     * @param schedule_id Schedule id sent from client
     * @return Number of rows affected
     */
    @Override
    public int deleteSchedule(String schedule_id) {
        String sql = "DELETE FROM schedules WHERE schedule_id=" + schedule_id;
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
