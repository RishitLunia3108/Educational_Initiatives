
class DatabaseConnectionPool {
    private static DatabaseConnectionPool instance;
    private int connectionCount = 0;

    private DatabaseConnectionPool() {
        // private constructor
    }

    public static DatabaseConnectionPool getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionPool();
        }
        return instance;
    }

    public String getConnection() {
        connectionCount++;
        return "Connection " + connectionCount;
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        DatabaseConnectionPool pool1 = DatabaseConnectionPool.getInstance();
        DatabaseConnectionPool pool2 = DatabaseConnectionPool.getInstance();

        System.out.println(pool1 == pool2); // True (Singleton ensures one instance)
        System.out.println(pool1.getConnection()); // Connection 1
        System.out.println(pool2.getConnection()); // Connection 2
    }
}
