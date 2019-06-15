package plane;

import java.util.Collections;

public class Flight {
/**
 Flight �࣬�����˺���Ļ���������ʱ���жϷ���
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

	// ����Flight������
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

	// ���ú��ಿ�����Ե�get��set����
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

	// ���ú���ĵ�ǰԤ������
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

	// ʵ�ֲ�ѯĳһ�����ȫ������
	public static String superQF(Flight f) {
		String flightInfo = String.format("�����:%-7s ��ǰԤ������: %-3s ��������:%-10s\n�˿��б�:\n", f.FlightID, f.currentPassengers,
				f.departureDate);
		for (Order o : manageOrder.getOrders()) {
			if (o.flight.equals(f) && !o.status.equals("CANCEL")) {
				flightInfo = flightInfo + String.format("%-4s %-18s %-3s %-10s %-11s\n", o.passenger.getRealName(),
						o.passenger.getIdentityId(), o.seatNum, o.createDate, o.status);
			}
		}
		return flightInfo;
	}

	// ˢ�º�����Ϣ
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
