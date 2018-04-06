package com.project.View;

import com.project.util.StationCode;
import com.project.util.TrainManager;
import com.project.util.ViewSaver;
import com.project.models.Date;
import com.project.models.Train;
import com.project.util.SessionHelper;
import com.toedter.calendar.JDateChooser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class SettingPanel extends JPanel implements ActionListener {


    private static final int DATE_NOTI = 1;
    private static final int TRAIN_NOTI = 2;

    private JButton dateAddButton;
    private JButton trainAddButton;

    private JLabel dateAddLabel;
    private JLabel trainAddLabel;

    private JLabel dateNotiLabel;

    public JLabel getDateNotiLabel() {
        return dateNotiLabel;
    }

    public JLabel getTrainNotiLabel() {
        return trainNotiLabel;
    }

    private JLabel trainNotiLabel;

    private JComboBox<Integer> hourCombo;
    private JComboBox<Integer> minCombo;
    private JComboBox<String> directionCombo;


    private JDateChooser jDateChooser;


    private JTable searchTable;
    private JScrollPane tableScroll;
    private DefaultTableModel tableModel;
    private Vector<String> tableRow;

    private JTable searchTable2;
    private JScrollPane tableScroll2;
    private DefaultTableModel tableModel2;
    Vector<String> tableRow2;

    private JLabel selectHourLabel;
    private JLabel selectMinLabel;
    private JLabel selectDirectionLabel;
    private JLabel stopStationLabel;

    private JCheckBox checkSeoul;
    private JCheckBox checkCheonan;
    private JCheckBox checkDaejeon;
    private JCheckBox checkDongdaegu;
    private JCheckBox checkDaegu;
    private JCheckBox checkUlsan;
    private JCheckBox checkGyungju;
    private JCheckBox checkBusan;

    private int trainAdd_startInfo;
    private int trainAdd_endInfo;
    private int trainAdd_StartTime;
    private int trainAdd_direction;
    private int trainAdd_stopInfo;


    SettingPanel() {
        setWidget(); // 위젯초기화
        getDateTableContent(); // 날짜 테이블 데이터 채우기
        getTrainTableContent();// 기차 테이블 데이터 채우기


    }

    private void setWidget() {
        setLayout(null);

        Font font = new Font("Courier", Font.BOLD, 12);

        dateAddLabel = new JLabel("날짜 추가하기");
        dateAddLabel.setFont(font.deriveFont(font.getStyle() | Font.BOLD));
        dateAddLabel.setBounds(40, 60, 80, 20);
        this.add(dateAddLabel);

        dateNotiLabel = new JLabel();
        dateNotiLabel.setBounds(40, 150, 200, 20);
        dateNotiLabel.setForeground(Color.RED);
        this.add(dateNotiLabel);


        trainAddLabel = new JLabel("열차편 추가하기");
        trainAddLabel.setFont(font.deriveFont(font.getStyle() | Font.BOLD));
        trainAddLabel.setBounds(40, 200, 120, 20);
        this.add(trainAddLabel);

        trainNotiLabel = new JLabel();
        trainNotiLabel.setBounds(40, 515, 200, 20);
        trainNotiLabel.setForeground(Color.RED);
        this.add(trainNotiLabel);


        String column2[] = {"열차번호", "출발역", "도착역", "출발시간", "정차정보"};
        tableModel2 = new DefaultTableModel(null, column2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        searchTable2 = new JTable(tableModel2);
        tableScroll2 = new JScrollPane(searchTable2);
        tableScroll2.setBounds(350, 255, 400, 150);
        this.add(tableScroll2);


        String column[] = {"운행 날짜"};
        tableModel = new DefaultTableModel(null, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        searchTable = new JTable(tableModel);
        tableScroll = new JScrollPane(searchTable);
        tableScroll.setBounds(350, 55, 100, 150);
        this.add(tableScroll);


        jDateChooser = new JDateChooser();
        jDateChooser.setDate(new java.util.Date());
        jDateChooser.setDateFormatString("yyyy-MM-dd");
        jDateChooser.setBounds(40, 90, 155, 30);
        this.add(jDateChooser);


        dateAddButton = new JButton("날짜추가");
        dateAddButton.setBounds(40, 120, 80, 35);
        dateAddButton.addActionListener(this);
        this.add(dateAddButton);

        Integer[] hourValue = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        Integer[] minValue = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59};
        String[] directioinValue = {"상행", "하행"};

        hourCombo = new JComboBox<Integer>(hourValue);
        minCombo = new JComboBox<Integer>(minValue);
        directionCombo = new JComboBox<String>(directioinValue);

        hourCombo.setBounds(80, 225, 80, 20);
        minCombo.setBounds(80, 255, 80, 20);
        directionCombo.setBounds(80, 285, 80, 20);

        this.add(hourCombo);
        this.add(minCombo);
        this.add(directionCombo);

        selectHourLabel = new JLabel("출발 시");
        selectMinLabel = new JLabel("출발 분");
        selectDirectionLabel = new JLabel("방향선택");
        selectHourLabel.setBounds(40, 225, 60, 20);
        selectMinLabel.setBounds(40, 255, 60, 20);
        selectDirectionLabel.setBounds(33, 285, 60, 20);

        this.add(selectHourLabel);
        this.add(selectMinLabel);
        this.add(selectDirectionLabel);


        stopStationLabel = new JLabel("정차역 선택");
        stopStationLabel.setBounds(70, 330, 100, 20);
        this.add(stopStationLabel);


        checkSeoul = new JCheckBox("서울역");
        checkCheonan = new JCheckBox("천안아산역");
        checkDaejeon = new JCheckBox("대전역");
        checkDongdaegu = new JCheckBox("동대구역");

        checkDaegu = new JCheckBox("대구역");
        checkUlsan = new JCheckBox("울산역");
        checkGyungju = new JCheckBox("경주역");
        checkBusan = new JCheckBox("부산역");

        checkSeoul.setBounds(30, 350, 80, 30);
        checkCheonan.setBounds(30, 380, 88, 30);
        checkDaejeon.setBounds(30, 410, 80, 30);
        checkDongdaegu.setBounds(30, 440, 80, 30);

        checkDaegu.setBounds(115, 350, 80, 30);
        checkUlsan.setBounds(115, 380, 80, 30);
        checkGyungju.setBounds(115, 410, 80, 30);
        checkBusan.setBounds(115, 440, 80, 30);

        this.add(checkSeoul);
        this.add(checkCheonan);
        this.add(checkDaejeon);
        this.add(checkDongdaegu);
        this.add(checkDaegu);
        this.add(checkUlsan);
        this.add(checkGyungju);
        this.add(checkBusan);

        trainAddButton = new JButton("열차편 추가");
        trainAddButton.setBounds(60, 480, 80, 35);
        trainAddButton.addActionListener(this);
        this.add(trainAddButton);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dateAddButton) {

            String noti = null;
            String strDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            try {
                strDate = sdf.format(jDateChooser.getDate());
            } catch (NullPointerException e1) {
                noti = "날짜를 정확하게 입력해 주세요";
                ViewSaver.getMain().settingRefresh(noti, DATE_NOTI);
            }


            int intDate = Integer.parseInt(strDate);

            Calendar calendar = Calendar.getInstance();

            int sum = (calendar.get(Calendar.YEAR) * 10000) + ((calendar.get(Calendar.MONTH) + 1) * 100) + calendar.get(Calendar.DATE);

            if (sum > intDate) {
                noti = "지난 날짜입니다";
                ViewSaver.getMain().settingRefresh(noti, DATE_NOTI);
                return;
            }


            Session session = SessionHelper.getCurrentSession();
            Transaction tx = session.beginTransaction();

            Date date = session.get(Date.class, intDate);

            if (date == null) {
                Date inpuDate = new Date(intDate);
                session.save(inpuDate);
            }
            tx.commit();

            if (date == null) {
                noti = "날짜가 추가되었습니다";
            } else {
                noti = "해당 날짜는 이미 추가되어 있습니다.";
            }


            ViewSaver.getMain().settingRefresh(noti, DATE_NOTI);

        } else if (e.getSource() == trainAddButton) {

            Session session = SessionHelper.getCurrentSession();
            Transaction tx = session.beginTransaction();

            if (directionCombo.getSelectedIndex() == 0) {
                trainAdd_direction = 1;
            } else {
                trainAdd_direction = 0;
            }


            if (!stopinfoCreate()) {
                trainNotiLabel.setText("정차역을 2개이상 선택해주세요");
                return;
            }


            trainAdd_StartTime = hourCombo.getSelectedIndex() * 100 + minCombo.getSelectedIndex();
            TrainManager.trainInsert(session, 1, 20, trainAdd_startInfo, trainAdd_endInfo, "KTX", trainAdd_stopInfo, trainAdd_direction, trainAdd_StartTime);

            tx.commit();
            String noti = "열차편이 추가되었습니다.";
            ViewSaver.getMain().settingRefresh(noti, TRAIN_NOTI);

        }

    }


    private boolean stopinfoCreate() {

        trainAdd_stopInfo = 0;
        trainAdd_startInfo = 0;
        trainAdd_endInfo = 0;
        JCheckBox[] checkCompo = {checkSeoul, checkCheonan, checkDaejeon, checkDongdaegu, checkDaegu, checkUlsan, checkGyungju, checkBusan};
        int[] station = {256, 128, 64, 32, 16, 8, 4, 2};
        ArrayList<Integer> stopList = new ArrayList<Integer>();


        for (int i = 0; i < 8; i++) {
            if (checkCompo[i].isSelected()) {
                trainAdd_stopInfo += station[i];

                stopList.add(station[i]);
            }
        }

        if (stopList.size() < 2) return false;


        if (trainAdd_direction == 0) {
            trainAdd_startInfo = stopList.get(0);
            trainAdd_endInfo = stopList.get(stopList.size() - 1);
        } else if (trainAdd_direction == 1) {
            trainAdd_startInfo = stopList.get(stopList.size() - 1);
            trainAdd_endInfo = stopList.get(0);
        }

        trainAdd_stopInfo += trainAdd_direction;

        return true;

    }


    private void getDateTableContent() {

        Session session = SessionHelper.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query getDate = session.createQuery("from Date as date ");
        List<Date> dateList = getDate.list();

        for (int i = 0; i < dateList.size(); i++) {
            tableRow = new Vector<String>();
            tableRow.add(dateList.get(i).getDate_id() + "");
            tableModel.addRow(tableRow);

        }


    }

    private void getTrainTableContent() {
        Session session = SessionHelper.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query getTrain = session.createQuery("from Train as train ORDER BY train.starttime asc ");
        List<Train> trainList = getTrain.list();

        for (int i = 0; i < trainList.size(); i++) {
            int hour = trainList.get(i).getStarttime() / 100;
            int min = trainList.get(i).getStarttime() % 100;

            String strHour;
            String strMin;
            if (hour >= 10) {
                strHour = hour + ":";

            } else {
                strHour = "0" + hour + ":";
            }

            if (min > 10) {
                strMin = min + "";
            } else {

                strMin = "0" + min;
            }

            tableRow = new Vector<String>();
            tableRow.add(trainList.get(i).getId() + "");
            tableRow.add(StationCode.intToStation(trainList.get(i).getStartStation()));
            tableRow.add(StationCode.intToStation(trainList.get(i).getEndStation()));
            tableRow.add(strHour + strMin);
            tableRow.add(trainList.get(i).getStopInfo() + "");

            tableModel2.addRow(tableRow);

        }

    }


}
