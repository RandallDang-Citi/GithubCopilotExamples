package com.example.demo;

import com.example.demo.Buyer;
import com.example.demo.Match;
import com.example.demo.Seller;

import java.util.ArrayList;
import java.util.List;

public class MatchAlgorithm {

    public static List<Match> getMatches(List<Buyer> buyers, List<Seller> sellers) {
        List<Match> matches = new ArrayList<>();
        for (Buyer buyer : buyers) {
            Seller selectedSeller = null;
            double maxQuality = Double.NEGATIVE_INFINITY;
            for (Seller seller : sellers) {
                if (seller.getAON() && selectedSeller != null) {
                    // 如果卖家是AON且已经有匹配的卖家，则跳过该卖家
                    continue;
                }
                if (seller.getQuality() > maxQuality && seller.getQuality() >= buyer.getQuality()) {
                    // 如果卖家的quality大于目前最大值且大于等于买家的quality，则更新最大值和匹配卖家
                    selectedSeller = seller;
                    maxQuality = seller.getQuality();
                }
            }
            if (selectedSeller != null) {
                // 如果存在匹配的卖家，则添加该匹配信息到结果中
                matches.add(new Match(buyer, selectedSeller));
                sellers.remove(selectedSeller);
            }
        }
        return matches;
    }
}