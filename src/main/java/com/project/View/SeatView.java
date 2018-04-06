package com.project.View;

import com.project.util.TrainInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class SeatView extends JPanel {


    private BufferedImage img;
    Image resizeImage;
    int seatNum; // 나는 몇번째 좌석일까용?? 을 나타내는 변수
    TrainInfo trainInfo;
    SeatView iSeatview;
    JLabel seatLabel;

    public SeatView(boolean bool, int seatNum, TrainInfo trainInfo) {

        iSeatview = this;
        setLayout(null);
        this.trainInfo = trainInfo;
        this.seatNum = seatNum;
        setSize(30, 30);

        seatLabel = new JLabel(seatNum+"");
        seatLabel.setBounds(10,10,20,10);
        seatLabel.setForeground(Color.WHITE);
        this.add(seatLabel);


        try {
            if (bool) {
                img = ImageIO.read(new File("img/black.png"));
                resizeImage = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            } else if (!bool) {
                img = ImageIO.read(new File("img/red.png"));
                resizeImage = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }


        InnerPanel innerPanel = new InnerPanel();
        innerPanel.setBounds(0, 0, 30, 30);
        innerPanel.setOpaque(false);

        add(innerPanel);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (bool) {
                    ReserveDialog reserveDialog = new ReserveDialog(trainInfo, seatNum, iSeatview);

                }


            }
        });

    }

    class InnerPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(resizeImage, 0, 0, null);
        }
    }

    void seatRefresh() {
        this.repaint();
        this.revalidate();
    }


}
