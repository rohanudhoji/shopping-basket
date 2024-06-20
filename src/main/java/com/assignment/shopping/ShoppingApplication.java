package com.assignment.shopping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShoppingApplication {

    // Constant items prices (in pence)
    private static final int APPLE_PRICE = 35;
    private static final int BANANA_PRICE = 20;
    private static final int MELON_PRICE = 50;
    private static final int LIME_PRICE = 15;


    public static void main(String[] args) {
        // Shopping List
        List<String> basket = List.of("Apple", "Apple", "Banana", "Melon", "Melon", "Lime", "Lime", "Lime");
        int totalCost = calculateTotalCost(basket);
        System.out.println("Total cost: " + totalCost + "p");
    }

    /**
     * calculateTotalCost for given list of basket
     *
     * @param basket
     * @return totalCost
     */
    public static int calculateTotalCost(List<String> basket) {
        checkForValidBasket(basket);
        Map<String, Long> itemCounts = countItems(removeInvalidItems(basket));
        Map<String, PriceCalculationStrategy> pricingStrategies = getPricingStrategies();
        return itemCounts.entrySet().stream()
                .mapToInt(entry -> {
                    String item = entry.getKey();
                    int count = Math.toIntExact(entry.getValue());
                    PriceCalculationStrategy strategy = pricingStrategies.get(item);
                    return strategy != null ? strategy.apply(count) : 0;
                })
                .sum();
    }

    /**
     * Check is basket is empty or any null items
     *
     * @param basket
     */
    private static void checkForValidBasket(List<String> basket) {

        if (basket == null || basket.isEmpty()) {
            throw new IllegalArgumentException("Basket cannot be null or empty");
        }

        for (String item : basket) {
            if (item == null) {
                throw new IllegalArgumentException("Basket contains an invalid item: null");
            }
        }

    }

    /**
     * remove invalid items from List.
     *
     * @param basket
     * @return
     */
    private static List<String> removeInvalidItems(List<String> basket) {
        List<Item> fruitList = Arrays.asList(Item.values());
        return basket.stream().filter(item ->
                (fruitList.stream().filter(fruit -> fruit.getText().equals(item)))
                        .count() > 0).collect(Collectors.toList());
    }

    /**
     * Convert List to Map with count of each items
     *
     * @param basket
     * @return Map<String, Long>
     */
    private static Map<String, Long> countItems(List<String> basket) {
        return basket.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Load all the Price Calculation Strategy for each product
     *
     * @return Map<String, PriceCalculationStrategy>
     */
    private static Map<String, PriceCalculationStrategy> getPricingStrategies() {
        Map<String, PriceCalculationStrategy> pricingStrategies = new HashMap<>();
        // For New Item new pricingStrategies can be added.
        pricingStrategies.put(Item.APPLE.getText(), count -> count * APPLE_PRICE);
        pricingStrategies.put(Item.BANANA.getText(), count -> count * BANANA_PRICE);
        pricingStrategies.put(Item.MELON.getText(), count -> (count / 2 + count % 2) * MELON_PRICE);
        pricingStrategies.put(Item.LIME.getText(), count -> (count / 3 * 2 + count % 3) * LIME_PRICE);

        return pricingStrategies;
    }
}

