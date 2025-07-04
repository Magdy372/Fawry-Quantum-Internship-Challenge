
import java.util.ArrayList;
import java.util.List;



// Cart item
class CartItem {
    Product product;
    int quantity;
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

// Cart
public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive");
            return;
        }
        if (quantity > product.getQuantity()) {
            System.out.println("Not enough stock for " + product.getName());
        }
        for (CartItem item : items) {
            if (item.product == product) {
                if (item.quantity + quantity > product.getQuantity()) {
                    System.out.println("Not enough stock for " + product.getName());
                }
                item.quantity += quantity;
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() { return items; }
    public boolean isEmpty() { return items.isEmpty(); }
}
