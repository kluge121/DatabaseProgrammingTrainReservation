package com.project.View;

import com.project.models.User;
import com.project.util.SessionHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterDialog extends JDialog implements ActionListener {


    private JButton confirm;
    private JButton cancle;

    private JTextField idField;
    private JPasswordField passField;
    private JTextField nameField;

    private JLabel idLabel;
    private JLabel passLabel;
    private JLabel nameLabel;

    private JLabel notiLable;


    RegisterDialog() {


        setTitle("회원가입");
        setLayout(null);
        setResizable(false);


        confirm = new JButton("확인");
        confirm.setBounds(150, 200, 70, 25);
        confirm.addActionListener(this);
        this.add(confirm);

        cancle = new JButton("취소");
        cancle.setBounds(230, 200, 70, 25);
        cancle.addActionListener(this);
        this.add(cancle);

        idField = new JTextField(15);
        idField.setBounds(160, 60, 140, 30);
        this.add(idField);

        passField = new JPasswordField();
        passField.setBounds(160, 95, 140, 30);
        this.add(passField);

        nameField = new JTextField();
        nameField.setBounds(160, 130, 140, 30);
        this.add(nameField);

        idLabel = new JLabel("ID");
        idLabel.setBounds(135, 65, 20, 20);
        this.add(idLabel);

        passLabel = new JLabel("PASSWORD");
        passLabel.setBounds(80, 100, 90, 20);
        this.add(passLabel);

        nameLabel = new JLabel("NAME");
        nameLabel.setBounds(110, 135, 40, 20);
        this.add(nameLabel);

        notiLable = new JLabel();
        notiLable.setBounds(130, 170, 150, 20);
        this.add(notiLable);

//        nameLabel = new JLabel("NAME");
//        nameLabel.setBounds();


        this.setSize(400, 280);
        this.setModal(true);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == confirm) {
            String id = idField.getText();
            String pass = new String(passField.getPassword());
            String name = nameField.getText();


            if(id.length()<1 || pass.length()<1 || name.length()<1){
                notiLable.setText("빈칸을 모두 입력해주세요");
                return;

            }



            System.out.println((id + " " + pass + " " + name));

            try {
                Session session = SessionHelper.getCurrentSession();
                Transaction tx = session.beginTransaction();
                User user = new User(id, pass, name);
                session.save(user);
                tx.commit();
                dispose();
            } catch (Exception e1) {
                notiLable.setText("중복된 아이디입니다");
            }



        } else if (e.getSource() == cancle) {
            dispose();
        }

    }
}
