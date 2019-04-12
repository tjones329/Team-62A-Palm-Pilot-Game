package cs2340.spacetradergame.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class CargoHoldTest {

    private CargoHold cg;
    @Test
    public void addZeros() {
        cg = new CargoHold(10);
        int[] testArray = new int[10];
        List<Integer> addItems = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
        cg.addCargo(addItems);
        assertArrayEquals(testArray, cg.getItems());
        assertEquals(0, cg.getCurrentFilled());
    }

    @Test
    public void addRegular() {
        cg = new CargoHold(10);
        int[] testArray = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> addItems = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        cg.addCargo(addItems);
        assertArrayEquals(testArray, cg.getItems());
        assertEquals(55, cg.getCurrentFilled());
    }

    @Test(expected = NoSuchElementException.class)
    public void addNull() {
        cg = new CargoHold(10);
        cg.addCargo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNegatives() {
        cg = new CargoHold(10);
        List<Integer> addItems = new ArrayList<>(Arrays.asList(-1,0,0,0,0,0,0,0,0,0));
        cg.addCargo(addItems);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidSize() {
        cg = new CargoHold(10);
        List<Integer> addItems = new ArrayList<>(Arrays.asList(0));
        cg.addCargo(addItems);
    }
}