package com.project.View;

import com.project.models.ReserveSeat;
import com.project.models.Stop;
import com.project.util.*;
import com.toedter.calendar.JDateChooser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ReservePanel extends JPanel implements ActionListener {

    private JLabel dateLabel;
    private JLabel timeLabel;
    private JLabel startLabe;
    private JLabel endLabel;
    private JLabel notiLabel;
    private JLabel selectInfoLabel;

    private JLabel priceLabel;


    private JComboBox<Integer> timeComboBox;
    private JComboBox<String> startComboBox;
    private JComboBox<String> endComboBox;

    private JDateChooser jDateChooser;

    private JButton seachBtn;

    private JTable searchTable;
    private JScrollPane tableScroll;
    private DefaultTableModel tableModel;
    private Vector<String> tableRow;

    private SeatPanel seatPanel;

    private static final String pattern = "yyyy-MM-dd";

    private int date;
    private int startInfo;
    private int endInfo;


    void hereAdd(int trainId, int date, String startTime, String endTime) {

        if (seatPanel != null) {

            this.remove(seatPanel);
        }

        boolean[] seatBoolean;
        Session session = SessionHelper.getCurrentSession();
        Transaction tx = session.beginTransaction();

//        Query getSeat = session.createQuery("from Seat as seat " +
//                "where seat.train.id = :train_id");
//        getSeat.setParameter("train_id", trainId);
//        List<SeatView> seatViewList = getSeat.list();


        Query getReserveSeat = session.createQuery("from ReserveSeat as reserveSeat " +
                "where reserveSeat.train_num = :train_id and reserveSeat.date.date_id = :date ");
        getReserveSeat.setParameter("train_id", trainId);
        getReserveSeat.setParameter("date", date);

        List<ReserveSeat> reserveSeatList = getReserveSeat.list();


        seatBoolean = new boolean[21];


        for (int i = 0; i < 21; i++) { // 일단 모든좌석 예약 가능하게 판별
            seatBoolean[i] = true;
        }
        for (int i = 0; i < reserveSeatList.size(); i++) {  //예약좌석 비교해서 불가 설정

            int tmpStart = startInfo;
            int tmpEnd = endInfo;
            int tmpSeatInfo = reserveSeatList.get(i).getSeat_info();
            int tmpSeatNum = reserveSeatList.get(i).getSeat_num();


            if (tmpStart > tmpEnd) {  //하행선체크 서울>부산방향
                while (tmpStart > tmpEnd) {
                    if ((tmpSeatInfo & tmpStart) > 0) {
                        System.out.println(tmpSeatNum);
                        seatBoolean[tmpSeatNum] = false;
                        break;
                    }
                    tmpStart /= 2;
                }
            } else if (tmpStart < tmpEnd) {  //상행선체크 부산>서울방향
                while (tmpStart < tmpEnd) {
                    if ((tmpSeatInfo & tmpStart) > 0) {
                        seatBoolean[tmpSeatNum] = false;
                        break;
                    }
                    tmpStart *= 2;
                }
            }
        }

        TrainInfo trainInfo = new TrainInfo();
        trainInfo.setDate(date);
        trainInfo.setTrainId(trainId);
        trainInfo.setEndStation(endInfo);
        trainInfo.setStartStation(startInfo);
        trainInfo.setStartTime(startTime);
        trainInfo.setEndTime(endTime);


        seatPanel = new SeatPanel(seatBoolean, trainInfo);


        seatPanel.setLayout(null);
        seatPanel.setBounds(450, 100, 230, 400);
        seatPanel.setBackground(Color.WHITE);
        this.add(seatPanel);


        this.add(selectInfoLabel);

        seatPanel.setVisible(true);
        seatPanel.revalidate();
        seatPanel.repaint();
        this.revalidate();
        this.repaint();


    }

    ReservePanel() {
        setLayout(null);

        priceLabel = new JLabel();
        priceLabel.setBounds(80, 150, 80, 20);
        this.add(priceLabel);

        notiLabel = new JLabel();
        dateLabel = new JLabel("출발날짜");
        timeLabel = new JLabel("출발시간");
        startLabe = new JLabel("출발역");
        endLabel = new JLabel("도착역");


        dateLabel.setBounds(30, 30, 50, 20);
        timeLabel.setBounds(30, 60, 50, 20);
        startLabe.setBounds(30, 90, 50, 20);
        endLabel.setBounds(30, 120, 50, 20);
        notiLabel.setBounds(80, 150, 250, 20);

        notiLabel.setForeground(Color.RED);

        this.add(dateLabel);
        this.add(timeLabel);
        this.add(endLabel);
        this.add(startLabe);
        this.add(notiLabel);
        ///////////////////////////////////////

        jDateChooser = new JDateChooser();
        jDateChooser.setDateFormatString("yyyy-MM-dd");
        jDateChooser.setBounds(90, 27, 150, 30);
        jDateChooser.setDate(new java.util.Date());
        this.add(jDateChooser);

        Integer[] timeCombo = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        timeComboBox = new JComboBox<Integer>(timeCombo);
        timeComboBox.setBounds(88, 62, 70, 20);
        this.add(timeComboBox);

        String stationCombo[] = {"부산역", "경주역", "울산역", "대구역", "동대구역", "대전역", "천안아산역", "서울역"};
        startComboBox = new JComboBox<String>(stationCombo);
        endComboBox = new JComboBox<String>(stationCombo);

        startComboBox.setBounds(88, 91, 120, 20);
        endComboBox.setBounds(88, 121, 120, 20);

        startComboBox.setSelectedIndex(7);

        this.add(startComboBox);
        this.add(endComboBox);

        seachBtn = new JButton("조회");
        seachBtn.setBounds(30, 152, 40, 15);
        seachBtn.addActionListener(this);
        this.add(seachBtn);

        String column[] = {"기차번호", "출발역", "도착역", "출발시간", "도착시간"};
        tableModel = new DefaultTableModel(null, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        searchTable = new JTable(tableModel);
        tableScroll = new JScrollPane(searchTable);
        tableScroll.setBounds(30, 190, 300, 300);
        this.add(tableScroll);

        searchTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int row = searchTable.rowAtPoint(e.getPoint());
                int colum = searchTable.columnAtPoint(e.getPoint());

                if (e.getClickCount() == 1) {
                    DefaultTableModel model = (DefaultTableModel) searchTable.getModel();

                    String startTime = (String) searchTable.getValueAt(row, 3);
                    String endTime = (String) searchTable.getValueAt(row, 4);

                    String sb = String.valueOf(searchTable.getValueAt(row, 0)) +
                            "편 " +
                            "  출발역:" + searchTable.getValueAt(row, 1) + " " +
                            "  도착역:" + searchTable.getValueAt(row, 2) + " " +
                            "  출발시간:" + startTime + " " +
                            "  도착시간:" + endTime + " ";
                    if (selectInfoLabel != null)
                        ViewSaver.getReservePanel().remove(selectInfoLabel);
                    selectInfoLabel = new JLabel();
                    selectInfoLabel.setBounds(380, 65, 400, 20);
                    selectInfoLabel.setText(sb);
                    hereAdd(Integer.parseInt((String) searchTable.getValueAt(row, 0)), date, startTime, endTime); // 좌석선택표 추기
                    seatPanel.setVisible(true);
                }
            }

        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == seachBtn) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date mDate = jDateChooser.getDate();


            if (mDate == null) {
                notiLabel.setText("날짜를 선택해주세요");
                priceLabel.setText("");
                return;
            }


            if (TrainManager.dateCompare(new Date(), mDate) == -1) {
                notiLabel.setText("지난 날짜입니다");
                priceLabel.setText("");
                return;
            }

            String strDate = sdf.format(mDate);
            date = Integer.parseInt(strDate);


            int startTime = (int) timeComboBox.getSelectedItem();

            int startIndex = startComboBox.getSelectedIndex();
            int endIndex = endComboBox.getSelectedIndex();

            startInfo = 1 << startIndex + 1;
            endInfo = 1 << endIndex + 1;

            int direction;
            if (startIndex > endIndex) {
                direction = 0;
            } else if (startIndex < endIndex) {
                direction = 1;
            } else {
                notiLabel.setText("출발역과 도착역을 올바르게 설정해주세요.");
                priceLabel.setText("");
                return;
            }
            Session session = SessionHelper.getCurrentSession();
            Transaction tx = session.beginTransaction();

            Query dateSearchQuery = session.createQuery("from Date as date " +
                    "where date.date_id = :date");

            dateSearchQuery.setParameter("date", date);

            if (dateSearchQuery.list().size() == 0) {
                notiLabel.setText("해당 날짜 열차는 아직 편성되지 않았습니다.");
                priceLabel.setText("");
                return;
            }
            int searchInfo = startInfo + endInfo;
            ///////////////////////////////////////////////////////////

            Query trainSearchQuery = session.createQuery("from Stop as stop " +
                    "where stop.direction = :direction and stop.start_time >= :startTime ORDER BY stop.train.id asc ");

            trainSearchQuery.setParameter("direction", direction);
            trainSearchQuery.setParameter("startTime", startTime * 100);

            System.out.println("한" + startTime);

            List<Stop> StopList = trainSearchQuery.list();

            ///////////////////////////////////////////////////////////
            List<Stop> startStopList = new ArrayList<Stop>();
            List<Stop> endStopList = new ArrayList<Stop>();


            tableModel
                    .setNumRows(0);


            for (Stop stop : StopList) {
                if (searchInfo == (stop.getTrain().getStopInfo() & searchInfo)) {
                    if (stop.getStation().getStation_code() == endInfo) {
                        endStopList.add(stop);
                    }
                    if (stop.getStation().getStation_code() == startInfo) {
                        startStopList.add(stop);
                    }
                }
            }

            StopCompare sc = new StopCompare();
            startStopList.sort(sc);
            endStopList.sort(sc);


            for (int i = 0; i < startStopList.size(); i++) {
                tableRow = new Vector<String>();
                tableRow.add(startStopList.get(i).getTrain().getId().toString());
                tableRow.add(StationCode.intToStation(startInfo));
                tableRow.add(StationCode.intToStation(endInfo));
                tableRow.add(startStopList.get(i).timeToStirng());
                tableRow.add(endStopList.get(i).timeToStirng());
                tableModel.addRow(tableRow);

            }


            if (startInfo > endInfo) {
                priceLabel.setText(getPrice(startInfo, endInfo).toString() + "원");

            } else {
                priceLabel.setText(getPrice(endInfo, startInfo).toString() + "원");

            }


            if (tableModel.getRowCount() == 0) {
                notiLabel.setText("조회된 열차가 없습니다");
                priceLabel.setText("");
                return;
            }
            notiLabel.setText("");
            if (seatPanel != null) {

                seatPanel.setVisible(false);
                selectInfoLabel.setText("");
            }


        }


    }


    private Integer getPrice(int highInfo, int lowInfo) {

        int tmp = highInfo / lowInfo;


        switch (tmp) {

            case 128:
                return 56000;
            case 64:
                return 48000;
            case 32:
                return 40000;
            case 16:
                return 32000;
            case 8:
                return 24000;
            case 4:
                return 16000;
            case 2:
                return 8000;

            default:
                return 0;
        }


    }

}
