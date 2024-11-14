public class E_commerce {
    public static void main(String[] args) {
        Customer customer = new Customer(1000000);
        Product cheese = new Product("Cheese", 60.0, 100, true, true, 1.0);
        Product tv = new Product("TV", 3500.0, 2, true, true, 10.0);
        ShoppingCart cart = new ShoppingCart();
        cart.add(cheese, 2);
        cart.add(tv, 2);
        Checkout.checkout(customer, cart);
    }
}
