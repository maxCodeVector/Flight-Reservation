package controller;

import bean.FlightInfo;
import bean.Order;
import bean.State;
import util.tools;

import java.util.ArrayList;
import java.util.Collections;

public class FlightController {
	public static ArrayList<FlightInfo> flights = new ArrayList<>();

	// ���ú���ĵ�ǰԤ������
	public static void currentPgFlight(FlightInfo f) {
		int counter = 0;
		for (Order o : manageOrder.getOrders()) {
			if (o.getFlight().equals(f) && o.getStatus()!=State.CANCEL) {
				counter++;
			}
		}
		f.setCurrentPassengers(counter);
		if (f.getFlightStatus()==State.AVAILABLE
				|| f.getFlightStatus()==State.FULL) {
			if (counter >= Integer.parseInt(f.getSeatCapacity())) {
				f.setFlightStatus(State.FULL);
			} else {
				f.setFlightStatus(State.AVAILABLE);
			}
		}
	}

	// ʵ�ֲ�ѯĳһ�����ȫ������
	public static String superQF(FlightInfo f) {
		String flightInfo = String.format("�����:%-7s ��ǰԤ������: %-3s ��������:%-10s\n�˿��б�:\n",
				f.getFlightID(), f.getCurrentPassengers(),
				f.getDepartureDate());
		for (Order o : manageOrder.getOrders()) {
			if (o.getFlight().equals(f) && o.getStatus()!=State.CANCEL) {
				flightInfo = flightInfo + String.format("%-4s %-18s %-3s %-10s %-11s\n",
						o.getPassenger().getRealName(),
						o.getPassenger().getIdentityId(),
						o.getSeatNum(), o.getCreateDate(), o.getStatus());
			}
		}
		return flightInfo;
	}

	// ˢ�º�����Ϣ
	public static void flush() {
		for (FlightInfo f : flights) {
			currentPgFlight(f);
			if (!tools.compareSystemTime(f.getExactTime(), 2)) {
				f.setFlightStatus(State.TERMINATE);
			}

			if (!tools.compareSystemTime(f.getExactTime(), 48)) {
				f.setFlightStatus(State.AVAILABLE);
			}
		}
		for (int i = 0; i < flights.size(); i++) {
			if (!tools.compareSystemTime(flights.get(i).getExactTime(), -10)) {
				flights.remove(i);
				i--;
			}
		}
		Collections.sort(manageOrder.getOrders(), tools.orderComp);
		Collections.sort(flights, tools.flightComp);
		manageFlight.saveFlight();
	}

}
