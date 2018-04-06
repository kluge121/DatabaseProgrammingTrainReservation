package com.project.View;

import com.project.util.ViewSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessDialog extends JDialog implements ActionListener {


    private JLabel notiLabel;
    private JButton okBtn;
    private JButton cancleBtn;

    private JPasswordField passField;

    static final int NOT_NOTI = 0;
    static final int DATE_NOTI = 1;
    static final int TRAIN_NOTI = 2;


    int reserveId;

    AccessDialog() {


        setTitle("관리 권한 접근");
        setLayout(null);
        setResizable(false);
        this.setSize(250, 130);
        this.setModal(true);


        String noti = "관리 비밀번호를 입력해주세요";
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

        passField = new JPasswordField();
        passField.setBounds(60, 50, 140, 30);
        this.add(passField);


        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == okBtn) {


            String inputPass = new String(passField.getPassword());

            if (inputPass.equals(ViewSaver.AdminPASS)) {

                ViewSaver.getMain().settingRefresh("", NOT_NOTI);
                ViewSaver.getMain().cars = (CardLayout) ViewSaver.getMain().content.getLayout();
                ViewSaver.getMain().cars.show(ViewSaver.getMain().content, "setting");
                ViewSaver.accessOath = true;
                dispose();

            } else {
                AccessFailDialog accessFailDialog = new AccessFailDialog();
            }



        } else if (e.getSource() == cancleBtn) {
            dispose();


        }

    }
}
