package reservations;

import entities.Flight;

public final class FlightReservation extends Reservation {
    private final Flight flight;
    private final String passengerName;
    private final String contact;
    private final int passengerCount;

    public FlightReservation(int confirmationNumber, Flight flight, String passengerName, String contact, int passengerCount) {
        super(confirmationNumber);
        this.flight = flight;
        this.passengerName = passengerName;
        this.contact = contact;
        this.passengerCount = passengerCount;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    @Override
    public void display() {
        System.out.println("=== Flight Reservation ===");
        System.out.println("Confirm: " + confirmationNumber);
        System.out.println("Passenger: " + passengerName + " (" + contact + ")");
        System.out.println("Flight: " + flight);
        System.out.println("Passengers: " + passengerCount);
    }
}
