package it.unimi.di.prog2.e17;

public class Star extends CelestialBody {

    /*
    * RI:
    * Inherited from CelestialBody.
    * 
    * AF:
    * Represents a star, a specific type of celestial body.
    */

    /** Constructs a star with the given name and position. */
    public Star(String name, SpacePoint position) {
        super(name, position);
    }

    @Override
    public int energy() {
        return 0;
    }

    @Override
    public String toString() {
        return "Star, name: " + getName() + ", pos: " + getPosition();
    }
}
