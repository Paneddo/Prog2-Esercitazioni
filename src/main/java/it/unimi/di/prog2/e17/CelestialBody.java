package it.unimi.di.prog2.e17;

/**
 * A celestial body in space, characterized by a name and a position.
 */
public abstract class CelestialBody implements Comparable<CelestialBody> {

    private String name;
    private SpacePoint position;

    /*
    * RI:
    *  name != null and name.length() > 0
    *  position != null
    * 
    * AF:
    * Represents a celestial body with a specific name and position in space.
    */

    /** Constructs a celestial body with the given name and position. */
    public CelestialBody(String name, SpacePoint position) {
        this.name = name;
        this.position = position;
    }

    /** Returns the name of the celestial body. 
     * @return the name of the celestial body
    */
    public String getName() {
        return name;
    }

    /** Returns the position of the celestial body in space. 
     * @return the position of the celestial body
    */
    public SpacePoint getPosition() {
        return position;
    }

    /** Returns the potential energy of the celestial body. 
     * <p>The potential energy is defined as the norm of its {@code position} point.
     * @return the potential energy of the celestial body
    */
    public int potentialEnergy() {
        return position.norm();
    }

    /** Sets the position of the celestial body.
     * @param position the new position to set
     */
    protected void setPosition(SpacePoint position) {
        this.position = position;
    }

    /** Returns the total energy of the celestial body.
     * <p> A celestial body's total energy is defined as the product of its potential energy and its kinetic energy.
     */
    public abstract int energy();

    @Override
    public int compareTo(CelestialBody other) {
        return this.name.compareTo(other.name);
    }
}
