package plane;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

//对航班时间顺序排序的比较器
class flightComaprtor implements Comparator<Object> {
	public int compare(Object obj1, Object obj2) {
		Flight f1 = (Flight) obj1;
		Flight f2 = (Flight) obj2;
		return tools.compareTime(f1.getExactTime(), f2.getExactTime()) ? 0 : -1;
	}
}

// 对订单产生时间顺序排序的比较器
class orderComaprtor implements Comparator<Object> {
	public int compare(Object obj1, Object obj2) {
		Order o1 = (Order) obj1;
		Order o2 = (Order) obj2;
		String t1 = o1.getCreateDate() + " 00:" + o1.seatNum.indexOf(1);
		String t2 = o2.getCreateDate() + " 00:" + o2.seatNum.indexOf(1);
		return tools.compareTime(t1, t2) ? 0 : -1;
	}
}

enum State {
	UNPUBLISHED("UNPUBLISHED"), AVAILABLE("AVAILABLE"), FULL("FULL"), TERMINATE("TERMINATE"), UNPAID("UNPAID"), PAID(
			"PAID"), CANCEL("CANCEL");
	private String state;

	private State(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}
}

public class tools {

	// 获得系统时间和日期
	Date date = new Date();
	String time = String.format("%tF,%tT", date, date);

	// 获取当前时间之前或之后几 小时hour
	public static String getTimeByHour(int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(calendar.getTime());
	}

	// 获取当前时间之前或之后几分钟 minute
	public static String getTimeByMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	// 查询订单时跳出验证登录密码的对话框
	public static void queryOrderDialog(Passenger pg) {
		JDialog my = new JDialog();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(2, 2, 50, 50));
		JLabel j1 = new JLabel("验证登录密码", SwingConstants.CENTER);
		JPasswordField ps = new JPasswordField();
		JButton b1 = new JButton("确定");
		JButton b2 = new JButton("取消");
		j1.setFont(new Font("宋体", Font.BOLD, 30));
		ps.setFont(new Font("宋体", Font.BOLD, 30));
		b1.setFont(new Font("宋体", Font.BOLD, 30));
		b2.setFont(new Font("宋体", Font.BOLD, 30));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(ps.getPassword()).equals(pg.getPassword())) {
					Flight.flush();
					pgCennter.myOr(pg);
					my.setVisible(false);
				} else
					JOptionPane.showMessageDialog(null, "密码错误");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my.setVisible(false);
			}
		});
		con.add(j1);
		con.add(ps);
		con.add(b1);
		con.add(b2);
		my.setBounds(600, 600, 500, 200);
		my.setVisible(true);
	}

	// 支付订单时跳出验证支付密码的对话框
	public static void paidDialog(String orNum) {
		JDialog my = new JDialog();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(2, 2, 50, 50));
		JLabel j1 = new JLabel("输入支付密码", SwingConstants.CENTER);
		JPasswordField ps = new JPasswordField();
		JButton b1 = new JButton("确定支付");
		JButton b2 = new JButton("暂不支付");
		j1.setFont(new Font("宋体", Font.BOLD, 30));
		ps.setFont(new Font("宋体", Font.BOLD, 30));
		b1.setFont(new Font("宋体", Font.BOLD, 30));
		b2.setFont(new Font("宋体", Font.BOLD, 30));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(ps.getPassword()).equals("Y")) {
					manageOrder.modOrder(orNum, State.PAID);
					JOptionPane.showMessageDialog(null, "支付成功");
					my.setVisible(false);
				} else
					JOptionPane.showMessageDialog(null, "密码错误");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my.setVisible(false);
			}
		});
		con.add(j1);
		con.add(ps);
		con.add(b1);
		con.add(b2);
		my.setBounds(600, 500, 500, 200);
		my.setVisible(true);
	}

	// 取消订单时跳出验证支付密码的对话框
	public static void unsubscribeDialog(String orNum) {
		JDialog my = new JDialog();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(2, 2, 50, 50));
		JLabel j1 = new JLabel("输入支付密码", SwingConstants.CENTER);
		JPasswordField ps = new JPasswordField();
		JButton b1 = new JButton("确定取消");
		JButton b2 = new JButton("暂不取消");
		j1.setFont(new Font("宋体", Font.BOLD, 30));
		ps.setFont(new Font("宋体", Font.BOLD, 30));
		b1.setFont(new Font("宋体", Font.BOLD, 30));
		b2.setFont(new Font("宋体", Font.BOLD, 30));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(ps.getPassword()).equals("Y")) {
					manageOrder.modOrder(orNum, State.CANCEL);
					JOptionPane.showMessageDialog(null, "退款已返还");
					my.setVisible(false);
				} else
					JOptionPane.showMessageDialog(null, "密码错误");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my.setVisible(false);
			}
		});
		con.add(j1);
		con.add(ps);
		con.add(b1);
		con.add(b2);
		my.setBounds(650, 600, 500, 200);
		my.setVisible(true);
	}

	// 比较时间t是否晚于系统时间hour小时以上,如果是，返回true
	public static boolean compareSystemTime(String t, int hour) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		boolean a = false;
		try {
			Date time1 = sdf.parse(t);
			Date time2 = sdf.parse(getTimeByHour(hour));
			a = time1.getTime() >= time2.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return a;
	}

	// 比较两个时间顺序,如果t1晚于或等于t2,返回true
	public static boolean compareTime(String t1, String t2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		boolean a = false;
		try {
			Date time1 = sdf.parse(t1);
			Date time2 = sdf.parse(t2);
			a = time1.getTime() > time2.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return a;
	}

	// 设置添加图片的方法
	public static JPanel Samp(JPanel p, String path) {
		ImageIcon background = new ImageIcon(path);
		JLabel l = new JLabel(background);
		l.setBounds(0, 0, p.getWidth(), p.getHeight()); // 把标签的大小位置设置为图片刚好填充整个面板
		p.add(l);
		return p;// 返回一个带有指定图片的JPanel面板
	}

	public static boolean checkTime(String time) {
		if (time.matches("[012]{1}\\d{1}:[012345]\\d{1}")) {
			int hour = Integer.parseInt(time.substring(0, 2));
			if (hour <= 24) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean checkdate(String date) {
		if (date.matches("2017-\\d{2}-\\d{2}")) {
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));
			if (month <= 12 && month > 0 && day <= 31 && day > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String showFlight(Admin ad, Flight f) {
		String a = "";
		if (ad != null) {
			a = a + (String.format("%-12s %-7s%-3S%-3S%-6s%-6s%-11s%-5s%-3s\n", f.flightStatus, f.FlightID, f.startCity,
					f.arrivalCity, f.startTime, f.arrivalTime, f.departureDate, f.price, f.seatCapacity));
		} else if (!f.flightStatus.equals(State.UNPUBLISHED.getState())) {
			a = a + (String.format("%-12s %-7s%-3S%-3S%-6s%-6s%-11s%-5s%-3s\n", f.flightStatus, f.FlightID, f.startCity,
					f.arrivalCity, f.startTime, f.arrivalTime, f.departureDate, f.price, f.seatCapacity));
		}
		return a;
	}

}
