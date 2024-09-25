package EXCERCISE.Behavioral.STRATEGY;
import java.util.ArrayList;
import java.util.List;

interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using PayPal.");
    }
}

class PaymentProcessor {
    private PaymentStrategy strategy;

    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(int amount) {
        strategy.pay(amount);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        PaymentProcessor payment = new PaymentProcessor(new CreditCardPayment());
        payment.processPayment(500);

        payment = new PaymentProcessor(new PayPalPayment());
        payment.processPayment(300);
    }
}
