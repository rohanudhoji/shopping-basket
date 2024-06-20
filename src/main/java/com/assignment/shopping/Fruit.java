package com.assignment.shopping;

public enum Fruit {
    APPLE("Apple"),
    BANANA("Banana"),
    MELON("Melon"),
    LIME("Lime");

    public final String label;

    private Fruit(String label) {
        this.label = label;
    }

    public String getText() {
        return this.label;
    }

}
