package com.example.desafio.model;

import java.util.Objects;

public class Hashtag implements Comparable<Hashtag>{

    private String name;
    private int value;

    public Hashtag(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
        if (this.value < hashtag.value) {
            return 1;
        }
        if (this.value > hashtag.value) {
            return -1;
        }
        return 0;
    }
}
