package entities;

public class Hotel {
    private final String id;
    private final String name;
    private final String location;
    private final double pricePerNight;
    private final int capacity = 100; // default capacity

    public Hotel(String id, String name, String location, double pricePerNight) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.pricePerNight = pricePerNight;
    }

    public String getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public String getLocation() { 
        return location; 
    }

    public double getPricePerNight() { 
        return pricePerNight; 
    }

    public int getCapacity() { 
        return capacity; 
    }

    @Override
    public String toString() {
        return String.format(
            "%s | %s | %s | Rp%.0f /night | Capacity: %d",
            id, name, location, pricePerNight, capacity
        );
    }
}
