package plane;

import util.Tool;

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

	// orders�����set��get����
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
				bw.write(o.orderNum + "%");
				bw.write(o.passenger.getIdentityId() + "?");
				bw.write(o.status + "@");
				bw.write(o.flight.FlightID + "*");
				bw.write(o.flight.departureDate + "&");
				bw.write(o.seatNum + "#");
				bw.write(o.createDate + ";");
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
		for (Flight f : manageFlight.flights) {
			if (f.FlightID.equals(fID) && -1 != f.departureDate.indexOf(date1)) {
				if (f.flightStatus.equals(State.AVAILABLE.getState())) {
					String seat = String.format("A%02d", (f.currentPassengers + 1));
					for (Order or : orders) {
						if (or.flight.equals(f) && or.status.equals(State.CANCEL.getState())) {
							seat = or.seatNum;
							orders.remove(or);
							break;
						}
					}
					String orNum = String.format("%s%s", f.FlightID, seat.substring(1));
					Order o = new Order(orNum, seat, State.UNPAID.getState(), pg, f, createDate);
					orders.add(o);
					String orderInfo = String.format(
							"Ԥ���ɹ�\n������:%s ����״̬��%s �۸�%s ��λ�ţ�%s �������ڣ�%s \n  ������Ϣ��%-7s%-5s%-5s%-6s%-6s%-11s\n",
							o.orderNum, o.status, o.flight.price, o.seatNum, o.createDate, o.flight.FlightID,
							o.flight.startCity, o.flight.arrivalCity, o.flight.startTime, o.flight.arrivalTime,
							o.flight.departureDate);
					Flight.currentPgFlight(f);
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
				String state = s.substring(s.indexOf("?") + 1, s.indexOf("@"));
				String fId = s.substring(s.indexOf("@") + 1, s.indexOf("*"));
				String depDate = s.substring(s.indexOf("*") + 1, s.indexOf("&"));
				String seat = s.substring(s.indexOf("&") + 1, s.indexOf("#"));
				String creDate = s.substring(s.indexOf("#") + 1, s.indexOf(";"));

				for (Flight f : manageFlight.flights) {
					if (f.FlightID.equals(fId) && f.departureDate.equals(depDate)) {
						for (Passenger pg : managePassenger.array) {
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
			if (o.orderNum.equals(orNum) && o.passenger.equals(pg)) {
				orderInfo = String.format(
						"��ѯ���\n������:%s ����״̬��%s �۸�%s ��λ�ţ�%s �������ڣ�%s \n  ������Ϣ��%-7s%-5s%-5s%-6s%-6s%-11s\n", o.orderNum,
						o.status, o.flight.price, o.seatNum, o.createDate, o.flight.FlightID, o.flight.startCity,
						o.flight.arrivalCity, o.flight.startTime, o.flight.arrivalTime, o.flight.departureDate);
				break;
			}
		}
		return orderInfo;
	}

	// ����Ա��ѯ�����ķ���
	public static String lookOrder(Passenger pg) {
		Collections.sort(orders, new orderComaprtor());
		String a = pg.getRealName() + "\n";
		int counter = 0;
		for (Order o : manageOrder.orders) {
			if (o.passenger.equals(pg)) {
				counter++;
				a = a + (String.format(
						counter + ".������:%s ����״̬��%s �۸�%s ��λ�ţ�%s �������ڣ�%s" + "\n  ������Ϣ��%-7s%-5s%-5s%-6s%-6s%-11s\n\n",
						o.orderNum, o.status, o.flight.price, o.seatNum, o.createDate, o.flight.FlightID,
						o.flight.startCity, o.flight.arrivalCity, o.flight.startTime, o.flight.arrivalTime,
						o.flight.departureDate));
			}
		}
		return a;
	}

	// �޸ĺ���״̬�ķ���
	public static void modOrder(String orNum, State status) {
		for (Order o : orders) {
			if (o.orderNum.equals(orNum))
				o.setStatus(status);
			for (Flight f : manageFlight.flights) {
				if (o.flight.equals(f)) {
					Flight.currentPgFlight(f);
					manageFlight.saveFlight();
					break;
				}
			}
		}
		saveOrder();
	}

}
