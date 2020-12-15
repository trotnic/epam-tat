package com.uvolchyk.tat.entity;

import com.google.common.base.Objects;

import java.util.List;

public class ProductItem {
    private String title;
    private List<Double> priceBounds;

    public ProductItem(String title, List<Double> priceBounds) {
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
        ProductItem that = (ProductItem) o;
        return Objects.equal(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
