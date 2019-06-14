package plane;

import java.util.Collections;

public class Flight {
/**
 Flight 类，设置了航班的基本参数和时间判断方法
*/
	String FlightID;
	String startTime;
	String arrivalTime;
	String startCity;
	String arrivalCity;
	String departureDate;
	String price;
	int currentPassengers;
	String seatCapacity;
	String flightStatus;

	// 设置Flight构造器
	public Flight(String FlightID, String startCity, String arrivalCity, String startTime, String arrivalTime,
			String departureDate, String price, String seatCapacity, String flightStatus, int currentPassengers) {
		this.FlightID = FlightID;
		this.startCity = startCity;
		this.arrivalCity = arrivalCity;
		this.startTime = startTime;
		this.arrivalTime = arrivalTime;
		this.departureDate = departureDate;
		this.price = price;
		this.seatCapacity = seatCapacity;
		this.currentPassengers = currentPassengers;
		this.flightStatus = flightStatus;
	}

	public Flight() {
	}

	// 设置航班部分属性的get和set方法
	public String getExactTime() {
		return departureDate + " " + startTime;
	}

	public void setFlightStatus(State flightStatus) {
		this.flightStatus = flightStatus.getState();
	}

	public int getCurrentPassengers() {
		return currentPassengers;
	}

	public void setCurrentPassengers(int currentPassengers) {
		this.currentPassengers = currentPassengers;
	}

	// 设置航班的当前预订人数
	public static void currentPgFlight(Flight f) {
		int counter = 0;
		for (Order o : manageOrder.getOrders()) {
			if (o.flight.equals(f) && !o.status.equals(State.CANCEL.getState())) {
				counter++;
			}
		}
		f.setCurrentPassengers(counter);
		if (f.flightStatus.equals(State.AVAILABLE.getState()) || f.flightStatus.equals(State.FULL.getState())) {
			if (counter >= Integer.parseInt(f.seatCapacity)) {
				f.setFlightStatus(State.FULL);
			} else {
				f.setFlightStatus(State.AVAILABLE);
			}
		}
	}

	// 实现查询某一航班的全部订单
	public static String superQF(Flight f) {
		String flightInfo = String.format("航班号:%-7s 当前预订人数: %-3s 出发日期:%-10s\n乘客列表:\n", f.FlightID, f.currentPassengers,
				f.departureDate);
		for (Order o : manageOrder.getOrders()) {
			if (o.flight.equals(f) && !o.status.equals("CANCEL")) {
				flightInfo = flightInfo + String.format("%-4s %-18s %-3s %-10s %-11s\n", o.passenger.getRealName(),
						o.passenger.getIdentityId(), o.seatNum, o.createDate, o.status);
			}
		}
		return flightInfo;
	}

	// 刷新航班信息
	public static void flush() {
		for (Flight f : manageFlight.flights) {
			currentPgFlight(f);
			if (!tools.compareSystemTime(f.getExactTime(), 2)) {
				f.setFlightStatus(State.TERMINATE);
			}

			if (!tools.compareSystemTime(f.getExactTime(), 48)) {
				f.setFlightStatus(State.AVAILABLE);
			}
		}
		for (int i = 0; i < manageFlight.flights.size(); i++) {
			if (!tools.compareSystemTime(manageFlight.flights.get(i).getExactTime(), -10)) {
				manageFlight.flights.remove(i);
				i--;
			}
		}
		Collections.sort(manageOrder.getOrders(), new orderComaprtor());
		Collections.sort(manageFlight.flights, new flightComaprtor());
		manageFlight.saveFlight();
	}

}
