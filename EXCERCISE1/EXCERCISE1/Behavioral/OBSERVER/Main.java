import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(int price);
}

class StockExchange {
    private List<Observer> observers = new ArrayList<>();
    private int stockPrice;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setStockPrice(int price) {
        this.stockPrice = price;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }
}

class StockClient implements Observer {
    private String name;

    public StockClient(String name) {
        this.name = name;
    }

    @Override
    public void update(int price) {
        System.out.println(name + " received stock price update: " + price);
    }
}

// Usage
public class Main{
    public static void main(String[] args) {
        StockExchange exchange = new StockExchange();
        StockClient client1 = new StockClient("Client 1");
        StockClient client2 = new StockClient("Client 2");

        exchange.addObserver(client1);
        exchange.addObserver(client2);

        exchange.setStockPrice(100);
        exchange.setStockPrice(105);
    }
}
