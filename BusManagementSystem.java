import java.util.ArrayList;
import java.util.List;

public class BusManagementSystem {
    private List<BusRecord> buses;
    private List<String> reservations;

    public BusManagementSystem() {
        buses = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public List<BusRecord> getBuses() {
        return buses;
    }

    public void addBus(BusRecord bus) {
        buses.add(bus);
    }

    public void removeBus(String busNumber) {
        buses.removeIf(bus -> bus.getBusNumber().equals(busNumber));
    }

    public void addReservation(String reservation) {
        reservations.add(reservation);
    }

    public List<String> getReservations() {
        return reservations;
    }
}
