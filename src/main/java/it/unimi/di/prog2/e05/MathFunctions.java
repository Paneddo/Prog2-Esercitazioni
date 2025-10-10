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

package it.unimi.di.prog2.e05;

/** Utility class for computing mathematical functions. */
public class MathFunctions {

    /** . */
    private MathFunctions() {
    }

    /*
     * Specify and implement a method that given a positive number returns an
     * approximation of its square root.
     *
     * Hint: https://en.wikipedia.org/wiki/Bisection_method
     */

    /**
     * Funzione che restituisce un'approssimazione della radice quadrata di un
     * numero positivo.
     * 
     * <p>
     * L'approssimazione deve essere tale che la differenza tra il quadrato del
     * risultato e il numero di partenza sia minore di {@code 10^-3}.
     *
     * @param n il numero di cui calcolare la radice quadrata
     * @return {@code y : y^2 = n} se {@code n >= 0}, {@code -1} altrimenti
     */

    public static double sqrt(double n) {
        if (n < 0) {
            return -1;
        }
        double low = 0;
        double high = n / 2 + 1;
        double mid = (low + high) / 2;
        while (Math.abs(mid * mid - n) >= 0.001) {
            if (mid * mid < n) {
                low = mid;
            } else {
                high = mid;
            }
            mid = (low + high) / 2;
        }
        return mid;
    }
}
