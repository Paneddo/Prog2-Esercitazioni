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

import java.util.Scanner;

/** Client for the {@link ArrayUtils} class. */
public class ArrayUtilsClient {

    /** . */
    private ArrayUtilsClient() {
    }

    /*
     * Write a client that reads 10 integers in increasing order from the standard
     * input and prints the index of the integer passed as argument on the command
     * line if found.
     */

    public static void main(String[] args) {
        int[] array = new int[10];
        try (Scanner sc = new Scanner(System.in)) {
            for (int i = 0; i < array.length; i++) {
                array[i] = sc.nextInt();
            }
        }
        int val = Integer.parseInt(args[0]);
        int searchResult = ArrayUtils.binarySearch(array, val);
        if (searchResult >= 0) {
            System.out.println(searchResult);
        }
    }
}
