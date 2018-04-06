package com.project.util;

import com.project.models.Stop;

import java.util.Comparator;

public class StopCompare implements Comparator<Stop> {


    @Override
    public int compare(Stop o1, Stop o2) {


        int time1 = o1.getStart_time();
        int time2 = o2.getStart_time();


        if (time1 > time2) {
            return 1;
        } else if (time1 < time2) {
            return -1;
        } else {
            return 0;
        }
    }
}
