package com.project.View;

import com.project.util.TrainInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SeatPanel extends JPanel {

    SeatView[] seatViewArray = new SeatView[21];

    BufferedImage upBuffer;
    Image upicon;
    TrainInfo trainInfo;

    public SeatPanel(boolean[] seatBoolean, TrainInfo trainInfo) {


        this.trainInfo = trainInfo;

        upPanel upPanel = new upPanel();
        upPanel.setBounds(89, 10, 50, 50);
        this.add(upPanel);

        try {
            upBuffer = ImageIO.read(new File("img/up.png"));
            upicon = upBuffer.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int seatPosX = 15;
        int seatPosY = 80;
        int seatNum = 1;

        seatViewArray[0] = new SeatView(false, 0, trainInfo);
        for (int i = 0; i < 5; i++) {
            seatViewArray[seatNum] = new SeatView(seatBoolean[seatNum], seatNum, trainInfo);
            seatViewArray[seatNum].setBounds(seatPosX, seatPosY, 30, 30);
            this.add(seatViewArray[seatNum++]);
            seatPosX += 40;
            seatViewArray[seatNum] = new SeatView(seatBoolean[seatNum], seatNum, trainInfo);
            seatViewArray[seatNum].setBounds(seatPosX, seatPosY, 30, 30);
            this.add(seatViewArray[seatNum++]);
            seatPosX += 90;
            seatViewArray[seatNum] = new SeatView(seatBoolean[seatNum], seatNum, trainInfo);
            seatViewArray[seatNum].setBounds(seatPosX, seatPosY, 30, 30);
            this.add(seatViewArray[seatNum++]);
            seatPosX += 40;
            seatViewArray[seatNum] = new SeatView(seatBoolean[seatNum], seatNum, trainInfo);
            seatViewArray[seatNum].setBounds(seatPosX, seatPosY, 30, 30);
            this.add(seatViewArray[seatNum++]);
            seatPosY += 60;
            seatPosX = 15;
        }


    }

    class upPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            g.drawImage(upicon, 0, 0, null);
        }
    }
}
