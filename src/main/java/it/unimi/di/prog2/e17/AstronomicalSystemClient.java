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

package it.unimi.di.prog2.e17;

import java.util.Scanner;

/**
 * Allows verification of the behavior of an astronomical system.
 *
 * <p>For more details, see the <em>overview</em> of this package.
 */
public class AstronomicalSystemClient {

    /** . */
    private AstronomicalSystemClient() {
    }

    /*
     * Simulates an astronomical system.
     *
     * <p>Reads the information of celestial bodies from standard input, and simulates the system for
     * the number of steps specified as the first argument on the command line; hence it emits on the
     * standard error the state of the system and the total energy.
     *
     * @param args the number of simulation steps.
     */

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        AstronomicalSystem astronomicalSystem = new AstronomicalSystem();

        int steps = Integer.parseInt(args[0]);

        try (Scanner s = new Scanner(System.in)) {
            while (s.hasNext()) {
                boolean isPlanet = s.next().charAt(0) == 'P'; // can be P or S
                String name = s.next();
                int x = s.nextInt();
                int y = s.nextInt();
                int z = s.nextInt();

                if (isPlanet) {
                    astronomicalSystem.addBody(new Planet(name, new SpacePoint(x, y, z)));
                } else {
                    astronomicalSystem.addBody(new Star(name, new SpacePoint(x, y, z)));
                }
            }
        }

        for (int i = 0; i < steps; i++) {
            astronomicalSystem.timeStep();
        }

        System.out.println(astronomicalSystem);
    }
}
