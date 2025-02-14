package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RotarTest {

    Rotar rotar;

    @BeforeEach
    void runBefore() {
        rotar = new Rotar(1);
    }

    @Test
    void setting1Test() {
        rotar = new Rotar(1);
        assertEquals(rotar.getSettingNum(), 1);

        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], Rotar.SETTING1[i]);     
        }
    }

    @Test
    void setting2Test() {
        rotar = new Rotar(2);
        assertEquals(rotar.getSettingNum(), 2);

        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], Rotar.SETTING2[i]);     
        }
    }

    @Test
    void setting3Test() {
        rotar = new Rotar(3);
        assertEquals(rotar.getSettingNum(), 3);

        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], Rotar.SETTING3[i]);     
        }
    }

    @Test
    void setting4Test() {
        rotar = new Rotar(4);
        assertEquals(rotar.getSettingNum(), 4);

        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], Rotar.SETTING4[i]);     
        }
    }

    @Test
    void setting5Test() {
        rotar = new Rotar(5);
        assertEquals(rotar.getSettingNum(), 5);
        
        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], Rotar.SETTING5[i]);     
        }
    }

    @Test
    void wrongSettingTest() {
        rotar = new Rotar(6);
        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], null);     
        }
    }

    @Test
    void rotateOnceTest() {
        rotar.rotate();       
        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], "7JzgSxbAK0?D8uYReEH3tl2vkZB,o!FX5nsIG9COTi1qNU rMhWd4.yLmaPfcQ6wVjp".split("")[i]);     
        }
    }

    @Test
    void rotateMultipleTest() {
        rotar.rotate();
        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], "7JzgSxbAK0?D8uYReEH3tl2vkZB,o!FX5nsIG9COTi1qNU rMhWd4.yLmaPfcQ6wVjp".split("")[i]);     
        }

        rotar.rotate();
        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], "JzgSxbAK0?D8uYReEH3tl2vkZB,o!FX5nsIG9COTi1qNU rMhWd4.yLmaPfcQ6wVjp7".split("")[i]);     
        }
    }

    @Test
    void resetTest() {
        rotar.rotate();
        Rotar.reset(rotar);
        
        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], Rotar.SETTING1[i]);     
        }
    }

    @Test
    void copyOnceTest() {
        Rotar.copy(rotar.getSetting(), Rotar.SETTING2);

        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], Rotar.SETTING2[i]);     
        }
    }

    @Test
    void copyMultipleTest() {
        Rotar.copy(rotar.getSetting(), Rotar.SETTING2);

        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], Rotar.SETTING2[i]);     
        }

        Rotar.copy(rotar.getSetting(), Rotar.SETTING1);
        for (int i = 0; i < rotar.getSetting().length; i++) {
            assertEquals(rotar.getSetting()[i], Rotar.SETTING1[i]);     
        }
    }


}
