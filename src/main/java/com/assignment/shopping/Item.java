package com.assignment.shopping;

public enum Item {
    APPLE("Apple"),
    BANANA("Banana"),
    MELON("Melon"),
    LIME("Lime");

    public final String label;

    private Item(String label) {
        this.label = label;
    }

    public String getText() {
        return this.label;
    }

}
