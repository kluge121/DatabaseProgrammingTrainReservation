package com.project.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SessionHelper {

    public static final SessionFactory sessionFactory;

    static {

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
        } catch (Throwable ex) {
            System.out.println("세션 팩토리 초기화 실패");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }


    public static Transaction beginTransaction(){
        return getCurrentSession().beginTransaction();
    }

    public static void commit(){
        getCurrentSession().getTransaction().commit();
    }

    public static void closeSession(){
        getCurrentSession().close();
    }

    public static void rollback(){
        if(getCurrentSession().isOpen()){
            Transaction tx = getCurrentSession().getTransaction();
            if(tx != null)
                tx.rollback();
        }
    }






}
