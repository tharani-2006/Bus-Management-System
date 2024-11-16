import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReservationManager {
    private final List<Reservation> reservations = new LinkedList<>();

    public void bookTicket(String bookingId, String busNumber, String passengerName, int noOfTickets) {
        Reservation reservation = new Reservation(bookingId, busNumber, passengerName, noOfTickets);
        reservations.add(reservation);
    }

    public boolean cancelTicket(String bookingId) {
        return reservations.removeIf(reservation -> reservation.getBookingId().equals(bookingId));
    }

    public List<Reservation> getAllReservations() {
        return new LinkedList<>(reservations);
    }
}
