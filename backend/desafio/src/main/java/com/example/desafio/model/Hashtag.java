package com.example.desafio.model;

import java.util.Objects;

public class Hashtag implements Comparable<Hashtag>{

    private String name;
    private int values;

    public Hashtag(String name, int values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return name.equals(hashtag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Hashtag hashtag) {
        if (this.values < hashtag.values) {
            return 1;
        }
        if (this.values > hashtag.values) {
            return -1;
        }
        return 0;
    }
}
