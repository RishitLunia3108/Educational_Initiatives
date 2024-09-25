public class Lights implements Observer {
    @Override
    public void update(boolean isOccupied) {
        if (isOccupied) {
            System.out.println("Lights are now ON.");
        } else {
            System.out.println("Lights are now OFF.");
        }
    }
}
