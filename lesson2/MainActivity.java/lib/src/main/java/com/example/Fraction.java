package com.example;

/**
 * Created by erica on 9/11/16.
 */
public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static int gcd(int m, int n) {
        if (m>n) {
            if (n==0) {
                return m;
            } else {
                return gcd(n,m%n);
            }
        } else if (n>m) {
            if (m==0) {
                return n;
            } else {
                return gcd(m,n%m);
            }
        } else {
            return m;
        }
    }

    public Fraction simplify() {
        int numGCD = gcd(numerator, denominator);
        numerator = numerator/numGCD;
        denominator = denominator/numGCD;
        return new Fraction(numerator, denominator);
    }

    public Fraction add(Fraction f2) {
        int numerator = this.numerator * f2.denominator + f2.numerator * denominator;
        int denominator = this.denominator * f2.denominator;
        Fraction simpleAddedFrac = new Fraction(numerator, denominator);
        return simpleAddedFrac.simplify();
    }

    public String toString() {
        return Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }

}
