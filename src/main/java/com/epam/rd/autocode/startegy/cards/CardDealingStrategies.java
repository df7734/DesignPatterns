package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> map = new TreeMap<>();
            for(int i=1; i<=players; i++){
                map.put("Player " + i, List.of(deck.dealCard()));
            }
            for(int i=1; i<=players; i++) {
                List<Card> list = map.get("Player " + i);
                list = new ArrayList<>(list);
                list.add(deck.dealCard());
                map.put("Player " + i, list);
            }
            map.put("Community", List.of(deck.dealCard(), deck.dealCard(), deck.dealCard(), deck.dealCard(), deck.dealCard()));
            map.put("Remaining", deck.restCards());
            return map;
        };
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> map = new TreeMap<>();
            for(int i=1; i<=players; i++){
                map.put("Player " + i, List.of());
            }
            for(int j = 0; j<5; j++){
                for(int i=1; i<=players; i++) {
                    List<Card> list = map.get("Player " + i);
                    list = new ArrayList<>(list);
                    list.add(deck.dealCard());
                    map.put("Player " + i, list);
                }
            }
            map.put("Remaining", deck.restCards());
            return map;
        };
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return (deck, players) -> {
            Map<String, List<Card>> map = new TreeMap<>();
            for(int i=1; i<=players; i++){
                map.put("Player " + i, List.of());
            }
            while (deck.size()>0){
                for(int i=1; i<=players; i++) {
                    List<Card> list = map.get("Player " + i);
                    list = new ArrayList<>(list);
                    list.add(deck.dealCard());
                    map.put("Player " + i, list);
                }
            }
            return map;
        };
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return (deck, players) -> {
            Map<String, List<Card>> map = new TreeMap<>();
            for(int i=1; i<=players; i++){
                map.put("Player " + i, List.of());
            }
            for(int j = 0; j<6; j++){
                for(int i=1; i<=players; i++) {
                    List<Card> list = map.get("Player " + i);
                    list = new ArrayList<>(list);
                    list.add(deck.dealCard());
                    map.put("Player " + i, list);
                }
            }
            map.put("Trump card", List.of(deck.dealCard()));
            map.put("Remaining", deck.restCards());
            return map;
        };
    }

}
