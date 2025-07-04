import java.time.LocalDate;

// Main class with example usage
public class main {
    public static void main(String[] args) {

        Product cheese = new ExpirableandShippableProduct("Cheese", 100, 5, 0.2, LocalDate.now().plusDays(5));
        Product tv = new ShippableProduct("TV", 300, 3, 5.0); 
        Product scratchCard = new NormalProduct("Mobile scratch card", 50, 10);
        Product biscuits = new ExpirableProduct("Biscuits",12,10, LocalDate.now().plusDays(5));


        Customer customer = new Customer("Magdy", 1000);

        Cart cart = new Cart();
        cart.add(cheese, 1);
        cart.add(biscuits, 1);
        cart.add(tv, 1);
        cart.add(scratchCard, 1);

        CheckoutService.checkout(customer, cart);

        // Try edge cases
        System.out.println("\n-- Edge case: expired product --");
        Product expiredCheese = new ExpirableandShippableProduct("Cheese", 100, 5, 0.2, LocalDate.now().minusDays(1));
        Cart cart2 = new Cart();
        cart2.add(expiredCheese, 1);
        CheckoutService.checkout(customer, cart2);

        System.out.println("\n-- Edge case: insufficient balance --");
        Customer poorCustomer = new Customer("Mohamed", 10);
        Cart cart3 = new Cart();
        cart3.add(tv, 1);
        CheckoutService.checkout(poorCustomer, cart3);

        System.out.println("\n-- Edge case: out of stock --");
        Cart cart4 = new Cart();
        cart4.add(biscuits, 10); 
        CheckoutService.checkout(customer, cart4);

        System.out.println("\n-- Edge case: empty cart --");
        Cart cart5 = new Cart();
        CheckoutService.checkout(customer, cart5);
    }
}

