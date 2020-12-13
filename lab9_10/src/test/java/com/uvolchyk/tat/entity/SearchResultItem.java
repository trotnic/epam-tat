package com.uvolchyk.tat.entity;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultItem {
    private String title;
    private List<Double> priceBounds;

    public SearchResultItem(String title, List<Double> priceBounds) {
        this.title = title;
        this.priceBounds = priceBounds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Double> getPriceBounds() {
        return priceBounds;
    }

    public void setPriceBounds(List<Double> priceBounds) {
        this.priceBounds = priceBounds;
    }

    public Double getActualPrice() {
        return priceBounds.stream().min(Double::compareTo).orElse(0.0);
    }
}
