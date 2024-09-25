public class AirConditioner implements Observer {
    @Override
    public void update(boolean isOccupied) {
        if (isOccupied) {
            System.out.println("Air Conditioner is now ON.");
        } else {
            System.out.println("Air Conditioner is now OFF.");
        }
    }
}
