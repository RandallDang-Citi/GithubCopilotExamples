package bills;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MarketMatching {
    public static void main(String[] args) {
        // Buy-side orders
        List<Order> buySide = new ArrayList<>();
        buySide.add(new Order("ClientX", 1000,true)); // ALL or None preference
        buySide.add(new Order("ClientY", 300, false)); // ALL or None preference
        buySide.add(new Order("ClientZ", 500, false)); // No preference

        // Sell-side orders
        List<Order> sellSide = new ArrayList<>();
        sellSide.add(new Order("ClientA", 800, false));
        sellSide.add(new Order("ClientB", 1000, true));
        sellSide.add(new Order("ClientC", 600, false));
        sellSide.add(new Order("ClientD", 500, false));

        // Match buy-side and sell-side orders
        List<String> matches = matchOrders(buySide, sellSide);

        // Print the matches
        System.out.println("Matched orders:");
        for (String match : matches) {
            System.out.println(match);
        }
    }

    public static List<String> matchOrders(List<Order> buySide, List<Order> sellSide) {
        List<String> matches = new ArrayList<>();

        // Sort buy-side and sell-side orders in descending order of quantity
        Collections.sort(buySide, Comparator.comparingInt(Order::getQuantity).reversed());
        Collections.sort(sellSide, Comparator.comparingInt(Order::getQuantity).reversed());

        for (Order buyOrder : buySide) {
            int buyQuantity = buyOrder.getQuantity();
            String buyClientId = buyOrder.getClientId();

            if (buyOrder.preference) {
                // ALL or None preference
                for (Order sellOrder : sellSide) {
                    int sellQuantity = sellOrder.getQuantity();
                    String sellClientId = sellOrder.getClientId();

                    if (sellQuantity >= buyQuantity) {
                        matches.add("Buyer: " + buyClientId + ", Seller: " + sellClientId + ", Quantity: " + buyQuantity);
                        sellOrder.quantity -= buyQuantity;
                        break;
                    }
                }
            } else {
                // No preference, match with multiple counterparties
                for (Order sellOrder : sellSide) {
                    int sellQuantity = sellOrder.getQuantity();
                    String sellClientId = sellOrder.getClientId();

                    if (sellQuantity > 0) {
                        int matchQuantity = Math.min(buyQuantity, sellQuantity);
                        matches.add("Buyer: " + buyClientId + ", Seller: " + sellClientId + ", Quantity: " + matchQuantity);
                        sellOrder.quantity -= matchQuantity;
                        buyQuantity -= matchQuantity;

                        if (buyQuantity == 0) {
                            break;
                        }
                    }
                }
            }
        }

        return matches;
    }
}
