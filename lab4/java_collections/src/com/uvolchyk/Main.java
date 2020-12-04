package com.uvolchyk;

import com.uvolchyk.model.Salad;
import com.uvolchyk.model.Vegetable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        List<Vegetable> vegetables = Arrays.asList(
                new Vegetable("Cucumber", 50, 200),
                new Vegetable("Tomato", 45, 200),
                new Vegetable("Carrot", 37, 100),
                new Vegetable("Capsicum", 90, 300)
        );
        
        Random random = new Random();
        Salad salad = new Salad(
                IntStream.range(0,3)
                        .mapToObj(i -> vegetables.get(random.nextInt(vegetables.size())))
                        .collect(Collectors.toSet())
        );

        salad.filterVegetablesWithCalories(50,100)
                .forEach(System.out::println);
        System.out.println(salad.countCalorific());

        salad.getFormula()
                .stream().sorted(Comparator.comparing(Vegetable::getCalorific))
                .forEach(System.out::println);
    }
}
