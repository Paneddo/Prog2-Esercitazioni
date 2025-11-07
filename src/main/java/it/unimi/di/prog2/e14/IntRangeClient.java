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

package it.unimi.di.prog2.e14;

import java.util.Iterator;
import java.util.Scanner;

/** A class to test int ranges. */
public class IntRangeClient {

  /** . */
  private IntRangeClient() {}

  /**
   * Prints a description of an iterator on integers.
   *
   * <p>Prints the <i>total number of iterations</i>, if such number is positive, it also prints the
   * <i>first</i> iterated value and, if it is different from it, the <i>last</i> one.
   *
   * @param it the iterator, mast not be {@code null}.
   */
  public static void printIterationsFirstLast(Iterator<Integer> it) {
    int iterations = 0, first = 0, last = 0;
    if (it.hasNext()) {
      first = it.next();
      iterations++;
      while (it.hasNext()) {
        last = it.next();
        iterations++;
      }
    }
    System.out.println(
        iterations + (iterations > 0 ? " " + first : "") + (iterations > 1 ? " " + last : ""));
  }

  /*
   * A method to test the {@link IntRange} class.
   *
   * <p>This methods reads the parameters of an {@link IntRange} from the lines in the standard
   * input in the form {@code command value}, where the command is:
   *
   * <ul>
   *   <li>{@code F} to set the from value of the range.
   *   <li>{@code T} to set the to value of the range.
   *   <li>{@code S} to set the step of the range.
   * </ul>
   *
   * commands can be repeated, the last value is the one that is considered; the default values for
   * from, to and step are respectively {@link Integer#MIN_VALUE}, {@link Integer#MAX_VALUE}, and 1.
   * Once the input is exhausted, the method emits in a single line of the standard output:
   *
   * <ul>
   *   <li>the number of integers in the range,
   *   <li>the first integer in the range (if any),
   *   <li>the last integer in the range (if different from the first).
   * </ul>
   *
   * @param args not used.
   */

  public static void main(String[] args) {

    IntRange range = new IntRange();

    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNext()) {
        char command = sc.next().charAt(0);
        int value = sc.nextInt();
        switch (command) {
          case 'F':
            range.setFrom(value);
            break;
          case 'T':
            range.setTo(value);
            break;
          case 'S':
            range.setStep(value);
            break;
          default:
            throw new IllegalArgumentException("Unknown command: " + command);
        }
      }
    }
    Iterator<Integer> it = range.iterator();
    printIterationsFirstLast(it);
  }
}
