package frames;

import bean.FlightInfo;
import bean.State;
import controller.FlightController;
import controller.manageFlight;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class updeleFlightFrame {// �����޸ĺ���Ĵ���

	updeleFlightFrame() {
		JFrame my = new JFrame("����Ա�޸ĺ���");
		Container con = my.getContentPane();
		my.setLayout(new GridBagLayout());
		JPanel p3 = new JPanel(new GridLayout(8, 2, 40, 40));
		JPanel p4 = new JPanel(new GridLayout(5, 2, 100, 50));
		JLabel j1 = new JLabel("��������Ҫ�ĺ����", SwingConstants.CENTER);
		JLabel j2 = new JLabel("�������", SwingConstants.CENTER);
		JLabel j3 = new JLabel("startCity", SwingConstants.CENTER);
		JLabel j4 = new JLabel("arrivalCity", SwingConstants.CENTER);
		JLabel j5 = new JLabel("startTime", SwingConstants.CENTER);
		JLabel j6 = new JLabel("arrivalTime", SwingConstants.CENTER);
		JLabel j7 = new JLabel("price", SwingConstants.CENTER);
		JLabel j8 = new JLabel("seatCapacity", SwingConstants.CENTER);
		JLabel j9 = new JLabel("flightStatus", SwingConstants.CENTER);
		JButton b1 = new JButton("ȷ��");
		JButton b2 = new JButton("��������");
		JButton b3 = new JButton("ȷ���޸�");
		JButton b4 = new JButton("ȷ��ɾ��");
		JButton b5 = new JButton("��������");
		JButton b6 = new JButton("ȡ������");
		JTextField t1 = new JTextField("");
		JTextField t2 = new JTextField("");
		JTextField t3 = new JTextField("");
		JTextField t4 = new JTextField("");
		JTextField t5 = new JTextField("");
		JTextField t6 = new JTextField("");
		JTextField t7 = new JTextField("");
		JTextField t8 = new JTextField("");
		JTextField t9 = new JTextField("");
		j1.setFont(new Font("����", Font.BOLD, 25));
		j2.setFont(new Font("����", Font.BOLD, 25));
		j2.setFont(new Font("����", Font.BOLD, 25));
		j3.setFont(new Font("����", Font.BOLD, 25));
		j4.setFont(new Font("����", Font.BOLD, 25));
		j5.setFont(new Font("����", Font.BOLD, 25));
		j6.setFont(new Font("����", Font.BOLD, 25));
		j7.setFont(new Font("����", Font.BOLD, 25));
		j8.setFont(new Font("����", Font.BOLD, 25));
		j9.setFont(new Font("����", Font.BOLD, 25));
		t1.setFont(new Font("����", Font.BOLD, 30));
		t2.setFont(new Font("����", Font.BOLD, 30));
		t3.setFont(new Font("����", Font.BOLD, 30));
		t4.setFont(new Font("����", Font.BOLD, 30));
		t5.setFont(new Font("����", Font.BOLD, 30));
		t6.setFont(new Font("����", Font.BOLD, 30));
		t7.setFont(new Font("����", Font.BOLD, 30));
		t8.setFont(new Font("����", Font.BOLD, 30));
		t9.setFont(new Font("����", Font.BOLD, 25));
		b1.setFont(new Font("����", Font.BOLD, 30));
		b2.setFont(new Font("����", Font.BOLD, 30));
		b3.setFont(new Font("����", Font.BOLD, 30));
		b4.setFont(new Font("����", Font.BOLD, 30));
		b5.setFont(new Font("����", Font.BOLD, 30));
		b6.setFont(new Font("����", Font.BOLD, 30));
		b3.setEnabled(false);
		b4.setEnabled(false);
		b5.setEnabled(false);
		b6.setEnabled(false);
		t9.setEditable(false);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean a = true;
				for (FlightInfo f : FlightController.flights) {
					if (f.getFlightID().equals(t1.getText()) && f.getDepartureDate().contains(t2.getText())) {
						t2.setText(f.getDepartureDate());
						t3.setText(f.getStartCity());
						t4.setText(f.getArrivalCity());
						t5.setText(f.getStartTime());
						t6.setText(f.getArrivalTime());
						t7.setText(f.getPrice());
						t8.setText(f.getSeatCapacity());
						t9.setText(f.getFlightStatus().getState());
						t1.setEditable(false);
						t2.setEditable(false);
						a = false;
						if(f.getFlightStatus()==State.UNPUBLISHED){
							t3.setEditable(true);
							t4.setEditable(true);
							t5.setEditable(true);
							t6.setEditable(true);
							b3.setEnabled(true);
							b4.setEnabled(true);
							b5.setEnabled(true);
							b6.setEnabled(false);
						}
						else if(f.getFlightStatus()==State.TERMINATE) {
							JOptionPane.showMessageDialog(null, "��Сʱ֮�ڽ���ɵĺ��಻�ɲ���");
							b3.setEnabled(false);
							b4.setEnabled(true);
							b5.setEnabled(false);
							b6.setEnabled(false);
						} else{
							t3.setEditable(false);
							t4.setEditable(false);
							t5.setEditable(false);
							t6.setEditable(false);
							b3.setEnabled(true);
							b4.setEnabled(false);
							b5.setEnabled(false);
							b6.setEnabled(true);
						}
					}
				}
				if (a) {
					JOptionPane.showMessageDialog(null, "�ú��಻����");
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
		b3.addActionListener(e -> {
			boolean right = manageFlight.checkFlight(t1.getText(), t3.getText(), t4.getText(), t5.getText(),
					t6.getText(), t2.getText(), t7.getText(), t8.getText());

			if (right) {
				for (FlightInfo fl : FlightController.flights) {
					if (fl.getFlightID().equals(t1.getText()) && fl.getDepartureDate().contains(t2.getText())) {
						FlightController.flights.remove(fl);
						FlightInfo f = new FlightInfo(
								t1.getText(), t3.getText(), t4.getText(), t5.getText(), t6.getText(),
								t2.getText(), t7.getText(), t8.getText(),
								fl.getFlightStatus(), fl.getCurrentPassengers());
						FlightController.flights.add(f);
						manageFlight.saveFlight();
						String a = String.format("�޸ĳɹ�\n%-7s%-4S%-4S%-6s%-6s%-10s����%-3s�۸�%-4s\n",
								f.getFlightID(),
								f.getStartCity(), f.getArrivalCity(),
								f.getStartTime(), f.getArrivalTime(), f.getDepartureDate(),
								f.getSeatCapacity(), f.getPrice());
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
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (FlightInfo fl : FlightController.flights) {
					if (fl.getFlightID().equals(t1.getText()) && fl.getDepartureDate().contains(t2.getText())) {
						FlightController.flights.remove(fl);
						break;
					}
				}
				manageFlight.saveFlight();
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
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
				for (FlightInfo fl : FlightController.flights) {
					if (fl.getFlightID().equals(t1.getText()) && fl.getDepartureDate().contains(t2.getText())) {
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
				JOptionPane.showMessageDialog(null, "�����ɹ�");
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (FlightInfo fl : FlightController.flights) {
					if (fl.getFlightID().equals(t1.getText()) && fl.getDepartureDate().contains(t2.getText())) {
						fl.setFlightStatus(State.UNPUBLISHED);
						break;
					}
				}
				manageFlight.saveFlight();
				JOptionPane.showMessageDialog(null, "��ȡ������");
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
