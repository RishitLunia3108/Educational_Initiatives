import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Room {
    private String name;
    private boolean occupied;
    private int occupants;
    private int maxCapacity;
    private CsvDatabase database;
    private int roomNumber; // Room number used for CSV lookup
    private List<Observer> observers;

    public Room(String name, int roomNumber, CsvDatabase database) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.database = database;
        this.observers = new ArrayList<>();
        loadRoomData();
    }

    private void loadRoomData() {
        List<String[]> records = database.readCsv();

        // Check if roomNumber is valid
        if (roomNumber < 1 || roomNumber > records.size()) {
            throw new IllegalArgumentException("Invalid room number: " + roomNumber);
        }

        String[] roomData = records.get(roomNumber - 1);

        try {
            this.maxCapacity = Integer.parseInt(roomData[1].trim()); // Trim to remove any spaces
            this.occupied = Boolean.parseBoolean(roomData[2].trim());
            this.occupants = Integer.parseInt(roomData[3].trim());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing room data: " + e.getMessage());
            // Handle the error appropriately (e.g., set default values or rethrow)
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Room data is incomplete: " + Arrays.toString(roomData));
            // Handle the error appropriately
        }
    }

    // Save room data to CSV
    private void saveRoomData() {
        String[] updatedData = {
                String.valueOf(roomNumber),
                String.valueOf(maxCapacity),
                String.valueOf(occupied),
                String.valueOf(occupants),
                "", "" // No booking time and duration for now
        };
        database.updateRoom(roomNumber, updatedData);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void setOccupants(int occupants) {
        this.occupants = occupants;
        if (occupants >= 2) {
            setOccupied(true);
        } else {
            setOccupied(false);
        }
        saveRoomData();
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity > 0) {
            this.maxCapacity = maxCapacity;
            System.out.println(this.name + " maximum capacity set to " + maxCapacity);
            saveRoomData();
        } else {
            System.out.println("Invalid capacity. Please enter a valid positive number.");
        }
    }

    private void setOccupied(boolean status) {
        this.occupied = status;
        if (status) {
            System.out.println(
                    this.name + " is now occupied by " + this.occupants + " persons. AC and lights turned on.");
        } else {
            System.out.println(this.name + " is now unoccupied. AC and lights turned off.");
        }
        notifyObservers();
        saveRoomData();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(occupied);
        }
    }

    public boolean isOccupied() {
        return occupied;
    }

    public String getName() {
        return name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void bookRoom(String startTime, int duration) {
        System.out.println(this.name + " booked from " + startTime + " for " + duration + " minutes.");
        this.occupied = true; // Mark the room as occupied
        saveRoomData(); // Save state to CSV or database
    }

    public void cancelBooking() {
        System.out.println(this.name + " booking canceled.");
        this.occupied = false; // Mark the room as unoccupied
        saveRoomData(); // Save state to CSV or database
    }
}
