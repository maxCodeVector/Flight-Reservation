package bean;

public class FlightInfo {
    /**
     * FlightController 类，设置了航班的基本参数和时间判断方法
     */

    private String FlightID;

    public String getFlightID() {
        return FlightID;
    }

    public void setFlightID(String flightID) {
        FlightID = flightID;
    }

    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    private String arrivalTime;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    private String startCity;

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    private String arrivalCity;

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    private String departureDate;

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private int currentPassengers;

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    public void setCurrentPassengers(int currentPassengers) {
        this.currentPassengers = currentPassengers;
    }

    private String seatCapacity;

    public String getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(String seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    private State flightStatus;

    public State getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(State flightStatus) {
        this.flightStatus = flightStatus;
    }

    public FlightInfo() {
    }

    public FlightInfo(String FlightID,
                            String startCity, String arrivalCity,
                            String startTime, String arrivalTime, String departureDate,
                            String price, String seatCapacity,
                            State flightStatus, int currentPassengers) {
        this.setFlightID(FlightID);
        this.setStartCity(startCity);
        this.setArrivalCity(arrivalCity);
        this.setStartTime(startTime);
        this.setArrivalTime(arrivalTime);
        this.setDepartureDate(departureDate);
        this.setPrice(price);
        this.setSeatCapacity(seatCapacity);
        this.setCurrentPassengers(currentPassengers);
        this.setFlightStatus(flightStatus);
    }

    // 设置航班部分属性的get和set方法
    public String getExactTime() {
        return getDepartureDate() + " " + getStartTime();
    }


}