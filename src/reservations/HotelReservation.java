package reservations;

import entities.Hotel;

public final class HotelReservation extends Reservation {
    private final Hotel hotel;
    private final String checkIn;
    private final String checkOut;
    private final int guests;

    public HotelReservation(int confirmationNumber, Hotel hotel, String checkIn, String checkOut, int guests) {
        super(confirmationNumber);
        this.hotel = hotel;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guests = guests;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public void display() {
        System.out.println("=== Hotel Reservation ===");
        System.out.println("Confirm: " + confirmationNumber);
        System.out.println("Hotel: " + hotel);
        System.out.println("Check-in: " + checkIn + " | Check-out: " + checkOut);
        System.out.println("Guests: " + guests);
    }
}
