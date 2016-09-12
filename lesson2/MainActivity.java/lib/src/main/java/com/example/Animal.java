package com.example;

/**
 * Created by erica on 9/11/16.
 */
public abstract class Animal {

    private int numLegs;
    private String name;
    private String color;
    private String species;
    private double weight;


    public Animal(int numLegs, double weight, String species, String name,String color) {
        this.numLegs = numLegs;
        this.weight = weight;
        this.species = species;
        this.color = color;
        this.name = name;
    }

    public int getNumLegs() {
        return numLegs;
    }

    public void setLeg(int numLegs) {
        this.numLegs = numLegs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public abstract void grow();

}
