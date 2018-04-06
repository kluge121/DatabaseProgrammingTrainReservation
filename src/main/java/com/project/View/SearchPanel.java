package com.project.View;

import com.project.util.UserInfo;
import com.project.models.Reserve;
import com.project.util.SessionHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

public class SearchPanel extends JPanel {

    private JTable searchTable;
    private JScrollPane tableScroll;
    private DefaultTableModel tableModel;
    private Vector<String> tableRow;


    SearchPanel() {

        String column[] = {"예매번호", "출발날짜", "기차번호", "출발역", "도착역", "출발시간", "도착시간", "좌석번호"};
        tableModel = new DefaultTableModel(null, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        setLayout(null);
        searchTable = new JTable(tableModel);
        searchTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        tableScroll = new JScrollPane(searchTable);
        tableScroll.setBounds(125, 50, 500, 430);
        this.add(tableScroll);


        List<Reserve> reserveList = getReserveList();


        if (reserveList.size() > 0) {
            for (int i = 0; i < reserveList.size(); i++) {
                tableRow = new Vector<String>();

                tableRow.add(String.valueOf(reserveList.get(i).getId()));
                tableRow.add(reserveList.get(i).getStart_date() + "");
                tableRow.add(reserveList.get(i).getTrain().getId().toString());
                tableRow.add(reserveList.get(i).getStart_station());
                tableRow.add(reserveList.get(i).getEnd_station());
                tableRow.add(reserveList.get(i).getStartTime());
                tableRow.add(reserveList.get(i).getEndTime());
                tableRow.add(reserveList.get(i).getSeat_num() + "");
                tableModel.addRow(tableRow);

            }

        }

        searchTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int row = searchTable.rowAtPoint(e.getPoint());
                int colum = searchTable.columnAtPoint(e.getPoint());

                if (e.getClickCount() == 1) {
//                    DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
//
//                    Session session = SessionHelper.getCurrentSession();
//                    Transaction tx = session.beginTransaction();
                    int id = Integer.parseInt((String) searchTable.getValueAt(row, 0));

//
//                    Reserve reserve = session.get(Reserve.class, id);
//
//                    session.delete(reserve);
//                    tx.commit();
                    CancleDialog cancleDialog = new CancleDialog(id);


                }
            }

        });

//        this.setVisible(true);
    }


    private List getReserveList() {

        Session session = SessionHelper.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query trainSearchQuery = session.createQuery("from Reserve as reserve " +
                "where reserve.user.id = :userid ");

        trainSearchQuery.setParameter("userid", UserInfo.getUserId());
        return (List<Reserve>) trainSearchQuery.list();

    }
}
