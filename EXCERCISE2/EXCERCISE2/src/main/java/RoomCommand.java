import java.util.HashMap;
import java.util.Map;

public class RoomCommand {
    private Map<Integer, Room> roomMap;  // Maps room numbers to Room objects

    public RoomCommand(Room... rooms) {
        roomMap = new HashMap<>();
        for (Room room : rooms) {
            roomMap.put(room.getRoomNumber(), room);
        }
    }

    // Method to book a room
    public boolean bookRoom(int roomNumber, String startTime, int duration) {
        Room room = roomMap.get(roomNumber);
        if (room == null) {
            System.out.println("Invalid room number. Please try again.");
            return false;
        }
        if (!room.isOccupied()) {  // Check if the room is not occupied
            room.bookRoom(startTime, duration);  // Call the bookRoom method
            return true;
        } else {
            System.out.println("Room is already occupied or booked.");
            return false;
        }
    }

    // Method to cancel a room booking
    public boolean cancelBooking(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        if (room == null) {
            System.out.println("Invalid room number. Please try again.");
            return false;
        }
        if (room.isOccupied()) {  // Check if the room is occupied
            room.cancelBooking();  // Call the cancelBooking method
            return true;
        } else {
            System.out.println("Room is not currently booked.");
            return false;
        }
    }
}
