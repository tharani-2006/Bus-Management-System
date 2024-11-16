import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Reservation {
    private String bookingId;
    private String busNumber;
    private String passengerName;
    private int noOfTickets;

    public Reservation(String bookingId, String busNumber, String passengerName, int noOfTickets) {
        this.bookingId = bookingId;
        this.busNumber = busNumber;
        this.passengerName = passengerName;
        this.noOfTickets = noOfTickets;
    }

    public String getBookingId() {
        return bookingId;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Bus Number: " + busNumber + ", Passenger: " +
                passengerName + ", Tickets: " + noOfTickets;
    }
}
