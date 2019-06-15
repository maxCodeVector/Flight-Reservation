package plane;
/**
 * Flight �Ĺ����࣬�����˺���Ӧ�еĹ���
 * ������Ӻ��࣬�޸ĺ��ࡣ
 */

import util.Tool;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class manageFlight {
	// �������������flights
	static ArrayList<Flight> flights = new ArrayList<>();

	// ������Ӻ��๦��
	public static boolean creatFlight(String a1, String a2, String a3, String a4, String a5, String a6, String a7,
			String a8) {
		boolean j = checkFlight(a1, a2, a3, a4, a5, a6, a7, a8);
		FileWriter fw;
		try {
			fw = new FileWriter(Tool.getDataAdpater().getFlightPath(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			if (j) {
				Flight flight = new Flight(a1, a2, a3, a4, a5, a6, a7, a8, State.UNPUBLISHED.getState(), 0);
				flights.add(flight);
				int a = flights.size() - 1;
				bw.write(flights.get(a).FlightID + "%");
				bw.write(flights.get(a).startCity + "?");
				bw.write(flights.get(a).arrivalCity + "@");
				bw.write(flights.get(a).startTime + "*");
				bw.write(flights.get(a).arrivalTime + "#");
				bw.write(flights.get(a).departureDate + "^");
				bw.write(flights.get(a).price + "$");
				bw.write(flights.get(a).seatCapacity + "!");
				bw.write(flights.get(a).flightStatus + "/");
				bw.write(flights.get(a).currentPassengers + ";");
				bw.newLine();
				bw.close();
				fw.close();
				String temp = String.format("��ӳɹ�\n%-7s%-3S%-3S%-6s%-6s%-10s\n", flight.FlightID, flight.startCity,
						flight.arrivalCity, flight.startTime, flight.arrivalTime, flight.departureDate, flight.price,
						flight.seatCapacity);
				JOptionPane.showMessageDialog(null, temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return j;
	}

	// ���������ݴ��ļ����뵽����flights
	public static void initial() {
		FileReader fw;
		try {
			fw = new FileReader(Tool.getDataAdpater().getFlightPath());
			BufferedReader input1 = new BufferedReader(fw);
			String s = null;
			while ((s = input1.readLine()) != null) {
				s = s.replace(" ", "");
				String s1 = s.substring(0, s.indexOf("%"));
				String s2 = s.substring(s.indexOf("%") + 1, s.indexOf("?"));
				String s3 = s.substring(s.indexOf("?") + 1, s.indexOf("@"));
				String s4 = s.substring(s.indexOf("@") + 1, s.indexOf("*"));
				String s5 = s.substring(s.indexOf("*") + 1, s.indexOf("#"));
				String s6 = s.substring(s.indexOf("#") + 1, s.indexOf("^"));
				String s7 = s.substring(s.indexOf("^") + 1, s.indexOf("$"));
				String s8 = s.substring(s.indexOf("$") + 1, s.indexOf("!"));
				String s9 = s.substring(s.indexOf("!") + 1, s.indexOf("/"));
				int s10 = Integer.parseInt(s.substring(s.indexOf("/") + 1, s.indexOf(";")));
				Flight f = new Flight(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
				flights.add(f);
			}
			input1.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���ø����޸ĺ󺽰����ϵĹ���
	public static void saveFlight() {
		FileWriter fw;
		try {
			fw = new FileWriter(Tool.getDataAdpater().getFlightPath());
			fw.write("");
			fw.close();
			fw = new FileWriter(Tool.getDataAdpater().getFlightPath(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < flights.size(); i++) {
				bw.write(flights.get(i).FlightID + "%");
				bw.write(flights.get(i).startCity + "?");
				bw.write(flights.get(i).arrivalCity + "@");
				bw.write(flights.get(i).startTime + "*");
				bw.write(flights.get(i).arrivalTime + "#");
				bw.write(flights.get(i).departureDate + "^");
				bw.write(flights.get(i).price + "$");
				bw.write(flights.get(i).seatCapacity + "!");
				bw.write(flights.get(i).flightStatus + "/");
				bw.write(flights.get(i).currentPassengers + ";");
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �����д�ĺ�����Ϣ�Ƿ���ϸ�ʽ��ʱ���Ƿ����Ҫ��
	public static boolean checkFlight(String a1, String a2, String a3, String a4, String a5, String a6, String a7,
			String a8) {
		int j = 0;
		if (a1.matches("\\p{Upper}{2}\\d{4}")) {
			if (a2.matches("\\D+.*") && a3.matches("\\D+.*")) {
				if (tools.checkTime(a4) && tools.checkTime(a5) && tools.checkdate(a6)) {
					if (a7.matches("\\d+") && a8.matches("\\d+")) {
						if (tools.compareSystemTime(a6 + " " + a4, 2)
								&& tools.compareTime(a6 + " " + a5, a6 + " " + a4)) {
							j = 1;
						} else
							JOptionPane.showMessageDialog(null, "ȷ�����ʱ���ڵ�ǰʱ��2Сʱ֮�󣬵���ʱ�������ʱ��֮��");
					} else
						JOptionPane.showMessageDialog(null, "�۸�����������Ǹ�����");
				} else
					JOptionPane.showMessageDialog(null, "ĳ��ʱ�䲻�Ϸ�");
			} else
				JOptionPane.showMessageDialog(null, "���и�ʽ����");
		} else
			JOptionPane.showMessageDialog(null, "����Ÿ�ʽ����");
		return j == 1 ? true : false;
	}

}
