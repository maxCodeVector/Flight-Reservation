package plane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class updeleFlight {// 设置修改航班的窗口

	// 设置修改删除航班窗体的构造器
	updeleFlight() {
		JFrame my = new JFrame("管理员修改航班");
		Container con = my.getContentPane();
		my.setLayout(new GridBagLayout());
		JPanel p3 = new JPanel(new GridLayout(8, 2, 40, 40));
		JPanel p4 = new JPanel(new GridLayout(5, 2, 100, 50));
		JLabel j1 = new JLabel("请输入需要的航班号", SwingConstants.CENTER);
		JLabel j2 = new JLabel("起飞日期", SwingConstants.CENTER);
		JLabel j3 = new JLabel("startCity", SwingConstants.CENTER);
		JLabel j4 = new JLabel("arrivalCity", SwingConstants.CENTER);
		JLabel j5 = new JLabel("startTime", SwingConstants.CENTER);
		JLabel j6 = new JLabel("arrivalTime", SwingConstants.CENTER);
		JLabel j7 = new JLabel("price", SwingConstants.CENTER);
		JLabel j8 = new JLabel("seatCapacity", SwingConstants.CENTER);
		JLabel j9 = new JLabel("flightStatus", SwingConstants.CENTER);
		JButton b1 = new JButton("确定");
		JButton b2 = new JButton("放弃操作");
		JButton b3 = new JButton("确定修改");
		JButton b4 = new JButton("确定删除");
		JButton b5 = new JButton("发布航班");
		JButton b6 = new JButton("取消发布");
		JTextField t1 = new JTextField("");
		JTextField t2 = new JTextField("");
		JTextField t3 = new JTextField("");
		JTextField t4 = new JTextField("");
		JTextField t5 = new JTextField("");
		JTextField t6 = new JTextField("");
		JTextField t7 = new JTextField("");
		JTextField t8 = new JTextField("");
		JTextField t9 = new JTextField("");
		j1.setFont(new Font("宋体", Font.BOLD, 25));
		j2.setFont(new Font("宋体", Font.BOLD, 25));
		j2.setFont(new Font("宋体", Font.BOLD, 25));
		j3.setFont(new Font("宋体", Font.BOLD, 25));
		j4.setFont(new Font("宋体", Font.BOLD, 25));
		j5.setFont(new Font("宋体", Font.BOLD, 25));
		j6.setFont(new Font("宋体", Font.BOLD, 25));
		j7.setFont(new Font("宋体", Font.BOLD, 25));
		j8.setFont(new Font("宋体", Font.BOLD, 25));
		j9.setFont(new Font("宋体", Font.BOLD, 25));
		t1.setFont(new Font("宋体", Font.BOLD, 30));
		t2.setFont(new Font("宋体", Font.BOLD, 30));
		t3.setFont(new Font("宋体", Font.BOLD, 30));
		t4.setFont(new Font("宋体", Font.BOLD, 30));
		t5.setFont(new Font("宋体", Font.BOLD, 30));
		t6.setFont(new Font("宋体", Font.BOLD, 30));
		t7.setFont(new Font("宋体", Font.BOLD, 30));
		t8.setFont(new Font("宋体", Font.BOLD, 30));
		t9.setFont(new Font("宋体", Font.BOLD, 25));
		b1.setFont(new Font("宋体", Font.BOLD, 30));
		b2.setFont(new Font("宋体", Font.BOLD, 30));
		b3.setFont(new Font("宋体", Font.BOLD, 30));
		b4.setFont(new Font("宋体", Font.BOLD, 30));
		b5.setFont(new Font("宋体", Font.BOLD, 30));
		b6.setFont(new Font("宋体", Font.BOLD, 30));
		b3.setEnabled(false);
		b4.setEnabled(false);
		b5.setEnabled(false);
		b6.setEnabled(false);
		t9.setEditable(false);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean a = true;
				for (Flight f : manageFlight.flights) {
					if (f.FlightID.equals(t1.getText()) && -1 != f.departureDate.indexOf(t2.getText())) {
						t2.setText(f.departureDate);
						t3.setText(f.startCity);
						t4.setText(f.arrivalCity);
						t5.setText(f.startTime);
						t6.setText(f.arrivalTime);
						t7.setText(f.price);
						t8.setText(f.seatCapacity);
						t9.setText(f.flightStatus);
						t1.setEditable(false);
						t2.setEditable(false);
						a = false;
						switch (f.flightStatus) {
						case "UNPUBLISHED":
							t3.setEditable(true);
							t4.setEditable(true);
							t5.setEditable(true);
							t6.setEditable(true);
							b3.setEnabled(true);
							b4.setEnabled(true);
							b5.setEnabled(true);
							b6.setEnabled(false);
							break;
						case "TERMINATE":
							JOptionPane.showMessageDialog(null, "两小时之内将起飞的航班不可操作");
							b3.setEnabled(false);
							b4.setEnabled(true);
							b5.setEnabled(false);
							b6.setEnabled(false);
							break;
						default:
							t3.setEditable(false);
							t4.setEditable(false);
							t5.setEditable(false);
							t6.setEditable(false);
							b3.setEnabled(true);
							b4.setEnabled(false);
							b5.setEnabled(false);
							b6.setEnabled(true);
						}
						break;// 提前终止循环
					}
				}
				// 如果a 为true，表明航班不存在
				if (a) {
					JOptionPane.showMessageDialog(null, "该航班不存在");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					t6.setText("");
					t7.setText("");
					t8.setText("");
					b3.setEnabled(false);
					b4.setEnabled(false);
					b5.setEnabled(false);
					b6.setEnabled(false);
				}
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setEditable(true);
				t2.setEditable(true);
				t3.setEditable(true);
				t4.setEditable(true);
				t5.setEditable(true);
				t6.setEditable(true);
				t7.setEditable(true);
				b3.setEnabled(false);
				b4.setEnabled(false);
				b5.setEnabled(false);
				b6.setEnabled(false);
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t6.setText("");
				t7.setText("");
				t8.setText("");
				t9.setText("");
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean right = manageFlight.checkFlight(t1.getText(), t3.getText(), t4.getText(), t5.getText(),
						t6.getText(), t2.getText(), t7.getText(), t8.getText());

				if (right) {
					for (Flight fl : manageFlight.flights) {
						if (fl.FlightID.equals(t1.getText()) && -1 != fl.departureDate.indexOf(t2.getText())) {
							manageFlight.flights.remove(fl);
							Flight f = new Flight(t1.getText(), t3.getText(), t4.getText(), t5.getText(), t6.getText(),
									t2.getText(), t7.getText(), t8.getText(), fl.flightStatus, fl.currentPassengers);
							manageFlight.flights.add(f);
							manageFlight.saveFlight();
							String a = String.format("修改成功\n%-7s%-4S%-4S%-6s%-6s%-10s容量%-3s价格%-4s\n", f.FlightID,
									f.startCity, f.arrivalCity, f.startTime, f.arrivalTime, f.departureDate,
									f.seatCapacity, f.price);
							JOptionPane.showMessageDialog(null, a);
							break;
						}
					}
					t1.setEditable(true);
					t2.setEditable(true);
					t3.setText("");
					t4.setText("");
					t5.setText("");
					t6.setText("");
					t7.setText("");
					t8.setText("");
				}
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Flight fl : manageFlight.flights) {
					if (fl.FlightID.equals(t1.getText()) && -1 != fl.departureDate.indexOf(t2.getText())) {
						manageFlight.flights.remove(fl);
						break;
					}
				}
				manageFlight.saveFlight();
				JOptionPane.showMessageDialog(null, "删除成功");
				t1.setEditable(true);
				t2.setEditable(true);
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t6.setText("");
				t7.setText("");
				t8.setText("");
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Flight fl : manageFlight.flights) {
					if (fl.FlightID.equals(t1.getText()) && -1 != fl.departureDate.indexOf(t2.getText())) {
						fl.setFlightStatus(State.AVAILABLE);
						break;
					}
				}
				manageFlight.saveFlight();
				t3.setEditable(false);
				t4.setEditable(false);
				t5.setEditable(false);
				t6.setEditable(false);
				b3.setEnabled(true);
				b4.setEnabled(false);
				b5.setEnabled(false);
				b6.setEnabled(true);
				JOptionPane.showMessageDialog(null, "发布成功");
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Flight fl : manageFlight.flights) {
					if (fl.FlightID.equals(t1.getText()) && -1 != fl.departureDate.indexOf(t2.getText())) {
						fl.setFlightStatus(State.UNPUBLISHED);
						break;
					}
				}
				manageFlight.saveFlight();
				JOptionPane.showMessageDialog(null, "已取消发布");
			}
		});
		GridBagConstraints gb3 = new GridBagConstraints(1, 0, 2, 6, 10, 10, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(10, 0, 0, 20), 0, 0);
		GridBagConstraints gb4 = new GridBagConstraints(3, 0, 3, 4, 10, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0);
		p3.add(j1);
		p3.add(t1);
		p3.add(j2);
		p3.add(t2);
		p3.add(b1);
		p3.add(b2);
		p3.add(j3);
		p3.add(t3);
		p3.add(j4);
		p3.add(t4);
		p3.add(j5);
		p3.add(t5);
		p3.add(j6);
		p3.add(t6);
		p4.add(j7);
		p4.add(t7);
		p4.add(j8);
		p4.add(t8);
		p4.add(j9);
		p4.add(t9);
		p4.add(b3);
		p4.add(b4);
		p4.add(b5);
		p4.add(b6);
		con.add(p3, gb3);
		con.add(p4, gb4);
		my.setBounds(300, 200, 1100, 700);
		my.setResizable(false);
		my.setVisible(true);
	}

}
