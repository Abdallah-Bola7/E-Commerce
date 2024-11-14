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