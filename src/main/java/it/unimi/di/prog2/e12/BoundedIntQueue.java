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
 * A <em>queue</em> is a mutable data structure that provides access to its elements in
 * first-in/first-out order.
 *
 * <p>A <em>bounded</em> queue has an upper bound, established when a queue is created, on the
 * number of elements that can be stored in the queue.
 */
public class BoundedIntQueue {

    // EXERCISE: complete following the specification (with particular attention
    // to the eventual exceptions) and provide an implementation (including the
    // equals, hashCode, and toString methods); add methods that are adequate to
    // the specification. Provide also the RI and AF.

    // Given the boundedness constraint, it is not allowed to use any Java
    // Collection Framework class. An array can be used to store the elements in a
    // circular buffer (see https://www.wikiwand.com/en/articles/Circular_buffer).

    private final int[] elements;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    /*
     * RI: 
     *  elements != null
     *  size <= elements.length
     *  head, tail, size >= 0
     *  if size > 0 then elements between head and tail are valid elements
     * 
     * AF:
     *  the queue is represented in a circular buffer not longer than size
     *  head represents the idx of the first element, tail is the idx of the first free spot
     */

    /**
     * Creates a new bounded queue with the given capacity.
     *
     * @param capacity the capacity of the queue.
     * @throws IllegalArgumentException if {@code capacity} is negative.
     */
    public BoundedIntQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Negative capacity: " + capacity);
        }
        this.elements = new int[capacity];
    }

    /**
     * Adds an element to the queue.
     *
     * @param x the element to add.
     * @throws IllegalStateException if the queue is full.
     */
    public void enqueue(int x) {
        if (size == elements.length) {
            throw new IllegalStateException("Queue is full");
        }
        elements[tail] = x;
        tail = (tail + 1) % elements.length;
        size++;
    }

    /**
     * Removes the element at the head of the queue.
     *
     * @return the element at the head of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        int x = elements[head];
        head = (head + 1) % elements.length;
        size--;
        return x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BoundedIntQueue: [");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(elements[(head + i) % elements.length]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BoundedIntQueue other)) {
            return false;
        }
        if (size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (elements[(head + i) % elements.length] != other.elements[(other.head + i) % other.elements.length]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        for (int i = 0; i < size; i++) {
            result += Integer.hashCode(elements[(head + i) % elements.length]);
        }
        return result;
    }
}
