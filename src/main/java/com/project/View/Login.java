package com.project.View;

import com.project.util.UserInfo;
import com.project.util.ViewSaver;
import com.project.models.User;
import com.project.util.SessionHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Login extends JFrame implements ActionListener {


    private JButton loginBtn;
    private JButton registerBtn;
    private JTextField idField;
    private JPasswordField passField;
    private JLabel idLabel;
    private JLabel passLabel;

    private JLabel titleLabel;
    private JLabel titleLabel2;
    private JLabel titleLabel3;
    private JLabel notiLabel;


    private Image img1;
    private Image resizeImage1;

    private Image img2;
    private Image resizeImage2;


    private Session session;

    Login() {

        session = SessionHelper.getCurrentSession();
        Transaction tx = session.beginTransaction();

        tx.commit();


        setTitle("기차예매시스템");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLayout(null);


        getContentPane().setBackground(Color.WHITE);
        try {
            img1 = ImageIO.read(new File("img/logo.png"));
            resizeImage1 = img1.getScaledInstance(300, 150, Image.SCALE_SMOOTH);

            img2 = ImageIO.read(new File("img/ktx1.png"));
            resizeImage2 = img2.getScaledInstance(480, 70, Image.SCALE_SMOOTH);


        } catch (IOException e) {
            System.out.println("이미지 불러오기 실패");
            System.exit(0);
        }

        MyPanel panel = new MyPanel();
        panel.setBounds(0, 0, 240, 150);


        KTX1 ktx1 = new KTX1();
        ktx1.setBounds(0, 500, 600, 70);

        this.add(panel);
        this.add(ktx1);

        loginBtn = new JButton("로그인");
        loginBtn.setBounds(320, 400, 80, 50);
        loginBtn.addActionListener(this);
        this.add(loginBtn);


        registerBtn = new JButton("회원가입");
        registerBtn.setBounds(420, 400, 80, 50);
        registerBtn.addActionListener(this);
        this.add(registerBtn);

        idField = new JTextField(15);
        idField.setBounds(350, 280, 140, 30);
        this.add(idField);

        passField = new JPasswordField();
        passField.setBounds(350, 320, 140, 30);
        this.add(passField);

        idLabel = new JLabel("ID");
        idLabel.setBounds(320, 285, 20, 20);
        this.add(idLabel);

        passLabel = new JLabel("PASSWORD");
        passLabel.setBounds(270, 325, 90, 20);
        this.add(passLabel);

        notiLabel = new JLabel();
        notiLabel.setForeground(Color.RED);
        notiLabel.setBounds(290, 370, 180, 20);
        this.add(notiLabel);


        Font font = new Font("Courier", Font.BOLD, 30);
        titleLabel = new JLabel("예매 관리 시스템");
        titleLabel.setBounds(250, 30, 400, 100);
        titleLabel.setFont(font);
        this.add(titleLabel);

        Font font1 = new Font("Courier", Font.BOLD, 20);
        titleLabel2 = new JLabel("DB Project");
        titleLabel2.setBounds(570,500,200,40);
        titleLabel2.setFont(font1);
        this.add(titleLabel2);

        titleLabel3 = new JLabel("배민수 최승규");
        titleLabel3.setBounds(570,530,200,40);
        titleLabel3.setFont(font1);
        this.add(titleLabel3);



        setVisible(true);

//        dispose();
//        Main main = new Main();


    }


    public static void main(String args[]) {


        Login login = new Login();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(registerBtn)) {
            RegisterDialog registerDialog = new RegisterDialog();

        } else if (e.getSource().equals(loginBtn)) {

            session = SessionHelper.getCurrentSession();
            Transaction tx = session.beginTransaction();


            Query query = session.createQuery("from User as user " +
                    "where user.id = :id and user.password = :pass");


            query.setParameter("id", idField.getText())
                    .setParameter("pass", new String(passField.getPassword()));

            List<User> list = query.list();

            if (query.list().size() == 1) {

                UserInfo.setUserId(idField.getText());
                UserInfo.setUser(list.get(0));
                Main main = new Main();
                ViewSaver.setMain(main);
                dispose();


            } else {
                //로그인 실패
                notiLabel.setText("아이디와 비밀번호를 확인해주세요");
            }

            tx.commit();
        }

    }

    class MyPanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(resizeImage1, 0, 0, null);
        }
    }

    class KTX1 extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(resizeImage2, 0, 0, null);
        }

    }


}
