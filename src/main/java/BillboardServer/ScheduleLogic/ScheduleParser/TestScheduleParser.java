package BillboardServer.ScheduleLogic.ScheduleParser;

import BillboardServer.Database.Schema.ScheduleSchema.ScheduleSchemaIF;
import BillboardServer.ScheduleLogic.Model.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TestScheduleParser {
    ScheduleSchemaIF stubData;
    ScheduleParser parser;
    ArrayList<HashMap<String,String>> fakeData;
    HashMap<String,String> entry;

    @BeforeEach
    void constructParser() throws SQLException {
        stubData = new ScheduleSchemaStub();
        fakeData = stubData.getAllSchedules();
        entry = fakeData.get(0);
        parser = new ScheduleParser();
    }

    @Test
    void oneEntry_scheduleIdTest(){
        Schedule sch = ScheduleParser.parseOneEntry(entry);
        int schedule_id = sch.getScheduleId();
        assertEquals(1, schedule_id);
    }

    @Test
    void oneEntry_StartTimeTest() throws ParseException {
        Schedule sch = ScheduleParser.parseOneEntry(entry);
        Date start_time = sch.getStartTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expected = formatter.parse("2020-05-19 11:30:00");
        assertEquals(expected, start_time);
    }
    @Test
    void oneEntry_TimeLimitTest(){
        Schedule sch = ScheduleParser.parseOneEntry(entry);
        int expected = 30;
        assertEquals(expected, sch.getTimeLimit());
    }
    @Test
    void oneEntry_NextRunTest(){
        Schedule sch = ScheduleParser.parseOneEntry(entry);
        int expected = 30;
        assertEquals(expected, sch.getNextRun());
    }

    @Test
    void manyEntries_Test_Length(){
        ArrayList<Schedule> schedules = ScheduleParser.parseResult(fakeData);
        assertEquals(4, schedules.size());
    }

    @Test
    void manyEntries_Test_Example(){
        ArrayList<Schedule> schedules = ScheduleParser.parseResult(fakeData);
        Schedule sch = schedules.get(2);
        assertEquals(26, sch.getTimeLimit());
        assertEquals(120, sch.getNextRun());
    }
}