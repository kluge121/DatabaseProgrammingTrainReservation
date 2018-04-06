package com.project.util;

import com.project.models.Seat;
import com.project.models.Station;
import com.project.models.Stop;
import com.project.models.Train;
import org.hibernate.Session;

import java.util.Date;

public class TrainManager {

    //    static String[] stationArray = {"부산역", "경주역", "울산역", "대구역", "동대구역", "대전", "천안아산", "서울"};
//    static int []  stationArray2 = {1,2,4,8,16,32,64,128};
//    diretion = 0 서울>부산    1 부산>서울
    private static int[] useTime = {0, 40, 20, 45, 5, 15, 10, 20, 0};


    public static void trainInsert(Session session, int carCount, int seatCount, int start, int end, String type, int stopinfo, int direction, int startTime) {


        int shift;
        int i = 0;
        int start_time = startTime;
        int tmpCarCount = 1;
        int tmpSeatCount = 1;
        Train train = new Train(type, carCount, seatCount, start, end, stopinfo, direction, startTime);
        session.save(train);

        for (; tmpCarCount <= carCount; tmpCarCount++) {
            for (tmpSeatCount = 1; tmpSeatCount <= seatCount; tmpSeatCount++) {
                Seat seat = new Seat(tmpCarCount, tmpSeatCount, train);
                session.save(seat);
            }

        }

        if (direction == 0) {
            i = 1;

            shift = 512;
            for (; i <= 8; ) {

                shift = shift >> 1;
                if ((shift & stopinfo) > 0) {

                    Station station = session.get(Station.class, shift);


                    Stop stop = new Stop();
                    stop.setTrain(train);
                    stop.setStation(station);
                    stop.setDirection(direction);
                    stop.setStart_time(start_time);

                    session.save(stop);

                }

                start_time = TrainManager.calculateTime(start_time, i++);

            }
        } else if (direction == 1) {
            i = 7;
            shift = 1;
            for (; i >= 0; ) {

                shift = shift << 1;
                if ((shift & stopinfo) > 0) {

                    Station station = session.get(Station.class, shift);
                    Stop stop = new Stop();
                    stop.setTrain(train);
                    stop.setStation(station);
                    stop.setDirection(direction);
                    stop.setStart_time(start_time);

                    session.save(stop);

                }

                start_time = TrainManager.calculateTime(start_time, i--);

            }
        }

    }

    private static int calculateTime(int time, int index) {

        int sumTime = time + useTime[index];
        int hour = sumTime / 100;
        int min = sumTime % 100;


        if (sumTime % 100 >= 60) {
            hour += 1;
            min -= 60;
            sumTime = hour * 100 + min;
        }

        if (sumTime % 100 == 24) {
            sumTime = sumTime - 2400;
        }

        return sumTime;
    }


    public static int dateCompare(Date nowDate, Date selectDate) {


        if (nowDate.getYear() == selectDate.getYear() && nowDate.getMonth() == selectDate.getMonth()) {

            if (nowDate.getDay() > selectDate.getDay())
                return -1;


            else
                return 1;
        } else {

            if (nowDate.getTime() < selectDate.getTime())
                return 1;

            else
                return -1;


        }


    }
}
