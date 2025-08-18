package Railway_Reservation_System;

import java.io.*;
import java.util.*;

public class ReservationSystem {
    private static final int MAX_SEATS = 3; // Example: Max seats for a route
    private static final String BOOKING_FILE = "bookings.txt";
    private List<Passenger> confirmed = new ArrayList<>();
    private List<Passenger> waitlist = new ArrayList<>();

    public ReservationSystem() {
        loadBookings();
    }

    public void bookTicket(Passenger p) {
        if (confirmed.size() < MAX_SEATS) {
            confirmed.add(p);
            System.out.println("Ticket Confirmed for " + p.getName());
        } else {
            waitlist.add(p);
            System.out.println("Added to Waitlist for " + p.getName());
        }
        saveBookings();
    }

    private void loadBookings() {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKING_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                Passenger p = new Passenger(info[0], Integer.parseInt(info[1]), info[2]);
                if (info[3].equals("CONFIRMED")) confirmed.add(p);
                else waitlist.add(p);
            }
        } catch (IOException e) {
            // no file yet, first run
        }
    }

    private void saveBookings() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKING_FILE))) {
            for (Passenger p : confirmed)
                bw.write(p.getName() + "," + p.getAge() + "," + p.getRoute() + ",CONFIRMED\n");
            for (Passenger p : waitlist)
                bw.write(p.getName() + "," + p.getAge() + "," + p.getRoute() + ",WAITLIST\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // For demonstration: main method
    public static void main(String[] args) {
        ReservationSystem rs = new ReservationSystem();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter name:");
        String name = sc.nextLine();
        System.out.println("Enter age:");
        int age = sc.nextInt(); sc.nextLine();
        System.out.println("Enter route:");
        String route = sc.nextLine();

        Passenger p = new Passenger(name, age, route);
        rs.bookTicket(p);
    }
}
