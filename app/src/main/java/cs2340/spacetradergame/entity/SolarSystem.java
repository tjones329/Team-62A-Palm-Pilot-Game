package cs2340.spacetradergame.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cs2340.spacetradergame.model.Point;

public class SolarSystem {
// Many of these names are from Star Trek: The Next Generation, or are small changes
// to names of this series. A few have different origins.
public static final String[] planetNames = {
    "Acamar",
    "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
    "Aldea",
    "Andevian",
    "Antedi",
    "Balosnee",
    "Baratas",
    "Brax",			// One of the heroes in Master of Magic
    "Bretel",		// This is a Dutch device for keeping your pants up.
    "Calondia",
    "Campor",
    "Capelle",		// The city I lived in while programming this game
    "Carzon",
    "Castor",		// A Greek demi-god
    "Cestus",
    "Cheron",
    "Courteney",	// After Courteney Cox…
    "Daled",
    "Damast",
    "Davlos",
    "Deneb",
    "Deneva",
    "Devidia",
    "Draylon",
    "Drema",
    "Endor",
    "Esmee",		// One of the witches in Pratchett's Discworld
    "Exo",
    "Ferris",		// Iron
    "Festen",		// A great Scandinavian movie
    "Fourmi",		// An ant, in French
    "Frolix",		// A solar system in one of Philip K. Dick's novels
    "Gemulon",
    "Guinifer",		// One way of writing the name of king Arthur's wife
    "Hades",		// The underworld
    "Hamlet",		// From Shakespeare
    "Helena",		// Of Troy
    "Hulst",		// A Dutch plant
    "Iodine",		// An element
    "Iralius",
    "Janus",		// A seldom encountered Dutch boy's name
    "Japori",
    "Jarada",
    "Jason",		// A Greek hero
    "Kaylon",
    "Khefka",
    "Kira",			// My dog's name
    "Klaatu",		// From a classic SF movie
    "Klaestron",
    "Korma",		// An Indian sauce
    "Kravat",		// Interesting spelling of the French word for "tie"
    "Krios",
    "Laertes",		// A king in a Greek tragedy
    "Largo",
    "Lave",			// The starting system in Elite
    "Ligon",
    "Lowry",		// The name of the "hero" in Terry Gilliam's "Brazil"
    "Magrat",		// The second of the witches in Pratchett's Discworld
    "Malcoria",
    "Melina",
    "Mentar",		// The Psilon home system in Master of Orion
    "Merik",
    "Mintaka",
    "Montor",		// A city in Ultima III and Ultima VII part 2
    "Mordan",
    "Myrthe",		// The name of my daughter
    "Nelvana",
    "Nix",			// An interesting spelling of a word meaning "nothing" in Dutch
    "Nyle",			// An interesting spelling of the great river
    "Odet",
    "Og",			// The last of the witches in Pratchett's Discworld
    "Omega",		// The end of it all
    "Omphalos",		// Greek for navel
    "Orias",
    "Othello",		// From Shakespeare
    "Parade",		// This word means the same in Dutch and in English
    "Penthara",
    "Picard",		// The enigmatic captain from ST:TNG
    "Pollux",		// Brother of Castor
    "Quator",
    "Rakhar",
    "Ran",			// A film by Akira Kurosawa
    "Regulas",
    "Relva",
    "Rhymus",
    "Rochani",
    "Rubicum",		// The river Ceasar crossed to get into Rome
    "Rutia",
    "Sarpeidon",
    "Sefalla",
    "Seltrice",
    "Sigma",
    "Sol",			// That's our own solar system
    "Somari",
    "Stakoron",
    "Styris",
    "Talani",
    "Tamus",
    "Tantalos",		// A king from a Greek tragedy
    "Tanuga",
    "Tarchannen",
    "Terosa",
    "Thera",		// A seldom encountered Dutch girl's name
    "Titan",		// The largest moon of Jupiter
    "Torin",		// A hero from Master of Magic
    "Triacus",
    "Turkana",
    "Tyrus",
    "Umberlee",		// A god from AD&D, which has a prominent role in Baldur's Gate
    "Utopia",		// The ultimate goal
    "Vadera",
    "Vagra",
    "Vandor",
    "Ventax",
    "Xenon",
    "Xerxes",		// A Greek hero
    "Yew",			// A city which is in almost all of the Ultima games
    "Yojimbo",		// A film by Akira Kurosawa
    "Zalkon",
    "Zuul"			// From the first Ghostbusters movie
};
    enum TechLevel {
        PREAGRICULTURE, AGRICULTURE, MIDIEVAL, RENAISSANCE, EARLYINDUSTRIAL, INDUSTRIAL,
        POSTINDUSTRIAL, HITECH;
        public static TechLevel[] techLevels = TechLevel.values();
    }
    enum Resources {
        NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR, DESERT, LOTSOFWATER, RICHSOIL, POORSOIL,
        RICHFAUNA, LIFELESS, WEIRDMUSHROOMS, LOTSOFHERBS, ARTISTIC, WARLIKE;
        public static Resources[] resources = Resources.values();
    }
    enum Pirates {
        MINIMAL, FEW, SOME, MANY, SWARMS;
        public static Pirates[] pirates = Pirates.values();
    }
    enum Police {
        MINIMAL, FEW, SOME, MANY, SWARMS;
        public static Police[] police = Police.values();
    }
    enum Government {
        ANARCHY, TRIBAL, MONARCHY, DEMOCRACY, SOCIALIST, TOTALITARIAN;
        public static Government[] governments = Government.values();
    }

    private static List<String> planetList = Arrays.asList(planetNames);


    private String name;
    private Point pos;
    private TechLevel techLevel;
    private Resources resources;
    private Pirates pirates;
    private Police police;
    private Government government;
    private List<Planet> planets;

    public SolarSystem(String name) {
        this.name = name;
        Random random = new Random();
        pos = new Point(random.nextInt(Universe.universeWidth),
                random.nextInt(Universe.universeHeight));
    }

    public void startSystem() {
        Random random = new Random();
        techLevel = TechLevel.techLevels[(int) (random.nextGaussian() * TechLevel.techLevels.length)];
        int resourcesInt = random.nextInt(Resources.resources.length + 3);
        if (resourcesInt < 3) {
            resources = Resources.NOSPECIALRESOURCES;
        } else {
            resources = Resources.resources[resourcesInt - 3];
        }
        pirates = Pirates.pirates[(int) (random.nextGaussian() * Pirates.pirates.length)];
        police = Police.police[(int) (random.nextGaussian() * Police.police.length)];
        government = Government.governments[random.nextInt(Government.governments.length)];

        planets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Point getPos() {
        return pos;
    }

    @Override
    public int hashCode() {
        return pos.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SolarSystem) {
            SolarSystem s = (SolarSystem) obj;
            return name.equals(s.getName()) && pos.equals(s.getPos());
        } else {
            return false;
        }
    }
}
