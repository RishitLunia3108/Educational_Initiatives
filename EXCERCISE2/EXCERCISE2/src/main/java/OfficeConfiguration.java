public class OfficeConfiguration {
    private static OfficeConfiguration instance;
    private Room[] rooms;
    private CsvDatabase database;

    private OfficeConfiguration() {}

    public static OfficeConfiguration getInstance() {
        if (instance == null) {
            instance = new OfficeConfiguration();
        }
        return instance;
    }

    public void configureRooms(int numRooms, CsvDatabase database) {
        this.database = database;
        rooms = new Room[numRooms];
        for (int i = 0; i < numRooms; i++) {
            rooms[i] = new Room("Room " + (i + 1), i + 1, database);
        }
        System.out.println("Office configured with " + numRooms + " meeting rooms.");
    }

    public Room getRoom(int roomNumber) {
        if (roomNumber >= 1 && roomNumber <= rooms.length) {
            return rooms[roomNumber - 1];
        } else {
            System.out.println("Room " + roomNumber + " does not exist.");
            return null;
        }
    }
}
