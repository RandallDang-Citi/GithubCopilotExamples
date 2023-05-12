package tf;

class Order {
    private String clientId;
    public int quantity;
    public boolean preference;

    public Order(String clientId, int quantity, boolean preference) {
        this.clientId = clientId;
        this.quantity = quantity;
        this.preference = preference;
    }

    public String getClientId() {
        return clientId;
    }

    public int getQuantity() {
        return quantity;
    }
}
