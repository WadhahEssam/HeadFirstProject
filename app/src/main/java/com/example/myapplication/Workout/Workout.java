package com.example.myapplication.Workout;

import androidx.annotation.NonNull;

public class Workout {
    private String name;
    private String description;

    public static final Workout[] workouts = {
            new Workout("The Limb Loosener", "5 hands pushups or anothing you want"),
            new Workout("Core Agony", "100 Pull-ups \n Push-ups \n 100 Sit-ups"),
            new Workout("Strength and Length", "500 meter run \n 21 x 1.5 pood")
    };

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return name + ' ' + description;
    }
}
