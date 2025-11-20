package it.unimi.di.prog2.e17;

import java.util.StringJoiner;

/**
 * Class that represents an immutable three dimensional point.
 */
public class SpacePoint {
    private final int x;
    private final int y;
    private final int z;

    /*
     * RI:
     * 
     * AF:
     *  A SpacePoint represents the point (x, y, z) in a three-dimensional space. 
    */

    /** Initializes the point at the origin (0,0,0). */
    public SpacePoint() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /** Initializes the point at the given coordinates.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param z the z coordinate.
     */
    public SpacePoint(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /** Returns the norm of the point.
     * 
     * <p>The norm is defined as |x| + |y| + |z|.
     * It's also know as <span class="math inline">ℓ<sub>1</sub></span> norm
     *
     * @return the Manhattan norm of this point.
     */
    public int norm() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    /** Returns the x coordinate.
     *
     * @return the x coordinate.
     */
    public int x() {
        return x;
    }

    /** Returns the y coordinate.
     *
     * @return the y coordinate.
     */
    public int y() {
        return y;
    }

    /** Returns the z coordinate.
     *
     * @return the z coordinate.
     */
    public int z() {
        return z;
    }

    /** Fabricates a new SpacePoint which is the sum of this point and another point.
     * 
     * @param other the other point to add.
     * @return a new SpacePoint which is the sum of this point and {@code other}.
     */
    public SpacePoint add(SpacePoint other) {
        return new SpacePoint(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /** Fabricates a new SpacePoint which is the difference of this point and another point.
     * 
     * @param other the other point to subtract.
     * @return a new SpacePoint which is the difference of this point and {@code other}.
     */
    public SpacePoint sub(SpacePoint other) {
        return new SpacePoint(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        sj.add(String.valueOf(x));
        sj.add(String.valueOf(y));
        sj.add(String.valueOf(z));
        return sj.toString();
    }
}
