package com.project.View;

import com.project.util.StationCode;
import com.project.util.ViewSaver;
import com.project.models.Reserve;
import com.project.models.ReserveSeat;
import com.project.util.SessionHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancleDialog extends JDialog implements ActionListener {


    JLabel notiLabel;
    JButton okBtn;
    JButton cancleBtn;


    int reserveId;

    public CancleDialog(int reserveId) {

        this.reserveId = reserveId;

        setTitle("예약 취소");
        setLayout(null);
        setResizable(false);
        this.setSize(250, 130);
        this.setModal(true);


        String noti = "예약을 취소하시겠습니까?";
        notiLabel = new JLabel(noti);
        notiLabel.setBounds(55, 30, 170, 20);
        this.add(notiLabel);

        okBtn = new JButton("예");
        okBtn.setBounds(60, 80, 70, 25);
        okBtn.addActionListener(this);
        this.add(okBtn);

        cancleBtn = new JButton("아니오");
        cancleBtn.setBounds(120, 80, 70, 25);
        cancleBtn.addActionListener(this);
        this.add(cancleBtn);


        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == okBtn) {

            Session session = SessionHelper.getCurrentSession();
            Transaction tx = session.beginTransaction();
            Reserve reserve = session.get(Reserve.class, reserveId);


            Query getTrain = session.createQuery("from ReserveSeat as reserveSeat " +
                    "where reserveSeat.train_num = :train_id and reserveSeat.seat_num = :seat_num");

            getTrain.setParameter("train_id", reserve.getTrain().getId());
            getTrain.setParameter("seat_num", reserve.getSeat_num());

            ReserveSeat reserveSeat = (ReserveSeat) getTrain.list().get(0);

            int info = reserveSeat.getSeat_info();


            int startInfo = StationCode.staionToCode(reserve.getStart_station());
            int endInfo = StationCode.staionToCode(reserve.getEnd_station());

            if (startInfo > endInfo) {
                while (startInfo > endInfo) {
                    if ((startInfo & info) > 0)
                        info = info - startInfo;
                    startInfo = startInfo / 2;
                }
            } else if (startInfo < endInfo) {
                while (startInfo < endInfo) {
                    if ((startInfo & info) > 0)
                        info = info - startInfo;
                    startInfo = startInfo * 2;
                }
            }


            if(info==0){
                session.delete(reserveSeat);
            }else{
                reserveSeat.setSeat_info(info);
                session.update(reserveSeat);
            }


            session.delete(reserve);
            tx.commit();
            dispose();

            ViewSaver.getMain().searchRefresh();



        } else if (e.getSource() == cancleBtn) {
            dispose();
        }

    }
}
