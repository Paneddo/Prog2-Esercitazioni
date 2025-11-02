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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A map from {@link String} to {@link Integer}.
 *
 * <p>A <em>map</em> is a collection that associates keys to values. In this case, the keys are
 * strings and the values are integers. The map cannot contain duplicate keys, which means that each
 * key can be associated to at most one value.
 */
public class StringToIntMap {

    /**
     * A record holding an entry for the map
     *
     * @param key   the entry key
     * @param value the value associated to our key
     */
    private record Entry(String key, int value) {

        /*
         * RI:
         *
         * - key != null
         *
         * AF:
         *
         *  - represents the mapping from key to value
         */

        public Entry {
            Objects.requireNonNull(key, "Key cannot be null");
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (!(obj instanceof Entry other))
                return false;

            return key.equals(other.key) && value == other.value;
        }
    }

    private final List<Entry> entries;

    /*
     * RI:
     *  entries != null and does not contain null elements
     *  entries is sorted by key, and does not contain duplicate keys
     * 
     * AF:
     *  the list entries represents the set of key-value mappings in this map
     */

    /** Creates a new empty map. */
    public StringToIntMap() {
        this.entries = new ArrayList<>();
    }

    /**
     * Returns the size of this map.
     *
     * @return the number of key-value mappings in this map.
     */
    public int size() {
        return entries.size();
    }

    /**
     * Returns if this map is empty.
     *
     * @return {@code true} iff this map contains no key-value mappings.
     */
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /**
     * Returns if this map contains the specified key.
     *
     * @param key the key to search for.
     * @return {@code true} iff this map contains a key-value mappings with the given {@code key}.
     */
    public boolean containsKey(String key) {
        return findKeyIndex(key) >= 0;
    }

    /**
     * Returns if this map contains the specified value.
     *
     * @param value the value to search for.
     * @return {@code true} iff this map contains a key-value mappings with the given {@code value}.
     */
    public boolean containsValue(int value) {
        for (Entry entry : entries) {
            if (entry.value() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped.
     *
     * @param key the key whose associated value is to be returned.
     * @return the value to which the specified key is mapped.
     * @throws NoSuchElementException if this map contains no mapping for the key, or the key is
     *     {@code null}.
     */
    public int get(String key) throws NoSuchElementException {
        int index = findKeyIndex(key);
        if (index >= 0) {
            return entries.get(index).value();
        } else {
            throw new NoSuchElementException("Key not found: " + key);
        }
    }

    /**
     * Associates in this map the new key with the specified value.
     *
     * @param key the key with which the specified value is to be associated.
     * @param value the value to be associated with the specified key.
     * @throws IllegalArgumentException if the map already contain a mapping for the key.
     * @throws NullPointerException if the key is {@code null}.
     */
    public void put(String key, int value) {
        int index = findKeyIndex(key);
        if (index >= 0) {
            throw new IllegalArgumentException("Key already exists: " + key);
        } else {
            int insertionPoint = -index - 1;
            entries.add(insertionPoint, new Entry(key, value));
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key the key whose mapping is to be removed from the map.
     * @return {@code true} iff this map contained a mapping for the specified key, and hence is
     *     modified by this operation.
     */
    public boolean remove(String key) {
        int index = findKeyIndex(key);
        if (index >= 0) {
            entries.remove(index);
            return true;
        } else {
            return false;
        }
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        entries.clear();
    }

    /**
     * Finds the index of the specified key in the entries list.
     *
     * @param key the key to search for.
     * @return the index of the key if found; otherwise, {@code -insertion_point - 1}, where
     *     insertion_point is the index where the key would be inserted to maintain sorted order.
     */
    private int findKeyIndex(String key) {
        int lo = 0;
        int hi = entries.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key.compareTo(entries.get(mid).key()) < 0)
                hi = mid - 1;
            else if (key.compareTo(entries.get(mid).key()) > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return -lo - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);
            sb.append(entry.key()).append("=").append(entry.value());
            if (i < entries.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof StringToIntMap other))
            return false;

        return entries.equals(other.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries);
    }
}
