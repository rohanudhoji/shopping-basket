package com.assignment.shopping.test;

import com.assignment.shopping.Item;
import com.assignment.shopping.ShoppingApplication;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingApplicationTest {


    @Test
    void testCalculateTotalCost_ValidBasket() {
        List<String> basket = List.of("Apple", "Apple", "Banana", "Melon", "Melon", "Lime", "Lime", "Lime");
        int totalCost = ShoppingApplication.calculateTotalCost(basket);
        assertEquals(170, totalCost, "Total cost should be 170p");
    }

    @Test
    void testCalculateTotalCost_EmptyBasket() {
        List<String> basket = List.of();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ShoppingApplication.calculateTotalCost(basket);
        });
        assertEquals("Basket cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCalculateTotalCost_InvalidBasket() {
        List<String> basket = Arrays.asList(
                Item.APPLE.getText(), null, Item.BANANA.getText());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ShoppingApplication.calculateTotalCost(basket);
        });
        assertEquals("Basket contains an invalid item: null", exception.getMessage());
    }


    @Test
    void testSpecialOffers() {
        List<String> basket = List.of("Melon", "Melon", "Lime", "Lime", "Lime", "Lime", "Lime", "Lime");

        int totalCost = ShoppingApplication.calculateTotalCost(basket);
        // 2 melons for 50p (buy one get one free)
        // 6 limes for 60p (three for the price of two)
        assertEquals(110, totalCost, "Total cost should be 110p");
    }
}
