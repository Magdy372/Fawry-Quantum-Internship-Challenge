
import java.time.LocalDate;

// Expirable and shippable product (e.g., Cheese, Biscuits)
public class ExpirableandShippableProduct extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weight;

    public ExpirableandShippableProduct(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() { return expiryDate; }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public double getWeight() { return weight; }
}