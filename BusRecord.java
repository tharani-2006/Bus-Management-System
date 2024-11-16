public class BusRecord {
    private String busNumber;
    private String busName;
    private String source;
    private String destination;

    public BusRecord(String busNumber, String busName, String source, String destination) {
        this.busNumber = busNumber;
        this.busName = busName;
        this.source = source;
        this.destination = destination;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getBusName() {
        return busName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return busName + " (" + busNumber + ") - From: " + source + " To: " + destination;
    }
}
