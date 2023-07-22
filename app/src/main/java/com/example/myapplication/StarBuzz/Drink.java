package com.example.myapplication.StarBuzz;

import androidx.annotation.NonNull;

public class Drink {
    private String name;
    private String description;

    public Drink(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description;
    }

    public static final Drink[] drinks = {
            new Drink("Latte", "A couple of espresso shots"),
            new Drink("Cappuccion", "Espresso, hot milk, and a steamed milk foam"),
            new Drink("Filter", "highest quality beans roasted and brwed fresh")
    };
}
