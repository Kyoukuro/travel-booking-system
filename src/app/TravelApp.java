package app;

import entities.Flight;
import entities.Hotel;
import reservations.Reservation;
import reservations.FlightReservation;
import reservations.HotelReservation;
import exceptions.ReservationNotFoundException;
import utils.ConfirmationGenerator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TravelApp {
    private final ArrayList<Flight> flights = new ArrayList<>();
    private final ArrayList<Hotel> hotels = new ArrayList<>();
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    public TravelApp() {
        seedData();
    }

    private void seedData() {
        // sample flights
        flights.add(new Flight("GA101", "Jakarta", "Bali", "2025-12-01", 30, 1200000));
        flights.add(new Flight("JT505", "Jakarta", "Surabaya", "2025-12-01", 25, 850000));
        flights.add(new Flight("SQ303", "Jakarta", "Singapore", "2025-12-05", 50, 2200000));
        flights.add(new Flight("QZ888", "Surabaya", "Bali", "2025-12-03", 20, 600000));

        // sample hotels
        hotels.add(new Hotel("H001", "Grand Bali Resort", "Bali", 700000));
        hotels.add(new Hotel("H002", "Surabaya Inn", "Surabaya", 400000));
        hotels.add(new Hotel("H003", "Jakarta Central Hotel", "Jakarta", 550000));
    }

    public void run() {
        while (true) {
            System.out.println("\n=== Travel Booking System ===");
            System.out.println("1. Cari Penerbangan");
            System.out.println("2. Cari Hotel");
            System.out.println("3. Pesan Penerbangan");
            System.out.println("4. Pesan Hotel");
            System.out.println("5. Batalkan Reservasi");
            System.out.println("6. Lihat Semua Reservasi");
            System.out.println("7. Keluar");
            System.out.print("Pilih (1-7): ");

            int choice = safeNextInt();
            switch (choice) {
                case 1 -> searchFlightsFlow();
                case 2 -> searchHotelsFlow();
                case 3 -> bookFlightFlow();
                case 4 -> bookHotelFlow();
                case 5 -> cancelReservationFlow();
                case 6 -> listReservations();
                case 7 -> {
                    System.out.println("Terima kasih! Sampai jumpa.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private int safeNextInt() {
        while (true) {
            try {
                int v = scanner.nextInt();
                scanner.nextLine();
                return v;
            } catch (InputMismatchException ex) {
                System.out.print("Input harus angka. Coba lagi: ");
                scanner.nextLine();
            }
        }
    }

    private void searchFlightsFlow() {
        System.out.println("\n--- Cari Penerbangan ---");
        System.out.print("Asal: ");
        String origin = scanner.nextLine().trim();
        System.out.print("Tujuan: ");
        String destination = scanner.nextLine().trim();
        System.out.print("Tanggal (YYYY-MM-DD) [opsional, tekan Enter lewati]: ");
        String date = scanner.nextLine().trim();
        System.out.print("Jumlah penumpang: ");
        int pax = safeNextInt();

        List<Flight> results = flights.stream()
                .filter(f -> f.getOrigin().equalsIgnoreCase(origin))
                .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                .filter(f -> date.isEmpty() || f.getDate().equals(date))
                .filter(f -> f.getSeats() >= pax)
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("Tidak ada penerbangan tersedia.");
            return;
        }

        System.out.println("Hasil pencarian:");
        results.forEach(System.out::println);
    }

    private void searchHotelsFlow() {
        System.out.println("\n--- Cari Hotel ---");
        System.out.print("Lokasi (kota): ");
        String loc = scanner.nextLine().trim();
        System.out.print("Tanggal check-in (YYYY-MM-DD) [opsional]: ");
        String checkIn = scanner.nextLine().trim();
        System.out.print("Tanggal check-out (YYYY-MM-DD) [opsional]: ");
        String checkOut = scanner.nextLine().trim();
        System.out.print("Jumlah tamu: ");
        int guests = safeNextInt();

        List<Hotel> results = hotels.stream()
                .filter(h -> h.getLocation().equalsIgnoreCase(loc))
                .filter(h -> h.getCapacity() >= guests)
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("Tidak ada hotel tersedia pada lokasi tersebut.");
            return;
        }

        System.out.println("Hasil pencarian:");
        results.forEach(System.out::println);
    }

    private void bookFlightFlow() {
        System.out.println("\n--- Pesan Penerbangan ---");
        System.out.print("Masukkan nomor penerbangan (contoh GA101): ");
        String flightNo = scanner.nextLine().trim();

        Flight flight = flights.stream()
                .filter(f -> f.getFlightNumber().equalsIgnoreCase(flightNo))
                .findFirst()
                .orElse(null);

        if (flight == null) {
            System.out.println("Nomor penerbangan tidak ditemukan.");
            return;
        }

        System.out.print("Jumlah penumpang: ");
        int pax = safeNextInt();

        if (pax <= 0) {
            System.out.println("Jumlah penumpang harus > 0.");
            return;
        }

        if (flight.getSeats() < pax) {
            System.out.println("Maaf, kursi tidak mencukupi. Tersisa: " + flight.getSeats());
            return;
        }

        System.out.print("Nama pemesan: ");
        String name = scanner.nextLine().trim();
        System.out.print("Kontak: ");
        String contact = scanner.nextLine().trim();

        int conf = ConfirmationGenerator.generate();
        FlightReservation fr = new FlightReservation(conf, flight, name, contact, pax);
        reservations.add(fr);

        // update seats
        flight.setSeats(flight.getSeats() - pax);

        System.out.println("Pemesanan berhasil! Nomor konfirmasi: " + conf);
        fr.display();
    }

    private void bookHotelFlow() {
        System.out.println("\n--- Pesan Hotel ---");
        System.out.print("Masukkan ID hotel (contoh H001): ");
        String hotelId = scanner.nextLine().trim();

        Hotel hotel = hotels.stream()
                .filter(h -> h.getId().equalsIgnoreCase(hotelId))
                .findFirst()
                .orElse(null);

        if (hotel == null) {
            System.out.println("ID hotel tidak ditemukan.");
            return;
        }

        System.out.print("Tanggal check-in (YYYY-MM-DD): ");
        String checkIn = scanner.nextLine().trim();
        System.out.print("Tanggal check-out (YYYY-MM-DD): ");
        String checkOut = scanner.nextLine().trim();
        System.out.print("Jumlah tamu: ");
        int guests = safeNextInt();

        if (guests <= 0) {
            System.out.println("Jumlah tamu harus > 0.");
            return;
        }

        int conf = ConfirmationGenerator.generate();
        HotelReservation hr = new HotelReservation(conf, hotel, checkIn, checkOut, guests);
        reservations.add(hr);

        System.out.println("Pemesanan berhasil! Nomor konfirmasi: " + conf);
        hr.display();
    }

    private void cancelReservationFlow() {
        System.out.println("\n--- Batalkan Reservasi ---");
        System.out.print("Masukkan nomor konfirmasi: ");
        int conf = safeNextInt();

        try {
            cancelReservation(conf);
            System.out.println("Pembatalan berhasil untuk konfirmasi " + conf);
        } catch (ReservationNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void cancelReservation(int conf) throws ReservationNotFoundException {
        // find reservation by confirmation number
        Reservation found = reservations.stream()
                .filter(r -> r.getConfirmationNumber() == conf)
                .findFirst()
                .orElse(null);

        if (found == null) throw new ReservationNotFoundException("Reservasi dengan nomor " + conf + " tidak ditemukan.");

        // pattern matching for instanceof (Java 16+)
        if (found instanceof FlightReservation fr) {
            // restore seats
            Flight f = fr.getFlight();
            f.setSeats(f.getSeats() + fr.getPassengerCount());
            System.out.println("Membatalkan FlightReservation: mengembalikan " + fr.getPassengerCount() + " kursi ke penerbangan " + f.getFlightNumber());
        } else if (found instanceof HotelReservation hr) {
            System.out.println("Membatalkan HotelReservation: " + hr.getHotel().getName());
            // no extra action for hotel in this simple model
        }

        reservations.remove(found);
    }

    private void listReservations() {
        System.out.println("\n--- Semua Reservasi ---");
        if (reservations.isEmpty()) {
            System.out.println("Belum ada reservasi.");
            return;
        }
        reservations.forEach(Reservation::display);
    }
}
