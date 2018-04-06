package com.project;

import com.project.models.Train;
import com.project.util.SessionHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RemoveTest {


    public static void main(String args[]) throws Exception {

        Session session = SessionHelper.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Train train = session.get(Train.class,1);

        session.delete(train);

        tx.commit();


    }

}
