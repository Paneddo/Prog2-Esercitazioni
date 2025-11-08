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

/**
 * Represents a range of integers with a {@code start}, {@code end}, and {@code step}.
 *
 * <p> Step can be positive or negative but not zero, as that would lead to an infinite loop.
 */
public class IntRange implements Iterable<Integer> {

  private int from;
  private int to;
  private int step;

  /*
   * RI:
   *  - step != 0
   * 
   * AF:
   *  - represents a range of integers from `from` to `to` (exclusive) with a step of `step`
   *
   */

  /** Constructor that initializes the range as specified in the main */
  public IntRange() {
    from = Integer.MIN_VALUE;
    to = Integer.MAX_VALUE;
    step = 1;
  }

  /**
   * Updates the start of the range.
   *
   * @param from new start of the range
   */
  void setFrom(int from) {
    this.from = from;
  }

  /**
   * Updates the end of the range.
   *
   * @param to new end of the range
   */
  void setTo(int to) {
    this.to = to;
  }

  /**
   * Updates the step of the range.
   *
   * @param step new step of the range
   * @throws IllegalArgumentException if {@code step} != 0
   */
  void setStep(int step) {
    if (step == 0)
      throw new IllegalArgumentException("Step must be non-zero");

    this.step = step;
  }

  @Override
  public Iterator<Integer> iterator() {
    /* Anonymous Class */
    return new Iterator<Integer>() {
      private int current = from;

      @Override
      public boolean hasNext() {
        if (step > 0)
          return current < to;
        else
          return current > to;
      }

      @Override
      public Integer next() {
        if (!hasNext()) {
          throw new IllegalStateException("No more elements");
        }

        int value = current;
        current += step;
        return value;
      }
    };
  }
}
