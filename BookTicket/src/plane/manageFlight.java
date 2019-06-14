package plane;
/**
 * Flight 的管理类，设置了航班应有的功能
 * 比如添加航班，修改航班。
 */

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class manageFlight {
	// 创建航班的数组flights
	static ArrayList<Flight> flights = new ArrayList<>();

	// 设置添加航班功能
	public static boolean creatFlight(String a1, String a2, String a3, String a4, String a5, String a6, String a7,
			String a8) {
		boolean j = checkFlight(a1, a2, a3, a4, a5, a6, a7, a8);
		FileWriter fw;
		try {
			fw = new FileWriter("Flight.dat", true);
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
				String temp = String.format("添加成功\n%-7s%-3S%-3S%-6s%-6s%-10s\n", flight.FlightID, flight.startCity,
						flight.arrivalCity, flight.startTime, flight.arrivalTime, flight.departureDate, flight.price,
						flight.seatCapacity);
				JOptionPane.showMessageDialog(null, temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return j;
	}

	// 将航班数据从文件导入到数组flights
	public static void initial() {
		FileReader fw;
		try {
			fw = new FileReader("Flight.dat");
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

	// 设置更新修改后航班资料的功能
	public static void saveFlight() {
		FileWriter fw;
		try {
			fw = new FileWriter("Flight.dat");
			fw.write("");
			fw.close();
			fw = new FileWriter("Flight.dat", true);
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

	// 检查填写的航班信息是否符合格式和时间是否符合要求
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
							JOptionPane.showMessageDialog(null, "确保起飞时间在当前时间2小时之后，到达时间在起飞时间之后");
					} else
						JOptionPane.showMessageDialog(null, "价格和容量必须是个数字");
				} else
					JOptionPane.showMessageDialog(null, "某个时间不合法");
			} else
				JOptionPane.showMessageDialog(null, "城市格式错误");
		} else
			JOptionPane.showMessageDialog(null, "航班号格式错误");
		return j == 1 ? true : false;
	}

}
