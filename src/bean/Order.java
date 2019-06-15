package bean;

import controller.FlightController;

public class Order {
	String orderNum;
	String seatNum;
	State status;
	Passenger passenger;
	FlightInfo flight;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public State getStatus() {
		return status;
	}

	public void setStatus(State status) {
		this.status = status;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public FlightInfo getFlight() {
		return flight;
	}

	public void setFlight(FlightInfo flight) {
		this.flight = flight;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	String createDate;


	public Order(String orderNum, String seatNum, State status, Passenger pg, FlightInfo flight, String createDate) {
		this.orderNum = orderNum;
		this.seatNum = seatNum;
		this.status = status;
		this.passenger = pg;
		this.flight = flight;
		this.createDate = createDate;
	}
}