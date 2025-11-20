package it.unimi.di.prog2.e17;

import java.util.Objects;

public class Planet extends CelestialBody {

    private SpacePoint velocity;

    /*
    * RI:
    * Inherited from CelestialBody, and
    * velocity != null
    * 
    * AF:
    * Represents a planet with its velocity, a specific type of celestial body.
    */

    /** Constructs a planet with the given name, position, and velocity. */
    public Planet(String name, SpacePoint position) {
        super(name, position);
        this.velocity = new SpacePoint(0, 0, 0);
    }

    /** Returns the velocity of the planet. 
     * @return the velocity of the planet
    */
    public SpacePoint getVelocity() {
        return velocity;
    }

    /** Returns the kinetic energy of the planet.
     * <p>The kinetic energy is defined as the norm of its {@code velocity} point.
     * 
     * @return the kinetic energy of the planet
    */
    public int kineticEnergy() {
        return velocity.norm();
    }

    @Override
    public int energy() {
        return kineticEnergy() * potentialEnergy();
    }

    /** Modifies the velocity of the planet based on the attraction towards another celestial body.
     * 
     * @param c the celestial body towards which the planet gravitates
     * @throws NullPointerException if {@code c} is null
     */
    public void gravitateTowards(CelestialBody c) throws NullPointerException {
        Objects.requireNonNull(c);

        SpacePoint otherPos = c.getPosition();
        int x = Integer.signum(otherPos.x() - getPosition().x());
        int y = Integer.signum(otherPos.y() - getPosition().y());
        int z = Integer.signum(otherPos.z() - getPosition().z());

        velocity = velocity.add(new SpacePoint(x, y, z));
    }

    /** Updates the position of the planet by applying its velocity. 
     * <p> Applying the velocity means adding the velocity vector to the current position.
    */
    public void applyVelocity() {
        setPosition(getPosition().add(velocity));
    }

    @Override
    public String toString() {
        return "Planet, name: " + getName() + ", pos: " + getPosition() + ", vel: " + velocity;
    }
}
