
import java.time.LocalDate;

// Expirable product (e.g., Biscuits)
public class ExpirableProduct extends Product implements Expirable {
    private LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() { return expiryDate; }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }
}