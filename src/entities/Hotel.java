package entities;

public class Hotel {
    private String id;
    private String name;
    private String location;
    private double pricePerNight;
    private int capacity = 100; // default capacity, simple model

    public Hotel(String id, String name, String location, double pricePerNight) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.pricePerNight = pricePerNight;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public double getPricePerNight() { return pricePerNight; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return "%s | %s | %s | Rp%.0f /night".formatted(id, name, location, pricePerNight);
    }
}
