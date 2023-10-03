import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Define the Busbook class
class Busbook {
    private int busno;
    private boolean ac;
    private int capacity;

    // Constructor
    public Busbook(int no, boolean ac, int cap) {
        this.busno = no;
        this.ac = ac;
        this.capacity = cap;
    }

    // Getter methods
    public int getbusno() {
        return busno;
    }

    public boolean getac() {
        return ac;
    }

    public int getcap() {
        return capacity;
    }

    // Setter methods
    public void setac(boolean val) {
        ac = val;
    }

    public void setcap(int cap) {
        capacity = cap;
    }

    // Display method
    public void displayBusbook() {
        System.out.println("Bus.No: " + busno + " Ac: " + ac + " Total capacity: " + capacity);
    }
}



// Define the Booking class
class Booking {
    private String passengername;
    private int busno;
    private Date date;

    public Booking() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of Passenger:");
        passengername = sc.next();
        System.out.println("Enter bus no:");
        busno = sc.nextInt();
        System.out.print("Enter date (dd-mm-yyyy): ");
        String dateinput = sc.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            date = dateFormat.parse(dateinput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Check availability of booking
    public boolean isAvailable(ArrayList<Booking> bookings, ArrayList<Busbook> buss) {
        int capacity = 0;

        for (Busbook bus : buss) {
            if (bus.getbusno() == busno) {
                capacity = bus.getcap();
            }
        }

        int booked = 0;
        for (Booking b : bookings) {
            if (b.busno == busno && b.date.equals(date)) {
                booked++;
            }
        }

        return booked < capacity;
    }
}

// Define the Bus class
public class Bus {
    public static void main(String[] args) {
        ArrayList<Busbook> buss = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        // Populate initial bus information
        buss.add(new Busbook(1, true, 2));
        buss.add(new Busbook(2, false, 10));
        buss.add(new Busbook(3, true, 5));

        int usropt = 1;
        Scanner sc = new Scanner(System.in);

        // Display initial bus information
        for (Busbook b : buss) {
            b.displayBusbook();
        }

        while (usropt == 1) {
            System.out.println("Enter 1 to book or 2 to exit");
            usropt = sc.nextInt();

            if (usropt == 1) {
                Booking booking = new Booking();
                if (booking.isAvailable(bookings, buss)) {
                    bookings.add(booking);
                    System.out.println("Your booking is confirmed");
                } else {
                    System.out.println("Sorry, Bus is full. Please try another bus or date.");
                }
            }
        }
    }
}
