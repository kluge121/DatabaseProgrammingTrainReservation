package com.project.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessFailDialog extends JDialog implements ActionListener {

    JLabel noti;
    JButton ok;


    public AccessFailDialog() {
        setLayout(null);
        setResizable(false);
        this.setSize(200, 140);
        this.setModal(true);

        noti = new JLabel("암호가 틀렸습니다");
        ok = new JButton("확인");

        noti.setBounds(50, 30, 100, 30);
        this.add(noti);

        ok.setBounds(60, 70, 80, 30);
        ok.addActionListener(this);
        this.add(ok);


        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ok) {
            dispose();

        }

    }
}
