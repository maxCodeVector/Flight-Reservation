package controller;

import bean.FlightInfo;
import bean.Order;
import bean.Passenger;
import bean.State;
import util.Tool;
import util.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JOptionPane;

public class manageOrder {
	private static ArrayList<Order> orders = new ArrayList<>();

	static Date date = new Date();
	static String createDate = String.format("%tF", date);


	public static ArrayList<Order> getOrders() {
		return orders;
	}

	public static void setOrders(ArrayList<Order> orders) {
		manageOrder.orders = orders;
	}

	// �洢����
	public static void saveOrder() {
		FileWriter fw;
		try {
			fw = new FileWriter(Tool.getDataAdpater().getOrderPath());
			fw.write("");
			fw.close();
			fw = new FileWriter("resource/Order.dat", true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Order o : orders) {
				bw.write(o.getOrderNum() + "%");
				bw.write(o.getPassenger().getIdentityId() + "?");
				bw.write(o.getStatus() + "@");
				bw.write(o.getFlight().getFlightID() + "*");
				bw.write(o.getFlight().getDepartureDate() + "&");
				bw.write(o.getSeatNum() + "#");
				bw.write(o.getCreateDate() + ";");
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Ԥ������
	public static void bookOrder(String fID, String date1, Passenger pg) {
		for (FlightInfo f : FlightController.flights) {
			if (f.getFlightID().equals(fID) && -1 != f.getDepartureDate().indexOf(date1)) {
				if (f.getFlightStatus()==State.AVAILABLE) {
					String seat = String.format("A%02d", (f.getCurrentPassengers() + 1));
					for (Order or : orders) {
						if (or.getFlight().equals(f) && or.getStatus()==State.CANCEL) {
							seat = or.getSeatNum();
							orders.remove(or);
							break;
						}
					}
					String orNum = String.format("%s%s", f.getFlightID(), seat.substring(1));
					Order o = new Order(orNum, seat, State.UNPAID, pg, f, createDate);
					orders.add(o);
					String orderInfo = String.format(
							"Ԥ���ɹ�\n������:%s ����״̬��%s �۸�%s ��λ�ţ�%s �������ڣ�%s \n  ������Ϣ��%-7s%-5s%-5s%-6s%-6s%-11s\n",
							o.getOrderNum(), o.getStatus(), o.getFlight().getPrice(),
							o.getSeatNum(), o.getCreateDate(), o.getFlight().getFlightID(),
							o.getFlight().getStartCity(), o.getFlight().getArrivalCity(),
							o.getFlight().getStartTime(), o.getFlight().getArrivalTime(),
							o.getFlight().getDepartureDate());
					FlightController.currentPgFlight(f);
					manageFlight.saveFlight();
					JOptionPane.showMessageDialog(null, orderInfo);
				} else
					JOptionPane.showMessageDialog(null, "�޷�Ԥ����״̬�ĺ���");
				break;
			}
		}
		saveOrder();
	}

	public static void initial() {
		FileReader fw;
		try {
			fw = new FileReader(Tool.getDataAdpater().getOrderPath());
			BufferedReader input1 = new BufferedReader(fw);
			String s = null;
			while ((s = input1.readLine()) != null) {
				s = s.replace(" ", "");
				String orNum = s.substring(0, s.indexOf("%"));
				String pID = s.substring(s.indexOf("%") + 1, s.indexOf("?"));
				String stateTemp = s.substring(s.indexOf("?") + 1, s.indexOf("@"));
				State state = State.valueOf(stateTemp);
				String fId = s.substring(s.indexOf("@") + 1, s.indexOf("*"));
				String depDate = s.substring(s.indexOf("*") + 1, s.indexOf("&"));
				String seat = s.substring(s.indexOf("&") + 1, s.indexOf("#"));
				String creDate = s.substring(s.indexOf("#") + 1, s.indexOf(";"));

				for (FlightInfo f : FlightController.flights) {
					if (f.getFlightID().equals(fId) && f.getDepartureDate().equals(depDate)) {
						for (Passenger pg : managePassenger.passengers) {
							if (pg.getIdentityId().equals(pID)) {
								Order order = new Order(orNum, seat, state, pg, f, creDate);
								orders.add(order);
								break;
							}
						}
						break;
					}
				}
			}
			input1.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �˿Ͳ�ѯ�����ķ���
	public static String lookOrder(String orNum, Passenger pg) {
		String orderInfo = null;
		for (Order o : orders) {
			if (o.getOrderNum().equals(orNum) && o.getPassenger().equals(pg)) {
				orderInfo = String.format(
						"��ѯ���\n������:%s ����״̬��%s �۸�%s ��λ�ţ�%s �������ڣ�%s \n  ������Ϣ��%-7s%-5s%-5s%-6s%-6s%-11s\n",
						o.getOrderNum(), o.getStatus(), o.getFlight().getPrice(),
						o.getSeatNum(), o.getCreateDate(), o.getFlight().getFlightID(),
						o.getFlight().getStartCity(), o.getFlight().getArrivalCity(),
						o.getFlight().getStartTime(), o.getFlight().getArrivalTime(),
						o.getFlight().getDepartureDate());
				break;
			}
		}
		return orderInfo;
	}

	// ����Ա��ѯ�����ķ���
	public static String lookOrder(Passenger pg) {
		Collections.sort(orders, tools.orderComp);
		String a = pg.getRealName() + "\n";
		int counter = 0;
		for (Order o : manageOrder.orders) {
			if (o.getPassenger().equals(pg)) {
				counter++;
				a = a + (String.format(
						counter + ".������:%s ����״̬��%s �۸�%s ��λ�ţ�%s �������ڣ�%s" + "\n  ������Ϣ��%-7s%-5s%-5s%-6s%-6s%-11s\n\n",
						o.getOrderNum(), o.getStatus(), o.getFlight().getPrice(),
						o.getSeatNum(), o.getCreateDate(), o.getFlight().getFlightID(),
						o.getFlight().getStartCity(), o.getFlight().getArrivalCity(),
						o.getFlight().getStartTime(), o.getFlight().getArrivalTime(),
						o.getFlight().getDepartureDate()));
			}
		}
		return a;
	}

	// �޸ĺ���״̬�ķ���
	public static void modOrder(String orNum, State status) {
		for (Order o : orders) {
			if (o.getOrderNum().equals(orNum))
				o.setStatus(status);
			for (FlightInfo f : FlightController.flights) {
				if (o.getFlight().equals(f)) {
					FlightController.currentPgFlight(f);
					manageFlight.saveFlight();
					break;
				}
			}
		}
		saveOrder();
	}

}
