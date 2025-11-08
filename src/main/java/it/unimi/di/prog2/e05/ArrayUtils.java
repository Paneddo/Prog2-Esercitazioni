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

/** Classe di metodi di utilità per array. */
public class ArrayUtils {

  /** . */
  private ArrayUtils() {}

  /*
   * Specify and implement a method that given a sorted array of integers and a
   * value, returns the index of the value if it is present in the array.
   *
   * Hint:
   * https://research.google/blog/extra-extra-read-all-about-it-nearly-all-binary-
   * searches-and-mergesorts-are-broken/
   */

  /**
   * Funzione che svolge una ricerca binaria.
   *
   * @param array l'array su cui effettuare la ricerca, deve essere ordinato e non {@code null}
   * @param value il valore da cercare
   * @return l'indice dell'elemento cercato, -1 se non trovato
   */
  public static int binarySearch(int[] array, int value) {
    int low = 0;
    int high = array.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (array[mid] == value) {
        return mid;
      } else if (value > array[mid]) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }
}
