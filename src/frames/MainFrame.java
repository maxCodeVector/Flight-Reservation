package frames;

import bean.Admin;
import bean.Passenger;
import controller.FlightController;
import util.Tool;
import util.tools;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 设置了管理员和乘客的主界面
 */

public class MainFrame {

	// 管理员主界面
	public static void adminF(Admin ad) {
		JFrame my = new JFrame();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(2, 1, 5, 1));
		JPanel p1 = new JPanel(new GridLayout());
		JPanel p2 = new JPanel(new FlowLayout(0, 100, 50));
		String pass = "尊敬的管理员" + ad.getRealName() + ",Welcome!欢迎进入机票预订系统!";
		JLabel j1 = new JLabel(pass, SwingConstants.CENTER);
		JButton b1 = new JButton("添加航班");
		JButton b2 = new JButton("查询航班");
		JButton b3 = new JButton("我的信息");
		JButton b4 = new JButton("添加成员");
		JButton b5 = new JButton("高级查询");
		JButton b6 = new JButton("退出登录");
		j1.setFont(new Font("宋体", Font.BOLD, 30));
		b1.setFont(new Font("宋体", Font.BOLD, 20));
		b2.setFont(new Font("宋体", Font.BOLD, 20));
		b3.setFont(new Font("宋体", Font.BOLD, 20));
		b4.setFont(new Font("宋体", Font.BOLD, 20));
		b5.setFont(new Font("宋体", Font.BOLD, 20));
		b6.setFont(new Font("宋体", Font.BOLD, 20));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlightFrameFactory.creatBaseFlightFrame();
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlightController.flush();
				FlightFrameFactory.queryFlightsFrame(ad, FlightController.flights);
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					AdminFrameFactory.ManageAdminFrame(ad);
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFrameFactory.creatAdminFrame();
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlightController.flush();
				new superQueryFrame();
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my.setVisible(false);
			}
		});
		p2.add(j1);
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		p2.add(b5);
		p2.add(b6);
		con.add(tools.Samp(p1, Tool.getDataAdpater().getPlaneImgPath()));
		con.add(p2);
		my.setBounds(400, 50, 900, 800);
		my.setResizable(false);
		my.setVisible(true);
		my.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	// 乘客主界面
	public static void passF(Passenger pg) {
		JFrame my = new JFrame("尊敬的乘客,欢迎进入机票预订系统!");
		Container con = my.getContentPane();
		my.setLayout(new GridLayout(2, 1, 0, 0));
		JPanel p1 = new JPanel(new GridLayout());
		JPanel p2 = new JPanel(new FlowLayout(5, 50, 50));
		String pass;
		if (pg != null) {
			pass = "尊敬的乘客" + pg.getRealName() + ",Welcome!欢迎进入机票预订系统!";
		} else {
			pass = "尊敬的游客先生/小姐,Welcome!欢迎进入机票预订系统!";
		}
		JLabel j1 = new JLabel(pass, SwingConstants.CENTER);
		JButton b1 = new JButton("我的资料");
		JButton b2 = new JButton("查询航班");
		JButton b3 = new JButton("我的订单");
		JButton b4 = new JButton("退出");
		j1.setFont(new Font("宋体", Font.BOLD, 30));
		b1.setFont(new Font("宋体", Font.BOLD, 20));
		b2.setFont(new Font("宋体", Font.BOLD, 20));
		b3.setFont(new Font("宋体", Font.BOLD, 20));
		b4.setFont(new Font("宋体", Font.BOLD, 20));
		b1.setEnabled(false);
		b3.setEnabled(false);
		if (pg != null) {
			b1.setEnabled(true);
			b3.setEnabled(true);
		}
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pgCennterFrame.myInfo(pg);
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlightController.flush();
				FlightFrameFactory.queryFlightsFrame(null, FlightController.flights);
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tools.queryOrderDialog(pg);
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my.setVisible(false);
			}
		});
		p2.add(j1);
		p2.add(b1);
		p2.add(new JLabel());
		p2.add(b2);
		p2.add(new JLabel());
		p2.add(b3);
		p2.add(new JLabel());
		p2.add(b4);
		p2.add(new JLabel());
		con.add(tools.Samp(p1, Tool.getDataAdpater().getSkyImgPath()));
		con.add(p2);
		my.setBounds(400, 200, 900, 700);
		my.setResizable(false);
		my.setVisible(true);
		my.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
