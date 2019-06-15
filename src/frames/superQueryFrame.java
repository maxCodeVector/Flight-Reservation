package frames;

import bean.FlightInfo;
import bean.Passenger;
import controller.FlightController;
import controller.manageOrder;
import controller.managePassenger;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//管理员超级查询的窗口
public class superQueryFrame {

	public superQueryFrame() {
		JFrame my = new JFrame("超级查询");
		Container con = my.getContentPane();
		my.setLayout(new GridBagLayout());
		JTextArea j = new JTextArea();
		JScrollPane sp1 = new JScrollPane(j);
		JPanel p1 = new JPanel(new GridLayout(2, 4, 30, 60));
		JPanel p2 = new JPanel(new GridLayout(1, 3, 30, 60));
		JButton b1 = new JButton("查询航班预订信息");
		JButton b2 = new JButton("查询乘客订单");
		JLabel j1 = new JLabel("航班号", SwingConstants.CENTER);
		JLabel j2 = new JLabel("日期", SwingConstants.CENTER);
		JLabel j3 = new JLabel("<html>输入乘客信息<br>如手机号码</html>");
		JTextField t1 = new JTextField("");
		JTextField t2 = new JTextField("");
		JTextField t3 = new JTextField("");
		j.setFont(new Font("宋体", Font.BOLD, 20));
		t1.setFont(new Font("宋体", Font.BOLD, 25));
		t2.setFont(new Font("宋体", Font.BOLD, 25));
		t3.setFont(new Font("宋体", Font.BOLD, 25));
		j1.setFont(new Font("宋体", Font.BOLD, 25));
		j2.setFont(new Font("宋体", Font.BOLD, 25));
		j3.setFont(new Font("宋体", Font.BOLD, 22));
		b1.setFont(new Font("宋体", Font.BOLD, 25));
		b2.setFont(new Font("宋体", Font.BOLD, 25));
		p1.add(j1);
		p1.add(t1);
		p1.add(j3);
		p1.add(t3);
		p1.add(j2);
		p1.add(t2);
		p1.add(new JLabel());
		p1.add(new JLabel());
		p2.add(b1);
		p2.add(new JLabel());
		p2.add(b2);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderInfo = "";
				for (FlightInfo f : FlightController.flights) {
					if (f.getFlightID().equals(t1.getText()) && -1 != f.getDepartureDate().indexOf(t2.getText())) {
						orderInfo = FlightController.superQF(f);
						j.setText(orderInfo);
						break;
					}
				}
				if (orderInfo.equals("")) {
					JOptionPane.showMessageDialog(null, "航班不存在");
				}
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderInfo = "";
				for (Passenger pg : managePassenger.passengers) {
					if (pg.getPassengerId().equals(t3.getText()) || pg.getTeleNum().equals(t3.getText())
							|| pg.getUserName().equals(t3.getText()) || pg.getIdentityId().equals(t3.getText())
							|| pg.getRealName().equals(t3.getText())) {
						orderInfo = manageOrder.lookOrder(pg);
						j.setText(orderInfo);
						break;
					}
				}
				if (orderInfo.equals("")) {
					JOptionPane.showMessageDialog(null, "该用户不存在");
				}
			}
		});
		GridBagConstraints gb1 = new GridBagConstraints(0, 0, 1, 7, 100, 100, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(10, 0, 5, 20), 0, 0);
		GridBagConstraints gb2 = new GridBagConstraints(0, 7, 1, 2, 10, 10, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(10, 0, 5, 20), 0, 0);
		GridBagConstraints gb3 = new GridBagConstraints(0, 9, 1, 1, 20, 20, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(0, 20, 20, 20), 0, 0);
		con.add(sp1, gb1);
		con.add(p1, gb2);
		con.add(p2, gb3);
		my.setBounds(700, 20, 850, 600);
		my.setResizable(false);
		my.setVisible(true);
	}

}
