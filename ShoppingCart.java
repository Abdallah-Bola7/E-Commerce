import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw  new RuntimeException("Quantity must be greater than zero for " + product.getName());
        }

        if (product.getQuantity() < quantity) {
            throw  new RuntimeException( "Not enough stock available for " + product.getName() + ". Available: " + product.getQuantity());
        }

        // Add item to cart
        items.add(new CartItem(product, quantity));
        product.currentQuantity(quantity);

    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }
}