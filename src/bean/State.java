package bean;

public enum State {
    UNPUBLISHED("UNPUBLISHED"), AVAILABLE("AVAILABLE"), FULL("FULL"), TERMINATE("TERMINATE"), UNPAID("UNPAID"), PAID(
            "PAID"), CANCEL("CANCEL");
    private String state;

    private State(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
