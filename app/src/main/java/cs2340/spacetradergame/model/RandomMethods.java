package cs2340.spacetradergame.model;

import java.util.Random;

public class RandomMethods {
    private static Random random = new Random();

    /**
     * Returns an integer between 0 and max, inclusive, with a gaussian distribution
     * @param max the maximum number
     * @return the random number
     */
    public static int gaussian(int max) {
        int i = (int) (((random.nextGaussian() + 3) / 6) * (max + 1));
        if (i < 0) {
            return 0;
        } else if (i > max) {
            return max;
        } else {
            return i;
        }
    }

    /**
     * Returns an integer between 0 and max, exclusive
     * @param max the upper bound
     * @return the random number
     */
    public static int nextInt(int max) {
        return random.nextInt(max);
    }
}
