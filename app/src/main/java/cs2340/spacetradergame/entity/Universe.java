package cs2340.spacetradergame.entity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Universe {

public static final int universeWidth = 150;
public static final int universeHeight = 100;

    private Set<SolarSystem> systems = new HashSet<>();

    public Universe() {
        Random random = new Random();
        int systemNum = 22 + random.nextInt(5);
        for (int i = 0; i < systemNum; ++i) {
            String name = (('A' + random.nextInt(26))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
            );
            SolarSystem curr;
            boolean end;
            do {
                curr = new SolarSystem(name);
                end = systems.add(curr);
            } while(end);
            curr.startSystem();
        }
    }
}
