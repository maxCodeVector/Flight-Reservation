package frames;

import bean.Admin;
import bean.FlightInfo;
import controller.manageFlight;
import util.tools;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * FlightFrameFactory��(Creat ,Look)
 * @author hya
 */
public class FlightFrameFactory {

	// ������Ӻ��ര��
	public static void creatBaseFlightFrame() {
		JFrame my = new JFrame("����Ա��������");
		Container con = my.getContentPane();
		my.setLayout(new GridBagLayout());
		JPanel p3 = new JPanel(new GridLayout(7, 2, 10, 55));
		JPanel p4 = new JPanel(new GridLayout(3, 2, 100, 50));
		JLabel j1 = new JLabel("FlightID", SwingConstants.CENTER);
		JLabel j2 = new JLabel("startCity", SwingConstants.CENTER);
		JLabel j3 = new JLabel("arrivalCity", SwingConstants.CENTER);
		JLabel j4 = new JLabel("startTime", SwingConstants.CENTER);
		JLabel j5 = new JLabel("arrivalTime", SwingConstants.CENTER);
		JLabel j6 = new JLabel("departureDate", SwingConstants.CENTER);
		JLabel j7 = new JLabel("price", SwingConstants.CENTER);
		JLabel j8 = new JLabel("seatCapacity", SwingConstants.CENTER);
		JButton b1 = new JButton("ȷ������");
		JButton b2 = new JButton("����");
		JTextField[] t = new JTextField[8];
		t[0] = new JTextField("");
		t[1] = new JTextField("");
		t[2] = new JTextField("");
		t[3] = new JTextField("xx:xx");
		t[4] = new JTextField("xx:xx");
		t[5] = new JTextField("xxxx-xx-xx");
		t[6] = new JTextField("");
		t[7] = new JTextField("");
		j1.setFont(new Font("����", Font.BOLD, 25));
		j2.setFont(new Font("����", Font.BOLD, 25));
		j2.setFont(new Font("����", Font.BOLD, 25));
		j3.setFont(new Font("����", Font.BOLD, 25));
		j4.setFont(new Font("����", Font.BOLD, 25));
		j5.setFont(new Font("����", Font.BOLD, 25));
		j6.setFont(new Font("����", Font.BOLD, 25));
		j7.setFont(new Font("����", Font.BOLD, 25));
		j8.setFont(new Font("����", Font.BOLD, 25));
		for (JTextField text : t)
			text.setFont(new Font("songti", Font.BOLD, 30));
		b1.setFont(new Font("����", Font.BOLD, 30));
		b2.setFont(new Font("����", Font.BOLD, 30));
		b1.addActionListener(e -> {
			if (manageFlight.creatFlight(t[0].getText(), t[1].getText(), t[2].getText(), t[3].getText(), t[4].getText(),
					t[5].getText(), t[6].getText(), t[7].getText())) {
				t[0].setText("");
				t[1].setText("");
				t[2].setText("");
				t[3].setText("xx:xx");
				t[4].setText("xx:xx");
				t[5].setText("xxxx-xx-xx");
				t[6].setText("");
				t[7].setText("");
			}
		});
		b2.addActionListener(e -> my.setVisible(false));

		GridBagConstraints gb3 = new GridBagConstraints(0, 0, 2, 6, 10, 10, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(10, 0, 0, 20), 0, 0);
		GridBagConstraints gb4 = new GridBagConstraints(2, 0, 2, 3, 10, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0);
		p3.add(j1);
		p3.add(t[0]);
		p3.add(j2);
		p3.add(t[1]);
		p3.add(j3);
		p3.add(t[2]);
		p3.add(j4);
		p3.add(t[3]);
		p3.add(j5);
		p3.add(t[4]);
		p3.add(j6);
		p3.add(t[5]);

		p4.add(j7);
		p4.add(t[6]);
		p4.add(j8);
		p4.add(t[7]);
		p4.add(b1);
		p4.add(b2);
		con.add(p3, gb3);
		con.add(p4, gb4);
		my.setBounds(300, 200, 1000, 700);
		my.setResizable(false);
		my.setVisible(true);
	}

	// ������ѯ���ര��
	public static void queryFlightsFrame(Admin ad, final ArrayList<FlightInfo> flights) {
		JFrame my = new JFrame("��ѯ����");
		Container con = my.getContentPane();
		my.setLayout(new GridLayout(3, 1, 5, 5));
		String a = "����״̬   �����  �� ��  �� �� ���ʱ�� ����ʱ��  ��������  �۸� ����\n";
		for (FlightInfo f : flights) {
			a = a + tools.showFlight(ad, f);
		}
		JTextArea j = new JTextArea(a);
		JTextArea t = new JTextArea("��ѯ���");
		JScrollPane sp1 = new JScrollPane(j);
		JScrollPane sp2 = new JScrollPane(t);
		JPanel p1 = new JPanel(new GridLayout(3, 4, 50, 40));
		JButton b1 = new JButton("������ѯ");
		JButton b2 = new JButton("����Ų���");
		JButton b3 = new JButton("�������");
		JButton b4 = new JButton("����");
		JLabel j1 = new JLabel("��������", SwingConstants.CENTER);
		JLabel j2 = new JLabel("�������", SwingConstants.CENTER);
		JLabel j3 = new JLabel("��������", SwingConstants.CENTER);
		JLabel j4 = new JLabel("�����", SwingConstants.CENTER);
		JTextField t1 = new JTextField("");
		JTextField t2 = new JTextField("");
		JTextField t3 = new JTextField("");
		JTextField t4 = new JTextField("");
		j.setFont(new Font("����", Font.BOLD, 20));
		t.setFont(new Font("����", Font.BOLD, 20));
		t1.setFont(new Font("����", Font.BOLD, 20));
		t2.setFont(new Font("����", Font.BOLD, 20));
		t3.setFont(new Font("����", Font.BOLD, 20));
		t4.setFont(new Font("����", Font.BOLD, 20));
		j1.setFont(new Font("����", Font.BOLD, 20));
		j2.setFont(new Font("����", Font.BOLD, 20));
		j3.setFont(new Font("����", Font.BOLD, 20));
		j4.setFont(new Font("����", Font.BOLD, 20));
		b1.setFont(new Font("����", Font.BOLD, 20));
		b2.setFont(new Font("����", Font.BOLD, 20));
		b3.setFont(new Font("����", Font.BOLD, 20));
		b4.setFont(new Font("����", Font.BOLD, 20));
		b3.setVisible(false);
		if (ad != null) {
			b3.setVisible(true);
		}
		p1.add(j1);
		p1.add(t1);
		p1.add(j3);
		p1.add(t3);
		p1.add(j2);
		p1.add(t2);
		p1.add(j4);
		p1.add(t4);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		b1.addActionListener(e-> {
				String ini = "��ѯ���";
				String ab = "";
				for (FlightInfo f : flights) {
					if ((-1 != f.getStartCity().indexOf(t1.getText()))
							&& (-1 != f.getArrivalCity().indexOf(t2.getText()))
							&& (-1 != f.getDepartureDate().indexOf(t3.getText()))) {
						ab += tools.showFlight(ad, f);
					}
				}
				if (ab.equals("")) {
					t.setText(ini + "\n�鲻���ú�����Ϣ��");
				} else {
					t.setText(ini + "\n" + ab);
				}
			});
		b2.addActionListener(e -> {
			String ini = "��ѯ���";
			String a1 = "";
			for (FlightInfo f : flights) {
				if (-1 != f.getFlightID().indexOf(t4.getText())) {
					a1 = a1 + tools.showFlight(ad, f);
				}
			}
			if (a1.equals("")) {
				t.setText(ini + "\n�鲻���ú�����Ϣ��");
			} else {
				t.setText(ini + "\n" + a1);
			}

		});
		b3.addActionListener(e -> new updeleFlightFrame());
		b4.addActionListener(e->my.setVisible(false));
		con.add(sp1);
		con.add(sp2);
		con.add(p1);
		my.setBounds(900, 50, 800, 700);
		my.setResizable(false);
		my.setVisible(true);
	}

}
