package cs2340.spacetradergame.entity;

import android.util.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Universe {


    public static final int universeWidth = 150;
    public static final int universeHeight = 100;

    private Set<SolarSystem> systems = new HashSet<>();

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
        Random random = new Random();
        int systemNum = 22 + random.nextInt(5);
        PlanetList planetList = new PlanetList();
        for (int i = systemNum - 1; i >= 0; --i) {
            String name = ((char)('A' + random.nextInt(26)))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + "-"
                    + ((char)('A' + random.nextInt(26)));

            SolarSystem curr;
            boolean added;
            do {
                curr = new SolarSystem(name, random);
                added = systems.add(curr);
            } while (!added);
            int planetNum = Math.min(1 + random.nextInt(8), planetList.size - i); // leave enough planet names for at least one per remaining system
            String[] planets = new String[planetNum];
            for (int j = 0; j < planets.length; ++j) {
                planets[j] = planetList.remove(random.nextInt(planetList.size));
            }
            curr.startSystem(planets, random);
        }
    }

    public void logUniverse() {
        Log.d("System number", String.valueOf(systems.size()));
        for (SolarSystem s : systems) {
            s.logSystem();
        }
    }
}
