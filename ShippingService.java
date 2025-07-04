
import java.util.List;

// ShippingService
class ShippingService {
    public static void ship(List<Shippable> items) {
        if (items.isEmpty()) return;
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (Shippable item : items) {
            System.out.println(item.getName() + " " + (int)(item.getWeight() * 1000) + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight " + totalWeight + "kg");
    }
}