package BillboardServer.Database.Schema.ScheduleSchema;

import java.util.ArrayList;
import java.util.HashMap;

public interface ScheduleSchemaIF {
    ArrayList<HashMap<String,String>> getAllSchedules();
    int deleteScheduleByBillboard(String billboard_id);
    int createSchedule(String billboard_id, String startTime, String timeLimit, String nextRun);
    int editSchedule(String schedule_id, String startTime, String timeLimit, String nextRun);
    int deleteSchedule(String schedule_id);
}
