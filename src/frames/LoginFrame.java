package frames;

import controller.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginFrame {

	private static void logon() {
		JFrame my = new JFrame();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(3, 2, 10, 20));
		JPanel p1 = new JPanel(new GridLayout(1, 2, 30, 20));
		JPanel p2 = new JPanel(new GridLayout(1, 2, 30, 20));
		JPanel p3 = new JPanel(new GridLayout(1, 4, 20, 20));
		p1.setBorder(new EmptyBorder(30, 40, 10, 40));
		p2.setBorder(new EmptyBorder(10, 40, 20, 40));
		p3.setBorder(new EmptyBorder(20, 40, 30, 40));

		JLabel j1 = new JLabel("name", SwingConstants.CENTER);
		JTextField t1 = new JTextField("bohao");
		j1.setFont(new Font("����", Font.BOLD, 30));
		t1.setFont(new Font("����", Font.BOLD, 30));
		p1.add(j1);
		p1.add(t1);
		con.add(p1);

		JLabel j2 = new JLabel("password", SwingConstants.CENTER);
		JPasswordField t2 = new JPasswordField("2333");
		j2.setFont(new Font("����", Font.BOLD, 30));
		t2.setFont(new Font("����", Font.BOLD, 30));
		p2.add(j2);
		p2.add(t2);
		con.add(p2);

		JButton b1 = new JButton("admin");
		JButton b2 = new JButton("passenger");
		JButton b3 = new JButton("traverl");
		JButton b4 = new JButton("register");
		b1.setFont(new Font("����", Font.BOLD, 20));
		b2.setFont(new Font("����", Font.BOLD, 20));
		b3.setFont(new Font("����", Font.BOLD, 20));
		b4.setFont(new Font("����", Font.BOLD, 20));
		p3.add(b1);
		p3.add(b2);
		p3.add(b3);
		p3.add(b4);
		con.add(p3);
		b1.addActionListener(e -> manageAdmin.logon(t1.getText(), new String(t2.getPassword())));
		b2.addActionListener(e -> managePassenger.logon(t1.getText(), new String(t2.getPassword())));
		b3.addActionListener(e -> MainFrame.passF(null));
		b4.addActionListener(e -> registerFrame.register());
		my.setResizable(false);
		my.setTitle("logon...");
		my.setVisible(true);
		my.setBounds(600, 200, 700, 350);
		my.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void Start() {
		managePassenger.initial();
		manageFlight.initial();
		manageOrder.initial();
		FlightController.flush();
		UIManager.put("OptionPane.messageFont", new Font("����", Font.BOLD, 30));
		UIManager.put("OptionPane.buttonFont", new Font("����", Font.BOLD, 30));
		LoginFrame.logon();
	}

}
