package reservations;

public sealed abstract class Reservation permits FlightReservation, HotelReservation {
    protected final int confirmationNumber;

    protected Reservation(int confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    public abstract void display();
}
