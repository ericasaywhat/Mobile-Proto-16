package com.example;

import java.util.Random;

/**
 * Created by erica on 9/11/16.
 */
public class Cat extends Animal{

    public Cat(String name, String color) {
        super(4, new Random().nextInt(25), "Cat", name, color);
    }


    @Override
    public void grow() {
        setWeight(getWeight()*3);
    }
}
