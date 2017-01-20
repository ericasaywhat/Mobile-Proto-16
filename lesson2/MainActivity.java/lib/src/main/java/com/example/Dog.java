package com.example;
import java.util.Random;

/**
 * Created by erica on 9/11/16.
 */
public class Dog extends Animal {


    public Dog(String name, String color) {
        super(4, new Random().nextInt(25), "Dog", name, color);
    }


    @Override
    public void grow() {
        setWeight(getWeight()*1.5);

    }
}
