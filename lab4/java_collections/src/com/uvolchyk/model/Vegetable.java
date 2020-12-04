package com.uvolchyk.model;

public class Vegetable {
    private String name;
    private Integer calorific;
    private Integer weight;

    public Vegetable(String name, Integer calorific, Integer weight) {
        this.name = name;
        this.calorific = calorific;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalorific() {
        return calorific;
    }

    public void setCalorific(Integer calorific) {
        this.calorific = calorific;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "name='" + name + '\'' +
                ", calorific=" + calorific +
                ", weight=" + weight +
                '}';
    }
}
