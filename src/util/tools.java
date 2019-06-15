package util;

import bean.*;
import controller.FlightController;
import bean.State;
import controller.manageOrder;
import frames.pgCennterFrame;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

public class tools {
	public static FlightComaprtor flightComp = new FlightComaprtor();
	public static OrderComaprtor orderComp = new OrderComaprtor();

	static class FlightComaprtor implements Comparator<Object> {
		public int compare(Object obj1, Object obj2) {
			FlightInfo f1 = (FlightInfo) obj1;
			FlightInfo f2 = (FlightInfo) obj2;
			return tools.compareTime(f1.getExactTime(), f2.getExactTime()) ? 0 : -1;
		}
	}

	static class OrderComaprtor implements Comparator<Object> {
		public int compare(Object obj1, Object obj2) {
			Order o1 = (Order) obj1;
			Order o2 = (Order) obj2;
			String t1 = o1.getCreateDate() + " 00:" + o1.getSeatNum().indexOf(1);
			String t2 = o2.getCreateDate() + " 00:" + o1.getSeatNum().indexOf(1);
			return tools.compareTime(t1, t2) ? 0 : -1;
		}
	}


	// ���ϵͳʱ�������
	Date date = new Date();
	String time = String.format("%tF,%tT", date, date);

	// ��ȡ��ǰʱ��֮ǰ��֮�� Сʱhour
	public static String getTimeByHour(int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(calendar.getTime());
	}

	// ��ȡ��ǰʱ��֮ǰ��֮�󼸷��� minute
	public static String getTimeByMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	// ��ѯ����ʱ������֤��¼����ĶԻ���
	public static void queryOrderDialog(Passenger pg) {
		JDialog my = new JDialog();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(2, 2, 50, 50));
		JLabel j1 = new JLabel("��֤��¼����", SwingConstants.CENTER);
		JPasswordField ps = new JPasswordField();
		JButton b1 = new JButton("ȷ��");
		JButton b2 = new JButton("ȡ��");
		j1.setFont(new Font("����", Font.BOLD, 30));
		ps.setFont(new Font("����", Font.BOLD, 30));
		b1.setFont(new Font("����", Font.BOLD, 30));
		b2.setFont(new Font("����", Font.BOLD, 30));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(ps.getPassword()).equals(pg.getPassword())) {
					FlightController.flush();
					pgCennterFrame.myOr(pg);
					my.setVisible(false);
				} else
					JOptionPane.showMessageDialog(null, "�������");
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

	// ֧������ʱ������֤֧������ĶԻ���
	public static void paidDialog(String orNum) {
		JDialog my = new JDialog();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(2, 2, 50, 50));
		JLabel j1 = new JLabel("����֧������", SwingConstants.CENTER);
		JPasswordField ps = new JPasswordField();
		JButton b1 = new JButton("ȷ��֧��");
		JButton b2 = new JButton("�ݲ�֧��");
		j1.setFont(new Font("����", Font.BOLD, 30));
		ps.setFont(new Font("����", Font.BOLD, 30));
		b1.setFont(new Font("����", Font.BOLD, 30));
		b2.setFont(new Font("����", Font.BOLD, 30));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(ps.getPassword()).equals("Y")) {
					manageOrder.modOrder(orNum, State.PAID);
					JOptionPane.showMessageDialog(null, "֧���ɹ�");
					my.setVisible(false);
				} else
					JOptionPane.showMessageDialog(null, "�������");
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

	// ȡ������ʱ������֤֧������ĶԻ���
	public static void unsubscribeDialog(String orNum) {
		JDialog my = new JDialog();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(2, 2, 50, 50));
		JLabel j1 = new JLabel("����֧������", SwingConstants.CENTER);
		JPasswordField ps = new JPasswordField();
		JButton b1 = new JButton("ȷ��ȡ��");
		JButton b2 = new JButton("�ݲ�ȡ��");
		j1.setFont(new Font("����", Font.BOLD, 30));
		ps.setFont(new Font("����", Font.BOLD, 30));
		b1.setFont(new Font("����", Font.BOLD, 30));
		b2.setFont(new Font("����", Font.BOLD, 30));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(ps.getPassword()).equals("Y")) {
					manageOrder.modOrder(orNum, State.CANCEL);
					JOptionPane.showMessageDialog(null, "�˿��ѷ���");
					my.setVisible(false);
				} else
					JOptionPane.showMessageDialog(null, "�������");
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

	// �Ƚ�ʱ��t�Ƿ�����ϵͳʱ��hourСʱ����,����ǣ�����true
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

	// �Ƚ�����ʱ��˳��,���t1���ڻ����t2,����true
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

	// �������ͼƬ�ķ���
	public static JPanel Samp(JPanel p, String path) {
		ImageIcon background = new ImageIcon(path);
		JLabel l = new JLabel(background);
		l.setBounds(0, 0, p.getWidth(), p.getHeight()); // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
		p.add(l);
		return p;// ����һ������ָ��ͼƬ��JPanel���
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

	public static String showFlight(Admin ad, FlightInfo f) {
		String a = "";
		if (ad != null) {
			a = a + (String.format("%-12s %-7s%-3S%-3S%-6s%-6s%-11s%-5s%-3s\n",
					f.getFlightStatus(), f.getFlightID(),
					f.getStartCity(), f.getArrivalCity(),
					f.getStartTime(), f.getArrivalTime(),
					f.getDepartureDate(), f.getPrice(), f.getSeatCapacity()));
		} else if (f.getFlightStatus()!=State.UNPUBLISHED) {
			a = a + (String.format("%-12s %-7s%-3S%-3S%-6s%-6s%-11s%-5s%-3s\n",
					f.getFlightStatus(), f.getFlightID(),
					f.getStartCity(), f.getArrivalCity(),
					f.getStartTime(), f.getArrivalTime(),
					f.getDepartureDate(), f.getPrice(), f.getSeatCapacity()));
		}
		return a;
	}

}
