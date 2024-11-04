import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Shippable {
    String getName();
    double getWeight();
}

class Product {
    private String name;
    private double price;
    private int quantity;
    private boolean isExpired;
    private boolean requiredShipping;
    private double weight;

    public Product(String name, double price, int quantity, boolean isExpired, boolean requiredShipping, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isExpired = isExpired;
        this.requiredShipping = requiredShipping;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public boolean requiredShipping() {
        return requiredShipping;
    }

    public double getWeight() {
        return weight;
    }

    public void currentQuantity(int amount) {
        this.quantity -= amount;
    }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    public AddItemResult add(Product product, int quantity) {
        if (quantity <= 0) {
            return new AddItemResult(false, "Quantity must be greater than zero for " + product.getName());
        }

        if (product.getQuantity() < quantity) {
            return new AddItemResult(false, "Not enough stock available for " + product.getName() + ". Available: " + product.getQuantity());
        }

        // Add item to cart
        items.add(new CartItem(product, quantity));
        product.currentQuantity(quantity);
        return new AddItemResult(true, "Added " + quantity + " of " + product.getName() + " to the cart.");
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}




class Customer {
    private double balance;

    public Customer(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void currentBalance(double amount) {
        balance -= amount;
    }
}

class ShippingFee {
    public void ship(List<Shippable> items) {
        System.out.println("Shipping items:");
        for (Shippable item : items) {
            System.out.println("- " + item.getName() + " (Weight: " + item.getWeight() + ")");
        }
    }
}
class AddItemResult {
    private boolean success;
    private String message;

    public AddItemResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

class Checkout {
    private static final double SHIPPING_FEE_PER_KG = 5.0;

    public void checkout(Customer customer, ShoppingCart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot checkout.");
        }

        double subtotal = 0.0;
        double shippingFees = 0.0;
        List<Shippable> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            subtotal += product.getPrice() * item.getQuantity();
            if (product.requiredShipping()) {
                shippingFees += product.getWeight() * SHIPPING_FEE_PER_KG * item.getQuantity();
                shippableItems.add(new Shippable() {
                    public String getName() {
                        return product.getName();
                    }
                    public double getWeight() {
                        return product.getWeight();
                    }
                });
            }
        }

        double paidAmount = subtotal + shippingFees;

        if (customer.getBalance() < paidAmount) {
            throw new IllegalStateException("Insufficient balance for this purchase.");
        }

        customer.currentBalance(paidAmount);
        System.out.println("Checkout Details:");
        System.out.println("Order Subtotal: $" + subtotal);
        System.out.println("Shipping Fees: $" + shippingFees);
        System.out.println("Total Amount Paid: $" + paidAmount);
        System.out.println("Customer's current balance: $" + customer.getBalance());
        if (!shippableItems.isEmpty()) {
            ShippingFee shippingService = new ShippingFee();
            shippingService.ship(shippableItems);
        }
    }
}
public class E_commerce {
    public static void main(String[] args) {
        Product cheese = new Product("Cheese", 60.0, 100, true, true, 1.0);
        Product tv = new Product("TV", 3500.0, 2, false, true, 10.0);
        ShoppingCart cart = new ShoppingCart();
        AddItemResult result1 = cart.add(cheese, 2);
        System.out.println(result1.getMessage());
        AddItemResult result2 = cart.add(tv, 3);
        System.out.println(result2.getMessage());
        AddItemResult result3 = cart.add(cheese, -1);
        System.out.println(result3.getMessage());
        System.out.println("Cart contains " + cart.getItems().size() + " items.");
    }
}
