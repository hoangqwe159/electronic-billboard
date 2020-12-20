package BillboardServer.ScheduleLogic.ScheduleParser;

import BillboardServer.ScheduleLogic.Model.Schedule;
import Utils.DateFormatter.DateFormatter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Parsing Array of HashMap to array of Schedules.
 */
public class ScheduleParser {
    /**
     * Parsing all the entries to and array of Schedule
     * @param result Represent all entries from the database
     * @return ArrayList of all schedule
     */
    public static ArrayList<Schedule> parseResult(ArrayList<HashMap<String, String>> result){
        ArrayList<Schedule> schedules = new ArrayList<>();
        //Parsing
        for (int i = 0; i < result.size(); i++) {
            Schedule sch = parseOneEntry(result.get(i));
            schedules.add(sch);
        }
        return schedules;
    }
    /**
     * @param oneEntry represent one entry
     * @return a Schedule
     */
    public static Schedule parseOneEntry(HashMap<String,String> oneEntry){
        Schedule sch = new Schedule ();
        sch.setBillboardId(Integer.parseInt(oneEntry.get("billboard_id")));
        sch.setScheduleId(Integer.parseInt(oneEntry.get("schedule_id")));
        sch.setCreatedAt(DateFormatter.formatDate(oneEntry.get("created_at")));
        sch.setUpdateAt(DateFormatter.formatDate(oneEntry.get("updated_at")));
        sch.setStartTime(DateFormatter.formatDate(oneEntry.get("start_time")));
        sch.setNextRun(Integer.parseInt(oneEntry.get("next_run")));
        sch.setTimeLimit(Integer.parseInt(oneEntry.get("time_limit")));
        return sch;
    }
}
