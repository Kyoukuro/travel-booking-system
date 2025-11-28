Travel Booking System – Java Console App

Aplikasi console sederhana untuk melakukan pemesanan perjalanan.
Fitur mencakup pencarian penerbangan, pencarian hotel, pemesanan, pembatalan, dan melihat daftar reservasi.

Cara Menjalankan
1. Pastikan JDK sudah terinstall

Cek:
javac -version

2. Jalankan script otomatis (Windows PowerShell)
./run.ps1

Script ini akan:
meng-compile semua file .java
menyimpan hasil compile ke folder out/
menjalankan aplikasi secara otomatis


Struktur Project
src/
│
├── Main.java
├── app/
│   └── TravelApp.java
│
├── entities/
│   ├── Flight.java
│   ├── Hotel.java
│
├── reservations/
│   ├── Reservation.java
│   ├── FlightReservation.java
│   ├── HotelReservation.java
│
├── utils/
│   ├── FlightLoader.java
│   └── HotelLoader.java
│
└── exceptions/
    └── ReservationNotFoundException.java

Fitur Utama
Cari Penerbangan berdasarkan destinasi, tanggal, atau semua penerbangan
Cari Hotel berdasarkan lokasi atau semua hotel
Pesan Penerbangan (dengan pengurangan kursi otomatis)
Pesan Hotel
Batalkan Reservasi (kursi kembali ditambah untuk flight)
Lihat Semua Reservasi
Input aman (menggunakan safeNextInt untuk mencegah error input)

Teknologi
Java (tanpa framework)
OOP (class: Flight, Hotel, Reservation, dll.)
Exception handling
Clean console UI

Author
Matthew Jonathan Syauta