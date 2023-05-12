package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // create buyers
        List<Buyer> buyers = new ArrayList<>();
        buyers.add(new Buyer("Buyer A", 4.0, true));
        buyers.add(new Buyer("Buyer B", 3.0, false));
        buyers.add(new Buyer("Buyer C", 2.0, false));
        buyers.add(new Buyer("Buyer D", 1.0, true));

        // create sellers
        List<Seller> sellers = new ArrayList<>();
        sellers.add(new Seller("Seller 1", 3.0, true));
        sellers.add(new Seller("Seller 2", 2.0, false));
        sellers.add(new Seller("Seller 3", 5.0, false));
        sellers.add(new Seller("Seller 4", 1.0, true));

        List<Match> matches = MatchAlgorithm.getMatches(buyers, sellers);

        for (Match match : matches) {
            System.out.println(match.getBuyer().getClientId() + " matched with " + match.getSeller().getClientId());
        }
    }



}
