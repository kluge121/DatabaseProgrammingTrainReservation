package com.project.View;

import com.project.util.StationCode;
import com.project.util.TrainInfo;
import com.project.util.UserInfo;
import com.project.util.ViewSaver;
import com.project.models.*;
import com.project.util.SessionHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReserveDialog extends JDialog implements ActionListener {


    private JLabel notiLabel;
    private JButton okBtn;
    private JButton cancleBtn;
    private TrainInfo trainInfo;
    private int seatNum;
    int startInfo;
    private int endInfo;

    private SeatView myParent;

    public ReserveDialog(TrainInfo trainInfo, int seatNum, SeatView myParent) {
        this.myParent = myParent;
        this.trainInfo = trainInfo;
        this.seatNum = seatNum;
        this.startInfo = trainInfo.getStartStation();
        this.endInfo = trainInfo.getEndStation();

        setTitle("예약하기");
        setLayout(null);
        setResizable(false);
        this.setSize(250, 130);
        this.setModal(true);


        String noti = trainInfo.getTrainId() + "편 열차를 예약하시겠습니까?";

        notiLabel = new JLabel(noti);
        notiLabel.setBounds(55, 30, 170, 20);
        this.add(notiLabel);


        okBtn = new JButton("예약");
        okBtn.setBounds(60, 80, 70, 25);
        okBtn.addActionListener(this);
        this.add(okBtn);

        cancleBtn = new JButton("취소");
        cancleBtn.setBounds(120, 80, 70, 25);
        cancleBtn.addActionListener(this);
        this.add(cancleBtn);


        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == okBtn) {
            Reserve();
            dispose();

        } else if (e.getSource() == cancleBtn) {
            dispose();
        }

    }


    void Reserve() {

        Session session = SessionHelper.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query getReserveSeat = session.createQuery("from ReserveSeat as reserveSeat " +
                "where reserveSeat.train_num = :train_id and reserveSeat.date.date_id = :date and reserveSeat.seat_num = :seatNum");
        getReserveSeat.setParameter("train_id", trainInfo.getTrainId());
        getReserveSeat.setParameter("date", trainInfo.getDate());
        getReserveSeat.setParameter("seatNum", seatNum);

        List<ReserveSeat> list = getReserveSeat.list();
        System.out.println("코드" + list.size());


        //////////////////////////////////////////////////////////////////////
        Query seatQuery = session.createQuery("from Seat as seat " +
                "where seat.seat_num = :seatNum and  seat.train.id = :trainId");

        seatQuery.setParameter("seatNum", seatNum);
        seatQuery.setParameter("trainId", trainInfo.getTrainId());


        List<Seat> seatsArray = seatQuery.list();

//        Query dateQuery = session.createQuery("from Date as date " +
//                "where date.date_id = :date");
//        dateQuery.setParameter("date", trainInfo.getDate());
//
//        System.out.println(seatsArray.size() + "개");

        Seat seat = seatsArray.get(0);
//        Date date = (Date) dateQuery.list().get(0);
        Date date = session.get(Date.class,trainInfo.getDate());
        //////////////////////////////////////////////////////////////////////

        if (list.size() > 0) {
            System.out.println("코드 있다");

            ReserveSeat reserveSeat = list.get(0);

            int tmpSeatinfo = reserveSeat.getSeat_info();

            if (startInfo > endInfo) {
                while (startInfo > endInfo) {
                    tmpSeatinfo = tmpSeatinfo + startInfo;
                    startInfo = startInfo / 2;
                }
            } else if (startInfo < endInfo) {

                while (startInfo < endInfo) {
                    tmpSeatinfo = tmpSeatinfo + startInfo;
                    startInfo = startInfo * 2;
                }

            }
            reserveSeat.setSeat_info(tmpSeatinfo);
            session.save(reserveSeat);

        } else {

            //해당 좌석에 예약정보가 없는경우 아예새로만듬
            System.out.println("코드 없다");

            int seatinfo = 0;

            if (startInfo > endInfo) {
                while (startInfo > endInfo) {
                    seatinfo = startInfo + seatinfo;
                    startInfo = startInfo / 2;
                }
            } else if (startInfo < endInfo) {
                while (startInfo < endInfo) {
                    seatinfo = startInfo + seatinfo;
                    startInfo = startInfo * 2;
                }

            }


//            seatQuery.setParameter("seatNum", seatNum);
//            seatQuery.setParameter("trainId", trainInfo.getTrainId());

            ReserveSeat reserveSeat = new ReserveSeat(trainInfo.getTrainId(), 1, seatNum, seatinfo);
            reserveSeat.setDate(date);
            reserveSeat.setSeat(seat);
            session.save(reserveSeat);

        }
//
//        Query getTrain = session.createQuery("from Train as train " +
//                "where train.id = :train_id");
//        getTrain.setParameter("train_id", trainInfo.getTrainId());
//        Train train = (Train) getTrain.list().get(0);
        Train train = session.get(Train.class, trainInfo.getTrainId());


        Reserve reserve = new Reserve("KTX", 1, seatNum, trainInfo.getDate());


        System.out.print("도착시간체크" + trainInfo.getEndTime());
        reserve.setStartTime(trainInfo.getStartTime());
        reserve.setEndTime(trainInfo.getEndTime());
        reserve.setStart_station(StationCode.intToStation(trainInfo.getStartStation()));
        reserve.setEnd_station(StationCode.intToStation(trainInfo.getEndStation()));
        reserve.setUser(UserInfo.getUser());
        reserve.setTrain(train);

        session.save(reserve);
        tx.commit();

        ViewSaver.getReservePanel().hereAdd(trainInfo.getTrainId(), trainInfo.getDate(), trainInfo.getStartTime(), trainInfo.getEndTime());


    }
}
