package com.example;

import java.util.*;

/**
 * Created by erica on 9/11/16.
 */
public class Farm {
    ArrayList<Animal> animalList;

    public Farm() {
        this.animalList = new ArrayList<>();
    }

    public void addAnimal(Animal newAnimal) {
        animalList.add(newAnimal);
    }

    public Animal getAnimal(int index) {
        return animalList.get(index);
    }

    public ArrayList<Animal> getHeaviestAnimals() {
        ArrayList<Animal> sortedAnimalList = new ArrayList<>(animalList);
        Collections.sort(sortedAnimalList, new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                if(o1.getWeight() > o2.getWeight()){
                    return -1;
                }
                else if (o1.getWeight() < o2.getWeight()) {
                    return 1;

                }
                else {
                    return 0;
                }

            }
        });
        return sortedAnimalList;
    }

    public void printCatNames() {
        int length = animalList.size();
        for (int i = 0; i <= length-1; i++ ) {
            if (animalList.get(i).getSpecies()== "Cat") {
                System.out.println(animalList.get(i).getName());
            }


        }

    }

    public double averageLegs() {
        int length = animalList.size();
        double totalLegs = 0;
        for (int i = 0; i <= length-1; i++ ) {
            totalLegs += animalList.get(i).getNumLegs();
        }
        return totalLegs/length;
    }

    public static void main(String[] args) {
        Cat c = new Cat("Meowth", "black");
        Dog d = new Dog("Puppy", "brown");
        Cow cow = new Cow("Mooer", "white");
        System.out.println("Test 1 Passed: " + (c.getWeight() >= 0 && c.getWeight() <= 25));
        System.out.println("Test 2 Passed: " + (d.getWeight() >= 0 && d.getWeight() <= 25));
        System.out.println("Test 3 Passed: " + (cow.getWeight() >= 100 && cow.getWeight() <= 200));

        double old_weight_cat = c.getWeight();
        double old_weight_dog = d.getWeight();
        double old_weight_cow = cow.getWeight();
        c.grow();
        d.grow();
        cow.grow();
        System.out.println("Test 4 Passed: " + (c.getWeight() / old_weight_cat == 3));
        System.out.println("Test 5 Passed: " +
                (Math.abs(d.getWeight() / old_weight_dog - 1.5) < 0.01));
        System.out.println("Test 6 Passed: " + (cow.getWeight() / old_weight_cow == 5));

        Farm farm = new Farm();
        farm.addAnimal(c);
        farm.addAnimal(d);
        farm.addAnimal(cow);

        ArrayList<Animal> sorted = farm.getHeaviestAnimals();
        for(int i = 0; i < sorted.size() - 1; i++) {
            System.out.println("Test " + (i + 7) + " Passed: " + (sorted.get(i).getWeight() > sorted.get(i + 1).getWeight()));
        }
        System.out.println("Test 9 Passed: " + (farm.getAnimal(0) == c));
        System.out.println("Test 10 Passed: " + (farm.getAnimal(1) == d));
        System.out.println("Test 11 Passed: " + (farm.getAnimal(2) == cow));
        c.setLeg(7);  // lol 7 legged cat
        System.out.println("Test 12 Passed: " + (farm.averageLegs() == 5));

        System.out.println("Printing 'Meowth'...");
        farm.printCatNames();
    }

}
