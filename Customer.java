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