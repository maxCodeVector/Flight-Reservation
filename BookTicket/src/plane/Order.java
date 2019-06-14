package plane;

public class Order {
	String orderNum;
	String seatNum;
	String status;
	Passenger passenger;
	Flight flight;
	String createDate;

	public String getStatus() {
		return status;
	}

	public void setStatus(State status) {
		this.status = status.getState();
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Order() {
	}

	public Order(String orderNum, String seatNum, String status, Passenger pg, Flight flight, String createDate) {
		this.orderNum = orderNum;
		this.seatNum = seatNum;
		this.status = status;
		this.passenger = pg;
		this.flight = flight;
		this.createDate = createDate;
	}
}