package com.project;

import com.project.models.Date;
import com.project.models.Station;
import com.project.util.SessionHelper;
import com.project.util.StationCode;
import com.project.util.TrainManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TrainDummyInsert {


    public static void main(String args[]) throws Exception {

        Session session = SessionHelper.getCurrentSession();
        Transaction tx = session.beginTransaction();

        int tmp = 512;
        for (int i = 0; i < 8; i++) {

            tmp = tmp / 2;
            Station station = new Station();
            station.setStation_code(tmp);
            session.save(station);

        }

        Date date = new Date(20171126);
        session.save(date);


        TrainManager.trainInsert(session, 1, 20, StationCode.SEOUL, StationCode.BUSAN, "KTX", 354, 0, 500);
        TrainManager.trainInsert(session, 1, 20, StationCode.BUSAN, StationCode.SEOUL, "KTX", 355, 1, 500);

        TrainManager.trainInsert(session, 1, 20, StationCode.SEOUL, StationCode.BUSAN, "KTX", 510, 0, 630);
        TrainManager.trainInsert(session, 1, 20, StationCode.BUSAN, StationCode.SEOUL, "KTX", 511, 1, 630);

        tx.commit();


    }


}
