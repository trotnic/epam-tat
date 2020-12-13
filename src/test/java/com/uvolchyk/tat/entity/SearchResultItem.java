package com.uvolchyk.tat.entity;

import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResultItem that = (SearchResultItem) o;
        return Objects.equals(title.toLowerCase(), that.title.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
