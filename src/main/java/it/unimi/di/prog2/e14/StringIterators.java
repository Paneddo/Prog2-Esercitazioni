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
import java.util.Objects;

/** Utility class with some string iterators. */
public class StringIterators {

    /** . */
    private StringIterators() {
    }

    /**
     * Filters even-length strings.
     *
     * @param it an iterator of strings.
     * @return an iterator that returns the strings of even length of {@code it}.
     * @throws NullPointerException if {@code it} is {@code null}.
     */
    public static Iterator<String> evenIterator(final Iterator<String> it) {
        Objects.requireNonNull(it);
        return new Iterator<>() {

            private String next;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return true;
                }
                while (it.hasNext()) {
                    String candidate = it.next();
                    if (candidate.length() % 2 == 0) {
                        next = candidate;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                String result = next;
                next = null;
                return result;
            }
        };
    }

    /**
     * Converts strings to uppercase.
     *
     * @param it an iterator of strings.
     * @return an iterator that returns the strings of {@code it} in uppercase.
     * @throws NullPointerException if {@code it} is {@code null}.
     */
    public static Iterator<String> uppercase(final Iterator<String> it) {
        Objects.requireNonNull(it);
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                return it.next().toUpperCase();
            }
        };
    }
}
