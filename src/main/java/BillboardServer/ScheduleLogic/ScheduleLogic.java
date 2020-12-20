package BillboardServer.ScheduleLogic;

import BillboardServer.ScheduleLogic.Model.Schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * This class handles the what-schedule-to-show logic.
 */
public class ScheduleLogic {

    /**
     * Get the current schedule to show based on time table processed
     * @param list - List of all schedules
     * @return billboard_id
     */
    public static Schedule getCurrentSchedule(ArrayList<Schedule> list){
        if(list.size() == 0) {
            return null;
        }
        else{
            //Remove overlap time table
            Date now = new Date();
            removeOverlapTime(list);
            for (Schedule s: list) {
                ArrayList<Date> startRun = new ArrayList<>();
                ArrayList<Date> endRun = new ArrayList<>();
                startRun.addAll(s.getStartRun());
                endRun.addAll(s.getEndRun());
                for (int i = 0; i < startRun.size(); i++) {
                    if (startRun.get(i).compareTo(now) <= 0 && endRun.get(i).compareTo(now) >= 0) {
                        return s;
                    }
                }
            }
            return null;
        }
    }


    /**
     * Remove any overlap in schedules
     * @param list - Array of all schedules
     */
    public static void removeOverlapTime(ArrayList<Schedule> list) {
        //Sort The List based on updated_at
        Collections.sort(list);
        //Generate start time array and end time array and store them in each schedule
        generateTimeTable(list);
        //Get the current value of start time and end time of first schedule in sorted list (The one with the last updated_at)
        ArrayList<Date> currentStartRun = new  ArrayList<Date>();
        currentStartRun.addAll(list.get(list.size()-1).getStartRun());
        ArrayList<Date> currentEndRun = new  ArrayList<Date>();
        currentEndRun.addAll(list.get(list.size()-1).getEndRun());

        for(int i = list.size() - 1; i > 0; i--) {
            //Time table of previous schedule
            ArrayList<Date> previousStartRun = list.get(i - 1).getStartRun();
            ArrayList<Date> previousEndRun = list.get(i - 1).getEndRun();
            for (int j = 0; j < previousStartRun.size(); j++) {
                //Compare them with the value of the second schedule
                for (int k = 0; k < currentStartRun.size(); k++) {
                    //Generate new start time and end time for second schedule (not overlap with the first one)
                    if (previousStartRun.get(j).getTime() < currentStartRun.get(k).getTime()
                            && previousEndRun.get(j).getTime() > currentStartRun.get(k).getTime()) {
                        previousEndRun.set(j, new Date(currentStartRun.get(k).getTime()));
                    } else if (previousStartRun.get(j).getTime() < currentStartRun.get(k).getTime()
                            && previousEndRun.get(j).getTime() >= currentStartRun.get(k).getTime()) {
                        previousEndRun.set(j, new Date(currentStartRun.get(k).getTime()));
                    } else if (previousStartRun.get(j).getTime() <= currentEndRun.get(k).getTime()
                            && previousEndRun.get(j).getTime() > currentEndRun.get(k).getTime()) {
                        previousStartRun.set(j, new Date(currentEndRun.get(k).getTime()));

                    } else if (previousStartRun.get(j).getTime() >= currentStartRun.get(k).getTime()
                            && previousEndRun.get(j).getTime() <= currentEndRun.get(k).getTime()) {
                        previousStartRun.set(j, new Date(1L));
                        previousEndRun.set(j, new Date(1L));
                    }
                }
            }
            for (int j = 0; j < previousStartRun.size(); j++) {
                if (previousStartRun.get(j).equals(new Date(1L))) {
                    previousStartRun.remove(j);
                    previousEndRun.remove(j);
                }

                list.get(i - 1).setStartRun(previousStartRun);
                list.get(i - 1).setEndRun(previousEndRun);

                //Combine those two into a new time table (2 new array of start time and run time)
                currentStartRun.addAll(previousStartRun);
                currentEndRun.addAll(previousEndRun);
            }

        }
    }

    /**
     * Based on the Schedule start_time, time_limit and next_run, set all the time a schedule should run
     * StartRun = array of all the start time of a schedule (StartRun[1] = StartRun[0] + next_run)
     * EndRun = array of all end time of a schedule (EndRun = startRun + TimeLimit)
     * @param list - Array of sorted schedules (sorted by update_at)
     */
    public static void generateTimeTable(ArrayList<Schedule> list){
        for(Schedule s: list) {
            s.setRunTime();
            s.setStartRun(s.getRunTime());
            ArrayList<Date> endRun = new ArrayList<>();
            for (int i = 0; i < s.getStartRun().size(); i++) {
                endRun.add(new Date(s.getStartRun().get(i).getTime() + s.getTimeLimit() * 60 * 1000));
            }
            s.setEndRun(endRun);
        }
    }
}
