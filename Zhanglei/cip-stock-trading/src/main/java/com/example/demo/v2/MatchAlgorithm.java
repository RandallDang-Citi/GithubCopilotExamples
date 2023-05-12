//package com.example.demo.v2;
//
//import com.example.demo.Buyer;
//import com.example.demo.Match;
//import com.example.demo.Seller;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class MatchAlgorithm {
//    public static List<Match> match(List<Buyer> buyers, List<Seller> sellers) {
//        List<Match> matches = new ArrayList<>();
//
//        // Sort buyers and sellers in descending order of quality/price
//        Collections.sort(buyers, Collections.reverseOrder());
//        Collections.sort(sellers);
//
//        // For each buyer, find the first seller that can satisfy the buyer's demand
//        for (Buyer buyer : buyers) {
//            boolean matched = false;
//            for (Seller seller : sellers) {
//                if (seller.getQuality() >= buyer.getQuality()) {
//                    if (seller.getAON() && matched) {
//                        // Skip AON seller if buyer already matched with another seller
//                        continue;
//                    }
//                    double quantity = Math.min(seller.getQuality(), buyer.getQuality());
//                    Match match = new Match(buyer.getClientId(), seller.getClientId(), quantity);
//                    matches.add(match);
//                    buyer.setQuantity(buyer.getQuantity() - quantity);
//                    seller.setQuantity(seller.getQuantity() - quantity);
//                    if (buyer.getQuantity() == 0) {
//                        // Buyer's demand is fulfilled
//                        break;
//                    }
//                    if (seller.getAON()) {
//                        // Stop looking for more sellers if AON seller is matched
//                        matched = true;
//                    }
//                }
//            }
//        }
//
//        return matches;
//    }
//}
