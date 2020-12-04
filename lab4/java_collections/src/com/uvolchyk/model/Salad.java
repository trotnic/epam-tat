package com.uvolchyk.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Salad {
    private Set<Vegetable> formula;

    public Salad(Set<Vegetable> formula) {
        this.formula = formula;
    }

    public Salad() {
        this.formula = new HashSet<>();
    }

    public Set<Vegetable> getFormula() {
        return formula;
    }

    public void setFormula(Set<Vegetable> formula) {
        this.formula = formula;
    }

    public void addVegetable(Vegetable vegetable) {
        this.formula.add(vegetable);
    }

    public void removeVegetable(Vegetable vegetable) {
        this.formula.remove(vegetable);
    }

    public Integer countCalorific() {
        return this.formula.stream()
                .map(Vegetable::getCalorific)
                .reduce(0, Integer::sum);
    }

    public Set<Vegetable> filterVegetablesWithCalories(Integer lowerBound, Integer upperBound) {
        return this.formula.stream()
                .filter(v -> v.getCalorific() >= lowerBound)
                .filter(v -> v.getCalorific() <= upperBound)
                .collect(Collectors.toSet());
    }
}
