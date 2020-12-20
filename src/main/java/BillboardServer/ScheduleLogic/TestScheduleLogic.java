package BillboardServer.ScheduleLogic;

import BillboardServer.Database.Schema.ScheduleSchema.ScheduleSchemaIF;
import BillboardServer.ScheduleLogic.Model.Schedule;
import BillboardServer.ScheduleLogic.ScheduleParser.ScheduleParser;
import BillboardServer.ScheduleLogic.ScheduleParser.ScheduleSchemaStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test is created after the DatabaseStub and ScheduleParserPrototype were implemented.
 * When the the marker run the test, some tests might fail because the class compare with the current time.
 */
class TestScheduleLogic {
    ArrayList<Schedule> testSchedules;
    ScheduleSchemaIF scheduleSchema = new ScheduleSchemaStub();
    @BeforeEach
    void getFakeData () throws SQLException {
        testSchedules = ScheduleParser.parseResult(scheduleSchema.getAllSchedules());
    }

    @Test
    void generateTimeTable_Test_Type(){
        //Before function call
        assertEquals(testSchedules.get(0).getStartRun(), null);

        //After
        ScheduleLogic.generateTimeTable(testSchedules);
        assertEquals(testSchedules.get(0).getStartRun().get(0).getClass().getSimpleName(), "Date");
    }

    @Test
    void generateTimeTable_Test_StartRun_Value(){
        ScheduleLogic.generateTimeTable(testSchedules);
        String actual = String.valueOf(testSchedules.get(0).getStartRun().get(0).getTime());
        String expected = "1589851800000";
        assertEquals(expected, actual);
    }

    @Test
    void generateTimeTable_Test_EndRun_Value() {
        ScheduleLogic.generateTimeTable(testSchedules);
        String actual = String.valueOf(testSchedules.get(0).getEndRun().get(0).getTime());
        String expected = "1589853600000";
        assertEquals(expected, actual);
    }

    @Test
    void timeOverLap_Test_RandomCase() {
        ScheduleLogic.removeOverlapTime(testSchedules);
        ArrayList<Date> firstStart = testSchedules.get(3).getStartRun();
        ArrayList<Date> firstEnd = testSchedules.get(3).getEndRun();
        ArrayList<Date> secondStart = testSchedules.get(1).getStartRun();
        ArrayList<Date> secondEnd = testSchedules.get(1).getEndRun();
        boolean isOverlap = false;
        for (int j = 0; j < secondStart.size(); j++) {
            for (int k = 0; k < firstStart.size(); k++) {
                if (secondStart.get(j).getTime() < firstStart.get(k).getTime()
                        && secondEnd.get(j).getTime() > firstStart.get(k).getTime()) {
                    isOverlap = true;
                } else if (secondStart.get(j).getTime() < firstStart.get(k).getTime()
                        && secondEnd.get(j).getTime() >= firstStart.get(k).getTime()) {
                    isOverlap = true;
                } else if (secondStart.get(j).getTime() <= firstEnd.get(k).getTime()
                        && secondEnd.get(j).getTime() > firstEnd.get(k).getTime()) {
                    isOverlap = true;

                } else if (secondStart.get(j).getTime() >= firstStart.get(k).getTime()
                        && secondEnd.get(j).getTime() <= firstEnd.get(k).getTime()) {
                    isOverlap = true;
                }else{
                    isOverlap = false;
                }
            }
        }
        assertEquals(false, isOverlap);
    }

    @Test
    void getCurrentBillboard_noSchedules() {
        Schedule currentSchedule = ScheduleLogic.getCurrentSchedule(new ArrayList<>());
        assertEquals(null, currentSchedule);
    }


    @Test
    void getCurrentBillboard_manySchedules(){
        Schedule currentSchedule = ScheduleLogic.getCurrentSchedule(testSchedules);
        assertEquals(1, currentSchedule.getBillboardId());
    }
}