
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// CheckoutService
class CheckoutService {
    private static final double SHIPPING_RATE_PER_KG = 30.0;

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty");
            return;
        }
        double subtotal = 0;
        double shippingWeight = 0;
        List<Shippable> shippables = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product p = item.product;
            if (item.quantity > p.getQuantity()) {
                System.out.println("Error: Not enough stock for " + p.getName());
                return;
            }
            // Check for expirable
            if (p instanceof Expirable) {
                LocalDate expiry = ((Expirable)p).getExpiryDate();
                if (expiry != null && expiry.isBefore(LocalDate.now())) {
                    System.out.println("Error: Product " + p.getName() + " is expired");
                    return;
                }
            }
            subtotal += p.getPrice() * item.quantity;
            // Check for shippable
            if (p instanceof Shippable) {
                for (int i = 0; i < item.quantity; i++) {
                    shippables.add((Shippable)p);
                    shippingWeight += ((Shippable)p).getWeight();
                }
            }
        }
        double shippingFee = shippingWeight * SHIPPING_RATE_PER_KG;
        double total = subtotal + shippingFee;
        if (customer.getBalance() < total) {
            System.out.println("Error: Customer's balance is insufficient.");
            return;
        }
        // Ship items
        ShippingService.ship(shippables);
        // Print receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.quantity + "x " + item.product.getName() + " " + (int)item.product.getPrice()*item.quantity);
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFee);
        System.out.println("Amount " + total);
        // Deduct balance and reduce product quantities
        customer.deduct(total);
        for (CartItem item : cart.getItems()) {
            item.product.reduceQuantity(item.quantity);
        }
        System.out.println("Customer balance after payment: " + customer.getBalance());
    }
}
