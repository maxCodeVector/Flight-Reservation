package frames;

import controller.managePassenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerFrame {

    public static void register() {
        JFrame my = new JFrame("�˿�ע��");
        Container con = my.getContentPane();
        con.setLayout(new GridLayout(7, 2, 0, 50));
        JPanel p1 = new JPanel(new GridLayout(1, 2, 100, 50));
        JLabel j1 = new JLabel("�û���", SwingConstants.CENTER);
        JLabel j2 = new JLabel("��ʵ����", SwingConstants.CENTER);
        JLabel j3 = new JLabel("�ֻ�����", SwingConstants.CENTER);
        JLabel j4 = new JLabel("���֤����", SwingConstants.CENTER);
        JLabel j5 = new JLabel("����", SwingConstants.CENTER);
        JLabel j6 = new JLabel("ȷ������", SwingConstants.CENTER);
        JButton b1 = new JButton("�ύ");
        JButton b2 = new JButton("����");
        JTextField t1 = new JTextField("");
        JTextField t2 = new JTextField("");
        JTextField t3 = new JTextField("");
        JTextField t4 = new JTextField("");
        JPasswordField t5 = new JPasswordField("");
        JPasswordField t6 = new JPasswordField("");
        j1.setFont(new Font("����", Font.BOLD, 30));
        j2.setFont(new Font("����", Font.BOLD, 30));
        j3.setFont(new Font("����", Font.BOLD, 30));
        j4.setFont(new Font("����", Font.BOLD, 30));
        j5.setFont(new Font("����", Font.BOLD, 30));
        j6.setFont(new Font("����", Font.BOLD, 30));
        b1.setFont(new Font("����", Font.BOLD, 30));
        b2.setFont(new Font("����", Font.BOLD, 30));
        t1.setFont(new Font("����", Font.BOLD, 30));
        t2.setFont(new Font("����", Font.BOLD, 30));
        t3.setFont(new Font("����", Font.BOLD, 30));
        t4.setFont(new Font("����", Font.BOLD, 30));
        t5.setFont(new Font("����", Font.BOLD, 30));
        t6.setFont(new Font("����", Font.BOLD, 30));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (new String(t5.getPassword()).equals(new String(t6.getPassword()))) {
                    if (managePassenger.checkPassenger(t1.getText(), t2.getText(), t3.getText(), t4.getText(), new String(t5.getPassword()))) {
                        managePassenger.register(t1.getText(), t2.getText(), t3.getText(), t4.getText(), new String(t5.getPassword()));
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        t5.setText("");
                        t6.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "���벻һ��");
                    t5.setText("");
                    t6.setText("");
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                my.setVisible(false);
            }
        });
        p1.add(b1);
        p1.add(b2);
        con.add(j1);
        con.add(t1);
        con.add(j2);
        con.add(t2);
        con.add(j3);
        con.add(t3);
        con.add(j4);
        con.add(t4);
        con.add(j5);
        con.add(t5);
        con.add(j6);
        con.add(t6);
        con.add(p1);
        my.setResizable(false);
        my.setTitle("��ӭ�����ƱԤ��ϵͳ");
        my.setVisible(true);
        my.setBounds(400, 100, 650, 700);
    }
}