package cs2340.spacetradergame.entity;

import android.util.Log;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cs2340.spacetradergame.model.RandomMethods;

/**
 * universe
 */
public class Universe {


    public static final int universeWidth = 150;
    public static final int universeHeight = 100;

    private List<SolarSystem> systems;

    /**
     * constructor for universe
     */
    public Universe() {
        class PlanetList {
            // Many of these names are from Star Trek: The Next Generation, or are small changes
            // to names of this series. A few have different origins.
            private String[] planetNames = {
                    "Acamar",
                    "Adahn",        // The alternate personality for The Nameless One in "Planescape: Torment"
                    "Aldea", "Andevian", "Antedi", "Balosnee", "Baratas",
                    "Brax",            // One of the heroes in Master of Magic
                    "Bretel",        // This is a Dutch device for keeping your pants up.
                    "Calondia", "Campor",
                    "Capelle",        // The city I lived in while programming this game
                    "Carzon",
                    "Castor",        // A Greek demi-god
                    "Cestus", "Cheron",
                    "Courteney",    // After Courteney Cox…
                    "Daled", "Damast", "Davlos", "Deneb", "Deneva", "Devidia", "Draylon", "Drema", "Endor",
                    "Esmee",        // One of the witches in Pratchett's Discworld
                    "Exo",
                    "Ferris",        // Iron
                    "Festen",        // A great Scandinavian movie
                    "Fourmi",        // An ant, in French
                    "Frolix",        // A solar system in one of Philip K. Dick's novels
                    "Gemulon",
                    "Guinifer",        // One way of writing the name of king Arthur's wife
                    "Hades",        // The underworld
                    "Hamlet",        // From Shakespeare
                    "Helena",        // Of Troy
                    "Hulst",        // A Dutch plant
                    "Iodine",        // An element
                    "Iralius",
                    "Janus",        // A seldom encountered Dutch boy's name
                    "Japori", "Jarada",
                    "Jason",        // A Greek hero
                    "Kaylon", "Khefka",
                    "Kira",            // My dog's name
                    "Klaatu",        // From a classic SF movie
                    "Klaestron",
                    "Korma",        // An Indian sauce
                    "Kravat",        // Interesting spelling of the French word for "tie"
                    "Krios",
                    "Laertes",        // A king in a Greek tragedy
                    "Largo",
                    "Lave",            // The starting system in Elite
                    "Ligon",
                    "Lowry",        // The name of the "hero" in Terry Gilliam's "Brazil"
                    "Magrat",        // The second of the witches in Pratchett's Discworld
                    "Malcoria", "Melina",
                    "Mentar",        // The Psilon home system in Master of Orion
                    "Merik", "Mintaka",
                    "Montor",        // A city in Ultima III and Ultima VII part 2
                    "Mordan",
                    "Myrthe",        // The name of my daughter
                    "Nelvana",
                    "Nix",            // An interesting spelling of a word meaning "nothing" in Dutch
                    "Nyle",            // An interesting spelling of the great river
                    "Odet",
                    "Og",            // The last of the witches in Pratchett's Discworld
                    "Omega",        // The end of it all
                    "Omphalos",        // Greek for navel
                    "Orias",
                    "Othello",        // From Shakespeare
                    "Parade",        // This word means the same in Dutch and in English
                    "Penthara",
                    "Picard",        // The enigmatic captain from ST:TNG
                    "Pollux",        // Brother of Castor
                    "Quator", "Rakhar",
                    "Ran",            // A film by Akira Kurosawa
                    "Regulas", "Relva", "Rhymus", "Rochani",
                    "Rubicum",        // The river Ceasar crossed to get into Rome
                    "Rutia", "Sarpeidon", "Sefalla", "Seltrice", "Sigma",
                    "Sol",            // That's our own solar system
                    "Somari", "Stakoron", "Styris", "Talani", "Tamus",
                    "Tantalos",        // A king from a Greek tragedy
                    "Tanuga", "Tarchannen", "Terosa",
                    "Thera",        // A seldom encountered Dutch girl's name
                    "Titan",        // The largest moon of Jupiter
                    "Torin",        // A hero from Master of Magic
                    "Triacus", "Turkana", "Tyrus",
                    "Umberlee",        // A god from AD&D, which has a prominent role in Baldur's Gate
                    "Utopia",        // The ultimate goal
                    "Vadera", "Vagra", "Vandor", "Ventax", "Xenon",
                    "Xerxes",        // A Greek hero
                    "Yew",            // A city which is in almost all of the Ultima games
                    "Yojimbo",        // A film by Akira Kurosawa
                    "Zalkon",
                    "Zuul"            // From the first Ghostbusters movie
            };
            int size = planetNames.length;

            String remove(int index) {
                String out = planetNames[index];
                planetNames[index] = planetNames[size-- - 1];
                return out;
            }
        }

        PlanetList planetList = new PlanetList();
        int systemNum = 22 + RandomMethods.nextInt(5);
        this.systems = new ArrayList<>(systemNum);
        Set<SolarSystem> systems = new HashSet<>();
        for (int i = systemNum - 1; i >= 0; --i) {
            String name = ((char)('A' + RandomMethods.nextInt(26)))
                    + String.valueOf(RandomMethods.nextInt(10))
                    + String.valueOf(RandomMethods.nextInt(10))
                    + String.valueOf(RandomMethods.nextInt(10))
                    + String.valueOf(RandomMethods.nextInt(10))
                    + "-"
                    + ((char)('A' + RandomMethods.nextInt(26)));

            SolarSystem curr;
            boolean added;
            do {
                curr = new SolarSystem(name);
                added = systems.add(curr);
            } while (!added);
            int planetNum = Math.min(planetList.size - i,
                    1 + RandomMethods.nextInt(Planet.MAX_RADIUS - Planet.MIN_RADIUS));
            // leave enough planet names for at least one per remaining system
            String[] planets = new String[planetNum];
            for (int j = 0; j < planets.length; ++j) {
                planets[j] = planetList.remove(RandomMethods.nextInt(planetList.size));
            }
            curr.startSystem(planets);
            this.systems.add(curr);
        }
    }

    /**
     * get random system
     * @return system
     */
    @Exclude
    public SolarSystem getRandomSystem() {
        int systemNum = RandomMethods.nextInt(systems.size());
        int i = 0;
        for (SolarSystem s : systems) {
            if (i == systemNum) {
                return s;
            }
            ++i;
        }
        return systems.iterator().next();
    }

    /**
     * finds system based on name
     * @param systemName string
     * @return the found solarsystem
     */
    public SolarSystem findSystem(String systemName) {
        for (SolarSystem s : systems) {
            if (s.getName().equals(systemName)) {
                return s;
            }
        }
        return null;
    }

    /**
     * printed thing
     */
    public void logUniverse() {
        Log.d("System number", String.valueOf(systems.size()));
        for (SolarSystem s : systems) {
            s.logSystem();
        }
    }

    /**
     *
     * @return systems
     */
    public List<SolarSystem> getSystems() {
        return systems;
    }

    /**
     * setter
     * @param systems list<SolarSystem></SolarSystem>
     */
    public void setSystems(List<SolarSystem> systems) {
        this.systems = systems;
    }
}
