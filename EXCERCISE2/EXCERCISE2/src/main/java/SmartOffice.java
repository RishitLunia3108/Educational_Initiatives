import java.util.Scanner;

public class SmartOffice {
    public static void main(String[] args) {
        // Initialize the CSV database
        CsvDatabase database = new CsvDatabase("F:/educational/EXCERCISE2/src/main/resources/room.csv");

        // Create room instances and add observers
        Room room1 = new Room("Room 1", 1, database);
        Room room2 = new Room("Room 2", 2, database);
        Room room3 = new Room("Room 3", 3, database);

        // Create observers (Air Conditioner and Lights)
        AirConditioner ac = new AirConditioner();
        Lights lights = new Lights();

        // Register observers to rooms
        room1.addObserver(ac);
        room1.addObserver(lights);
        room2.addObserver(ac);
        room2.addObserver(lights);
        room3.addObserver(ac);
        room3.addObserver(lights);

        // Use Scanner to accept user input for managing rooms
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Smart Office Facility Management ---");
            System.out.println("1. Configure Room Capacity");
            System.out.println("2. Add Occupants to Room");
            System.out.println("3. Book a Room");
            System.out.println("4. Cancel Room Booking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    configureRoomCapacity(scanner, room1, room2, room3);
                    break;
                case 2:
                    addOccupants(scanner, room1, room2, room3);
                    break;
                case 3:
                    bookRoom(scanner, room1, room2, room3);
                    break;
                case 4:
                    cancelBooking(scanner, room1, room2, room3);
                    break;
                case 5:
                    System.out.println("Exiting the Smart Office system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void configureRoomCapacity(Scanner scanner, Room room1, Room room2, Room room3) {
        System.out.print("Enter room number (1-3) to configure capacity: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter maximum capacity for Room " + roomNumber + ": ");
        int capacity = scanner.nextInt();

        switch (roomNumber) {
            case 1:
                room1.setMaxCapacity(capacity);
                break;
            case 2:
                room2.setMaxCapacity(capacity);
                break;
            case 3:
                room3.setMaxCapacity(capacity);
                break;
            default:
                System.out.println("Invalid room number.");
        }
    }

    private static void addOccupants(Scanner scanner, Room room1, Room room2, Room room3) {
        System.out.print("Enter room number (1-3) to add occupants: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter the number of occupants: ");
        int occupants = scanner.nextInt();

        switch (roomNumber) {
            case 1:
                room1.setOccupants(occupants);
                break;
            case 2:
                room2.setOccupants(occupants);
                break;
            case 3:
                room3.setOccupants(occupants);
                break;
            default:
                System.out.println("Invalid room number.");
        }
    }

    private static void bookRoom(Scanner scanner, Room room1, Room room2, Room room3) {
        System.out.print("Enter room number (1-3) to book: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter booking start time (HH:MM): ");
        String startTime = scanner.next();
        System.out.print("Enter booking duration in minutes: ");
        int duration = scanner.nextInt();

        RoomCommand bookingCommand = new RoomCommand(room1, room2, room3);
        boolean success = bookingCommand.bookRoom(roomNumber, startTime, duration);
        if (success) {
            System.out.println(
                    "Room " + roomNumber + " booked successfully from " + startTime + " for " + duration + " minutes.");
        } else {
            System.out.println("Room " + roomNumber + " is already booked or invalid.");
        }
    }

    private static void cancelBooking(Scanner scanner, Room room1, Room room2, Room room3) {
        System.out.print("Enter room number (1-3) to cancel booking: ");
        int roomNumber = scanner.nextInt();

        RoomCommand bookingCommand = new RoomCommand(room1, room2, room3);
        boolean success = bookingCommand.cancelBooking(roomNumber);
        if (success) {
            System.out.println("Booking for Room " + roomNumber + " cancelled successfully.");
        } else {
            System.out.println("Room " + roomNumber + " was not booked.");
        }
    }
}
