package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RotorTest {

    Rotor rotor;

    @BeforeEach
    void runBefore() {
        rotor = new Rotor(1, 0);
    }

    @Test
    void setting1Test() {
        rotor = new Rotor(1, 0);
        assertEquals(rotor.getSettingNum(), 1);

        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], rotor.SETTING1[i]);     
        }
    }

    @Test
    void setting2Test() {
        rotor = new Rotor(2, 0);
        assertEquals(rotor.getSettingNum(), 2);

        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], rotor.SETTING2[i]);     
        }
    }

    @Test
    void setting3Test() {
        rotor = new Rotor(3, 0);
        assertEquals(rotor.getSettingNum(), 3);

        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], rotor.SETTING3[i]);     
        }
    }

    @Test
    void setting4Test() {
        rotor = new Rotor(4, 0);
        assertEquals(rotor.getSettingNum(), 4);

        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], rotor.SETTING4[i]);     
        }
    }

    @Test
    void setting5Test() {
        rotor = new Rotor(5, 0);
        assertEquals(rotor.getSettingNum(), 5);
        
        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], rotor.SETTING5[i]);     
        }
    }

    @Test
    void wrongSettingTest() {
        rotor = new Rotor(6, 0);
        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], null);     
        }
    }

    @Test
    void rotateOnceTest() {
        rotor.rotate();       
        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], "7JzgSxbAK0?D8uYReEH3tl2vkZB,o!FX5nsIG9COTi1qNU rMhWd4.yLmaPfcQ6wVjp".split("")[i]);     
        }
    }

    @Test
    void rotateMultipleTest() {
        rotor.rotate();
        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], "7JzgSxbAK0?D8uYReEH3tl2vkZB,o!FX5nsIG9COTi1qNU rMhWd4.yLmaPfcQ6wVjp".split("")[i]);     
        }

        rotor.rotate();
        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], "JzgSxbAK0?D8uYReEH3tl2vkZB,o!FX5nsIG9COTi1qNU rMhWd4.yLmaPfcQ6wVjp7".split("")[i]);     
        }
    }

    @Test
    void resetTest() {
        rotor.rotate();
        rotor.reset(rotor);
        
        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], rotor.SETTING1[i]);     
        }
    }

    @Test
    void copyOnceTest() {
        rotor.copy(rotor.getSetting(), rotor.SETTING2);

        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], rotor.SETTING2[i]);     
        }
    }

    @Test
    void copyMultipleTest() {
        rotor.copy(rotor.getSetting(), rotor.SETTING2);

        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], rotor.SETTING2[i]);     
        }

        rotor.copy(rotor.getSetting(), rotor.SETTING1);
        for (int i = 0; i < rotor.getSetting().length; i++) {
            assertEquals(rotor.getSetting()[i], rotor.SETTING1[i]);     
        }
    }


}
