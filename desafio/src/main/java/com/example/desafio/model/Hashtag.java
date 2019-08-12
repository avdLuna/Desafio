package com.example.desafio.model;

import java.util.Objects;

public class Hashtag implements Comparable<Hashtag>{

    private String name;
    private int amount;

    public Hashtag(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
        if (this.amount < hashtag.amount) {
            return 1;
        }
        if (this.amount > hashtag.amount) {
            return -1;
        }
        return 0;
    }
}
