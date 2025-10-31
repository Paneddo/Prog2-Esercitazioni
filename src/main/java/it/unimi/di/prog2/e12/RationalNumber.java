/*

Copyright 2025 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.e12;

import java.util.Objects;

/**
 * A rational number is an immutable number that can be expressed as the
 * quotient or fraction \( p/q
 * \) of two {@code int}s, a numerator \( p \) and a non-zero denominator \( q
 * \).
 */
public class RationalNumber {
    private final int numerator;
    private final int denominator;

    /*
     * RI:
     * denominator != 0
     * numerator and denominator are in reduced form, MCD(numerator, denominator) = 1
     * 
     * AF:
     * The rational number p/q is represented by numerator=p and denominator=q
     */

    /**
     * Creates a new rational number.
     *
     * @param numerator   the numerator.
     * @param denominator the denominator.
     * @throws IllegalArgumentException if {@code denominator} is zero.
     */
    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        // Reduce to lowest terms
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;

        assert gcd(Math.abs(this.numerator), Math.abs(this.denominator)) == 1 : "Rational number not in reduced form"; // Check RI
    }

    /**
     * Computes the greatest common denominator of two integers.
     * 
     * @param a first integer, non-negative
     * @param b second integer, non-negative
     * @return the greatest common denominator
     */
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Returns the sum of this rational number and another one.
     *
     * @param other the other rational number.
     * @return the sum of this rational number and {@code other}.
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    public RationalNumber add(RationalNumber other) {
        Objects.requireNonNull(other, "Other rational number cannot be null");
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalNumber(newNumerator, newDenominator);
    }

    /**
     * Returns the product of this rational number and another one.
     *
     * @param other the other rational number.
     * @return the product of this rational number and {@code other}.
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    public RationalNumber mul(RationalNumber other) {
        Objects.requireNonNull(other, "Other rational number cannot be null");
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalNumber(newNumerator, newDenominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RationalNumber other) {
            return this.numerator == other.numerator && this.denominator == other.denominator;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
