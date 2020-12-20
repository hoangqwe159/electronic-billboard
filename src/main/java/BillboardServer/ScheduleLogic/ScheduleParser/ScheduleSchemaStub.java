package BillboardServer.ScheduleLogic.ScheduleParser;

import BillboardServer.Database.Schema.ScheduleSchema.ScheduleSchemaIF;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleSchemaStub implements ScheduleSchemaIF {
    @Override
    public ArrayList<HashMap<String, String>> getAllSchedules() {
        ArrayList<HashMap<String, String>> schedules = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            schedules.add(new HashMap<>());
        }
        ///Hardcode to create fake data entries
        schedules.get(0).put("schedule_id", String.valueOf(1));
        schedules.get(0).put("billboard_id", String.valueOf(1));
        schedules.get(0).put("start_time", "2020-05-19 11:30:00");
        schedules.get(0).put("time_limit", "30");
        schedules.get(0).put("next_run", "30");
        schedules.get(0).put("created_at", "2020-05-19 17:30:00");
        schedules.get(0).put("updated_at", "2020-05-19 17:30:00");

        schedules.get(1).put("schedule_id", String.valueOf(2));
        schedules.get(1).put("billboard_id", String.valueOf(2));
        schedules.get(1).put("start_time", "2020-05-19 15:30:00");
        schedules.get(1).put("time_limit", "10");
        schedules.get(1).put("next_run", "100");
        schedules.get(1).put("created_at", "2020-05-19 17:30:00");
        schedules.get(1).put("updated_at", "2020-05-19 09:15:00");

        schedules.get(2).put("schedule_id", String.valueOf(3));
        schedules.get(2).put("billboard_id", String.valueOf(3));
        schedules.get(2).put("start_time", "2020-05-10 16:35:00");
        schedules.get(2).put("time_limit", "26");
        schedules.get(2).put("next_run", "120");
        schedules.get(2).put("created_at", "2020-05-07 20:46:00");
        schedules.get(2).put("updated_at", "2020-05-07 22:30:00");

        schedules.get(3).put("schedule_id", String.valueOf(4));
        schedules.get(3).put("billboard_id", String.valueOf(4));
        schedules.get(3).put("start_time", "2020-05-07 18:25:00");
        schedules.get(3).put("time_limit", "100");
        schedules.get(3).put("next_run", "15");
        schedules.get(3).put("created_at", "2020-05-06 15:23:00");
        schedules.get(3).put("updated_at", "2020-05-07 23:03:00");
        return schedules;
    }

    @Override
    public int deleteScheduleByBillboard(String billboard_id) {
        return 0;
    }

    @Override
    public int createSchedule(String billboard_id, String startTime, String timeLimit, String nextRun) {
        return 0;
    }

    @Override
    public int editSchedule(String schedule_id, String startTime, String timeLimit, String nextRun) {
        return 0;
    }

    @Override
    public int deleteSchedule(String schedule_id) {
        return 0;
    }

}
