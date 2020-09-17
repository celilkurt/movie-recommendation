package com.recommendation.model;

import lombok.Getter;

@Getter
public class Rating implements Comparable<Rating>{

    private String item;
    private double value;

    public Rating(String item, double value) {
        this.item = item;
        this.value = value;
    }

    @Override
    public String toString() {
        return "entity.Rating{" +
                "item='" + item + '\'' +
                ", value=" + value +
                '}';
    }


    public int compareTo(Rating other) {

        if (value < other.value) return -1;
        if (value > other.value) return 1;

        return 0;
    }
}
