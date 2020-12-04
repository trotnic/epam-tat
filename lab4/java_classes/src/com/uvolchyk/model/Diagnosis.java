package com.uvolchyk.model;

import java.util.Objects;

public class Diagnosis {
    private String description;

    public Diagnosis(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return Objects.equals(description.toLowerCase(), diagnosis.description.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
