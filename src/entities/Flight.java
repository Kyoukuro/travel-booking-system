package entities;

public class Flight {
    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final String date;
    private int seats;
    private final double price;

    public Flight(String flightNumber, String origin, String destination, String date, int seats, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.seats = seats;
        this.price = price;
    }

    public String getFlightNumber() { return flightNumber; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getDate() { return date; }
    public int getSeats() { return seats; }
    public double getPrice() { return price; }

    public void setSeats(int seats) { this.seats = seats; }

    @Override
    public String toString() {
        return "%s | %s -> %s | %s | seats: %d | Rp%.0f".formatted(
                flightNumber, origin, destination, date, seats, price);
    }
}

