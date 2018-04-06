package com.project.View;

import com.project.util.ViewSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {


    JPanel root;
    JPanel menu;
    public JPanel content;

    ReservePanel jReserve;
    SettingPanel jSetting;
    SearchPanel jSearch;


    JButton menuReserve;
    JButton menuSearch;
    JButton menuSetting;
    JButton logout;


    JLabel label;


    public CardLayout cars;
    static final int NOT_NOTI = 0;
    static final int DATE_NOTI = 1;
    static final int TRAIN_NOTI = 2;


    Main() {

        setTitle("기차 예매 시스템");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);


        root = new JPanel();
        menu = new JPanel();
        content = new JPanel();
        ///////////////////////////


        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbcMenu = new GridBagConstraints();
        GridBagConstraints gbcContent = new GridBagConstraints();

        gbcMenu.fill = GridBagConstraints.BOTH;
        gbcContent.fill = GridBagConstraints.BOTH;
        gbcMenu.anchor = GridBagConstraints.NORTHWEST;
        gbcContent.anchor = GridBagConstraints.NORTHWEST;

        root.setLayout(gbl);

        gbcMenu.gridx = 0;
        gbcMenu.gridy = 0;
        gbcMenu.weightx = 1;
        gbcMenu.weighty = 0.;

        gbcContent.gridx = 0;
        gbcContent.gridy = 1;
        gbcContent.weightx = 1;
        gbcContent.weighty = 0.9;

////        gbcMenu.gridwidth = 800;
//        gbcMenu.gridheight = 1;
//
//        gbcContent.gridx = 0;
//        gbcContent.gridy = 1;
////        gbcContent.gridwidth = 800;
//        gbcContent.gridheight = 11;


        menuReserve = new JButton("예매하기"); // 버튼 초기화
        menuSearch = new JButton("예매조회"); // 버튼 초기화
        menuSetting = new JButton("관리"); // 버튼 초기화
        logout = new JButton("로그아웃");

        menuReserve.setSize(100, 100);
        menuSearch.setSize(160, 100);
        menuSetting.setSize(160, 100);

        menuReserve.addActionListener(this);
        menuSearch.addActionListener(this);
        menuSetting.addActionListener(this);
        logout.addActionListener(this);

        menu.add(menuReserve);
        menu.add(menuSearch);
        menu.add(menuSetting);
        menu.add(logout);


        ////////////////////////////

        content.setLayout(new CardLayout());

        jReserve = new ReservePanel();
        content.add(jReserve, "reserve");
        ViewSaver.setReservePanel(jReserve);

        jSearch = new SearchPanel();
        content.add(jSearch, "search");
        ViewSaver.setSearchPanel(jSearch);

        jSetting = new SettingPanel();
        content.add(jSetting, "setting");
        ViewSaver.setSettingPanel(jSetting);


        menu.setSize(800, 50);
        content.setSize(800, 550);


        gbl.setConstraints(menu, gbcMenu);
        gbl.setConstraints(content, gbcContent);

        root.add(menu);
        root.add(content);
        add(root);


        setVisible(true);


    }


    public void searchRefresh() {
        if (jSearch != null) {
            content.remove(jSearch);
        }
        jSearch = new SearchPanel();
        content.add(jSearch, "search");
        ViewSaver.setSearchPanel(jSearch);
        CardLayout cars = (CardLayout) content.getLayout();
        cars.show(content, "search");
        this.revalidate();
        this.repaint();

    }

    public void reserveRefresh() {
        if (jReserve != null) {
            content.remove(jReserve);
        }
        jReserve = new ReservePanel();
        content.add(jReserve, "reserve");
        ViewSaver.setReservePanel(jReserve);
        CardLayout cars = (CardLayout) content.getLayout();
        cars.show(content, "reserve");
        this.revalidate();
        this.repaint();

    }

    public void settingRefresh(String noti, int flag) {
        if (jSetting != null) {
            content.remove(jSetting);
        }
        jSetting = new SettingPanel();
        content.add(jSetting, "setting");
        ViewSaver.setSettingPanel(jSetting);
        CardLayout cars = (CardLayout) content.getLayout();
        cars.show(content, "setting");

        switch (flag) {
            case NOT_NOTI:
                break;
            case DATE_NOTI:
                jSetting.getDateNotiLabel().setText(noti);
                break;
            case TRAIN_NOTI:
                jSetting.getTrainNotiLabel().setText(noti);
                break;
        }
        this.revalidate();
        this.repaint();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == menuReserve) {
            reserveRefresh();
            cars = (CardLayout) content.getLayout();
            cars.show(content, "reserve");

        } else if (e.getSource() == menuSearch) {

            searchRefresh();
            cars = (CardLayout) content.getLayout();
            cars.show(content, "search");

        } else if (e.getSource() == menuSetting) {

            if (ViewSaver.accessOath) {
                settingRefresh("", NOT_NOTI);
                cars = (CardLayout) content.getLayout();
                cars.show(content, "setting");
            } else {
                AccessDialog cancleDialog = new AccessDialog();
            }


        } else if (e.getSource() == logout) {
            dispose();
            ViewSaver.accessOath = false;
            Login login = new Login();
        }

    }
}
