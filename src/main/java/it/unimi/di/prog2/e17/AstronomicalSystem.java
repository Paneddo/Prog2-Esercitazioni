package it.unimi.di.prog2.e17;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Class representing an astronomical system containing celestial bodies. */
public class AstronomicalSystem {
    private List<CelestialBody> bodies;

    /** Constructs an empty astronomical system. */
    public AstronomicalSystem() {
        this.bodies = new ArrayList<>();
    }

    private int binarySearch(CelestialBody b) {
        int lo = 0;
        int hi = bodies.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (bodies.get(mid).compareTo(b) > 0)
                hi = mid - 1;
            else if (bodies.get(mid).compareTo(b) < 0)
                lo = mid + 1;
            else
                return mid;
        }
        return -lo - 1;
    }

    /** Adds a celestial body to the system.
     * 
     * @param body the celestial body to add
     * @throws NullPointerException if the body is null
     */
    public void addBody(CelestialBody body) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(body);

        int index = binarySearch(body);

        if (index >= 0) {
            throw new IllegalArgumentException("A body with the same name already exists in the system.");
        }

        index = -index - 1;
        bodies.add(index, body);
    }

    /** Advances the simulation by one time step.
     * <p> In each time step, each planet updates its velocity based on the gravitational attraction
     * towards all other celestial bodies, and then updates its position by applying its velocity.
    */
    public void timeStep() {
        // First, update velocities based on gravitational attraction
        for (CelestialBody body1 : bodies) {
            if (body1 instanceof Planet planet) {
                for (CelestialBody body2 : bodies) {
                    if (body1 != body2) {
                        planet.gravitateTowards(body2);
                    }
                }
            }
        }

        // Then, apply velocities to update positions
        for (CelestialBody body : bodies) {
            if (body instanceof Planet planet) {
                planet.applyVelocity();
            }
        }
    }

    /** Calculates the total energy of the astronomical system. 
     * <p>The total energy is the sum of the energies of all celestial bodies in the system.
    */
    public int totalEnergy() {
        int totalEnergy = 0;
        for (CelestialBody body : bodies) {
            totalEnergy += body.energy();
        }
        return totalEnergy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CelestialBody body : bodies) {
            sb.append(body.toString()).append("\n");
        }
        sb.append("Total Energy: ").append(totalEnergy()).append("\n");
        return sb.toString();
    }
}
