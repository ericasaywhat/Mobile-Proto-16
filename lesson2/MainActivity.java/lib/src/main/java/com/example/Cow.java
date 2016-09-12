package com.example;

import java.util.Random;

/**
 * Created by erica on 9/11/16.
 */
public class Cow extends Animal{

    public Cow(String name, String color) {
        super(4,new Random().nextInt(100)+100,"Cow", name, color);
    }


    @Override
    public void grow() {
        setWeight(getWeight()*5);

    }
}
