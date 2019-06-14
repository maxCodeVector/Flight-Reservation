package plane;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//pgCenter(个人中心)类，创建了乘客查看个人信息和查看订单的窗口
public class pgCennter {

	public static void myInfo(Passenger pg) {
		JFrame my = new JFrame("个人信息");
		Container con = my.getContentPane();
		my.setLayout(new GridBagLayout());
		JPanel p2 = new JPanel(new GridLayout(6, 2, 10, 40));
		JPanel p3 = new JPanel(new GridLayout(5, 2, 10, 55));
		JPanel p4 = new JPanel(new GridLayout(1, 3, 50, 5));
		JLabel j1 = new JLabel("passengerID", SwingConstants.CENTER);
		JLabel j2 = new JLabel("身份证号码", SwingConstants.CENTER);
		JLabel j3 = new JLabel("姓名", SwingConstants.CENTER);
		JLabel j4 = new JLabel("性别", SwingConstants.CENTER);
		JLabel j5 = new JLabel("密码", SwingConstants.CENTER);
		JLabel j6 = new JLabel("用户名", SwingConstants.CENTER);
		JLabel j7 = new JLabel("手机号", SwingConstants.CENTER);
		JLabel j8 = new JLabel("验证密码", SwingConstants.CENTER);
		JLabel j9 = new JLabel("输入新密码", SwingConstants.CENTER);
		JLabel j10 = new JLabel("确认密码", SwingConstants.CENTER);
		JButton b1 = new JButton("修改密码");
		JButton b2 = new JButton("取消修改");
		JButton b3 = new JButton("确认修改");
		JButton b4 = new JButton("修改资料");
		JLabel t1 = new JLabel(pg.getPassengerId(), SwingConstants.CENTER);
		JLabel t2 = new JLabel(pg.getIdentityId(), SwingConstants.CENTER);
		JLabel t3 = new JLabel(pg.getRealName(), SwingConstants.CENTER);
		JLabel t4 = new JLabel(pg.getSex(), SwingConstants.CENTER);
		JLabel t5 = new JLabel("******", SwingConstants.CENTER);
		JTextField t6 = new JTextField(pg.getUserName());
		JTextField t7 = new JTextField(pg.getTeleNum());
		JPasswordField t8 = new JPasswordField("");
		JPasswordField t9 = new JPasswordField("");
		JPasswordField t10 = new JPasswordField("");
		j1.setFont(new Font("宋体", Font.BOLD, 30));
		j2.setFont(new Font("宋体", Font.BOLD, 30));
		j2.setFont(new Font("宋体", Font.BOLD, 30));
		j3.setFont(new Font("宋体", Font.BOLD, 30));
		j4.setFont(new Font("宋体", Font.BOLD, 30));
		j5.setFont(new Font("宋体", Font.BOLD, 30));
		j6.setFont(new Font("宋体", Font.BOLD, 30));
		j7.setFont(new Font("宋体", Font.BOLD, 30));
		j8.setFont(new Font("宋体", Font.BOLD, 30));
		j9.setFont(new Font("宋体", Font.BOLD, 30));
		j10.setFont(new Font("宋体", Font.BOLD, 30));
		t1.setFont(new Font("宋体", Font.BOLD, 30));
		t2.setFont(new Font("宋体", Font.BOLD, 25));
		t3.setFont(new Font("宋体", Font.BOLD, 30));
		t4.setFont(new Font("宋体", Font.BOLD, 30));
		t5.setFont(new Font("宋体", Font.BOLD, 30));
		t6.setFont(new Font("宋体", Font.BOLD, 30));
		t7.setFont(new Font("宋体", Font.BOLD, 30));
		t8.setFont(new Font("宋体", Font.BOLD, 30));
		t9.setFont(new Font("宋体", Font.BOLD, 30));
		t10.setFont(new Font("宋体", Font.BOLD, 30));
		b1.setFont(new Font("宋体", Font.BOLD, 30));
		b2.setFont(new Font("宋体", Font.BOLD, 30));
		b3.setFont(new Font("宋体", Font.BOLD, 30));
		b4.setFont(new Font("宋体", Font.BOLD, 30));
		b2.setEnabled(false);
		b3.setEnabled(false);
		t8.setEditable(false);
		t9.setEditable(false);
		t10.setEditable(false);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b1.setEnabled(false);
				b2.setEnabled(true);
				b3.setEnabled(true);
				b4.setEnabled(false);
				t6.setEditable(false);
				t7.setEditable(false);
				t8.setEditable(true);
				t9.setEditable(true);
				t10.setEditable(true);
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b1.setEnabled(true);
				b2.setEnabled(false);
				b3.setEnabled(false);
				b4.setEnabled(true);
				t6.setEditable(true);
				t7.setEditable(true);
				t8.setEditable(false);
				t9.setEditable(false);
				t10.setEditable(false);
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(t8.getPassword()).equals(pg.getPassword())) {
					if (!new String(t9.getPassword()).trim().equals("") && new String(t9.getPassword()).equals(new String(t10.getPassword()))) {
						pg.setPassword(new String(t9.getPassword()));
						managePassenger.savePassenger();
						JOptionPane.showMessageDialog(null, "密码修改成功");
						t8.setText("");
						t9.setText("");
						t10.setText("");
						t6.setEditable(true);
						t7.setEditable(true);
						t8.setEditable(false);
						t9.setEditable(false);
						t10.setEditable(false);
						b1.setEnabled(true);
						b2.setEnabled(false);
						b3.setEnabled(false);
						b4.setEnabled(true);
					} else
						JOptionPane.showMessageDialog(null, "密码不一致或密码为空");
				} else
					JOptionPane.showMessageDialog(null, "密码错误");
			}

		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean right = managePassenger.checkPassenger(t6.getText(), pg.getRealName(), t7.getText(),
						pg.getIdentityId(), pg.getPassword());
				if (right) {
					pg.setUserName(t6.getText());
					pg.setTeleNum(t7.getText());
					managePassenger.savePassenger();
					String message = String.format("修改资料成功\n用户名：%s\n手机号码：%s", pg.getUserName(), pg.getTeleNum());
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		GridBagConstraints gb2 = new GridBagConstraints(0, 0, 3, 5, 10, 10, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0);
		GridBagConstraints gb3 = new GridBagConstraints(3, 0, 2, 5, 10, 10, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 20), 0, 0);
		GridBagConstraints gb4 = new GridBagConstraints(3, 5, 2, 2, 10, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0);
		p2.add(j1);
		p2.add(t1);
		p2.add(j2);
		p2.add(t2);
		p2.add(j3);
		p2.add(t3);
		p2.add(j4);
		p2.add(t4);
		p2.add(j5);
		p2.add(t5);
		p2.add(b1);
		p3.add(j6);
		p3.add(t6);
		p3.add(j7);
		p3.add(t7);
		p3.add(j8);
		p3.add(t8);
		p3.add(j9);
		p3.add(t9);
		p3.add(j10);
		p3.add(t10);

		p4.add(b2);
		p4.add(b3);
		p4.add(b4);
		con.add(p2, gb2);
		con.add(p3, gb3);
		con.add(p4, gb4);
		my.setBounds(300, 200, 1200, 600);
		my.setResizable(false);
		my.setVisible(true);
	}

	public static void myOr(Passenger pg) {
		JFrame my = new JFrame("我的订单");
		Container con = my.getContentPane();
		my.setLayout(new GridBagLayout());
		JTextArea j = new JTextArea(manageOrder.lookOrder(pg));
		JTextArea t = new JTextArea("查询结果");
		JScrollPane sp1 = new JScrollPane(j);
		JScrollPane sp2 = new JScrollPane(t);
		JPanel p1 = new JPanel(new GridLayout(3, 4, 30, 60));
		JButton b1 = new JButton("查询航班");
		JButton b2 = new JButton("预订");
		JButton b3 = new JButton("查询订单");
		JButton b4 = new JButton("支付");
		JButton b5 = new JButton("退订");
		JLabel j1 = new JLabel("航班号", SwingConstants.CENTER);
		JLabel j2 = new JLabel("日期", SwingConstants.CENTER);
		JLabel j3 = new JLabel("订单号", SwingConstants.CENTER);
		JTextField t1 = new JTextField("");
		JTextField t2 = new JTextField("");
		JTextField t3 = new JTextField("");
		j.setFont(new Font("宋体", Font.BOLD, 20));
		t.setFont(new Font("宋体", Font.BOLD, 20));
		t1.setFont(new Font("宋体", Font.BOLD, 25));
		t2.setFont(new Font("宋体", Font.BOLD, 25));
		t3.setFont(new Font("宋体", Font.BOLD, 25));
		j1.setFont(new Font("宋体", Font.BOLD, 25));
		j2.setFont(new Font("宋体", Font.BOLD, 25));
		j3.setFont(new Font("宋体", Font.BOLD, 25));
		b1.setFont(new Font("宋体", Font.BOLD, 25));
		b2.setFont(new Font("宋体", Font.BOLD, 25));
		b3.setFont(new Font("宋体", Font.BOLD, 25));
		b4.setFont(new Font("宋体", Font.BOLD, 25));
		b5.setFont(new Font("宋体", Font.BOLD, 25));
		b2.setEnabled(false);
		b4.setEnabled(false);
		b5.setEnabled(false);
		p1.add(j1);
		p1.add(t1);
		p1.add(j3);
		p1.add(t3);
		p1.add(j2);
		p1.add(t2);
		p1.add(new JLabel());
		p1.add(b3);
		p1.add(b1);
		p1.add(b2);
		p1.add(b4);
		p1.add(b5);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ini = "查询结果";
				String a = "";
				for (Flight f : manageFlight.flights) {
					if (f.FlightID.equals(t1.getText()) && -1 != f.departureDate.indexOf(t2.getText())) {
						a = a + tools.showFlight(null, f);
						b2.setEnabled(true);
						t.setText(ini + "\n" + a);
						break;
					}
				}
				if (a.equals("")) {
					t.setText(ini + "\n航班未发布或不存在");
					b2.setEnabled(false);
				}
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageOrder.bookOrder(t1.getText(), t2.getText(), pg);
				j.setText(manageOrder.lookOrder(pg));
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderInfo = manageOrder.lookOrder(t3.getText(), pg);
				if (orderInfo == null) {
					b4.setEnabled(false);
					b5.setEnabled(false);
					JOptionPane.showMessageDialog(null, "订单号不存在");
				} else {
					b4.setEnabled(true);
					b5.setEnabled(true);
					t.setText(orderInfo);
				}
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tools.paidDialog(t3.getText());
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tools.unsubscribeDialog(t3.getText());
			}
		});
		GridBagConstraints gb1 = new GridBagConstraints(0, 0, 1, 5, 100, 100, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(10, 0, 5, 20), 0, 0);
		GridBagConstraints gb2 = new GridBagConstraints(0, 5, 1, 4, 100, 100, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 0, 0, 20), 0, 0);
		GridBagConstraints gb3 = new GridBagConstraints(0, 9, 1, 3, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(0, 20, 20, 20), 0, 0);
		con.add(sp1, gb1);
		con.add(sp2, gb2);
		con.add(p1, gb3);
		my.setBounds(900, 50, 900, 800);
		my.setResizable(false);
		my.setVisible(true);
	}

}
