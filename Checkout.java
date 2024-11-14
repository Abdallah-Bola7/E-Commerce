import java.util.ArrayList;
import java.util.List;

class Checkout {
    private static final double SHIPPING_FEE_PER_KG = 30;

    public static void checkout(Customer customer, ShoppingCart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot checkout.");
        }

        double subtotal = 0.0;
        double shippingFees = 0.0;
        double totalWeight = 0;

        System.out.println("** Shipment notice **");
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.isExpired()) {
                subtotal += product.getPrice() * item.getQuantity();
                shippingFees = SHIPPING_FEE_PER_KG;
                if (product.requiredShipping()) {
                    System.out.println(item.getQuantity() + "x  " + product.getName() + "        " + product.getWeight() + "g");
                    totalWeight += product.getWeight();
                }
            }
        }
        String kg = (totalWeight > 1000) ? " kg" : " g";
        System.out.println("Total package weight " + totalWeight + kg);
        System.out.println();
        double paidAmount = subtotal + shippingFees;

        if (customer.getBalance() < paidAmount) {
            throw new IllegalStateException("Insufficient balance for this purchase.");
        }
        System.out.println("** Checkout receipt **");
        for
        (CartItem item : cart.getItems()) {
            if (item.getProduct().isExpired())
                System.out.println(item.getQuantity() + "x  " + item.getProduct().getName() + "    " + item.getProduct().getPrice() * item.getQuantity());
        }
        System.out.println("---------------------------");
        customer.currentBalance(paidAmount);
        System.out.println("Subtotal:      " + subtotal);
        System.out.println("Shipping :      " + shippingFees);
        System.out.println("Amount :        " + paidAmount);

    }
}