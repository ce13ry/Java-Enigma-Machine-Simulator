package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnigmaTest {

    Enigma enigma;

    @BeforeEach
    void runBefore() {
        enigma = new Enigma();
    }

    @Test
    void enigmaTest() {
        assertEquals(enigma.getRotars().size(), 0);
    }

    @Test
    void addSetting1Test() {
        enigma.addSetting1();
        assertEquals(enigma.getRotars().size(), 1);
        assertEquals(enigma.getRotars().get(0).getSettingNum(), 1);
    }

    @Test
    void addSetting2Test() {
        enigma.addSetting2();
        assertEquals(enigma.getRotars().size(), 1);
        assertEquals(enigma.getRotars().get(0).getSettingNum(), 2);
    }

    @Test
    void addSetting3Test() {
        enigma.addSetting3();
        assertEquals(enigma.getRotars().size(), 1);
        assertEquals(enigma.getRotars().get(0).getSettingNum(), 3);
    }

    @Test
    void addSetting4Test() {
        enigma.addSetting4();
        assertEquals(enigma.getRotars().size(), 1);
        assertEquals(enigma.getRotars().get(0).getSettingNum(), 4);
    }

    @Test
    void addSetting5Test() {
        enigma.addSetting5();
        assertEquals(enigma.getRotars().size(), 1);
        assertEquals(enigma.getRotars().get(0).getSettingNum(), 5);
    }

    @Test
    void addMultipleTest() {
        enigma.addSetting1();
        enigma.addSetting2();
        enigma.addSetting3();
        enigma.addSetting4();
        enigma.addSetting5();
        assertEquals(enigma.getRotars().size(), 5);
        assertEquals(enigma.getRotars().get(0).getSettingNum(), 1);
        assertEquals(enigma.getRotars().get(1).getSettingNum(), 2);
        assertEquals(enigma.getRotars().get(2).getSettingNum(), 3);
        assertEquals(enigma.getRotars().get(3).getSettingNum(), 4);
        assertEquals(enigma.getRotars().get(4).getSettingNum(), 5);
    }

    @Test
    void addMultipleSameTest() {
        enigma.addSetting1();
        enigma.addSetting1();
        enigma.addSetting1();
        enigma.addSetting1();
        enigma.addSetting1();
        assertEquals(enigma.getRotars().size(), 5);
        assertEquals(enigma.getRotars().get(0).getSettingNum(), 1);
        assertEquals(enigma.getRotars().get(1).getSettingNum(), 1);
        assertEquals(enigma.getRotars().get(2).getSettingNum(), 1);
        assertEquals(enigma.getRotars().get(3).getSettingNum(), 1);
        assertEquals(enigma.getRotars().get(4).getSettingNum(), 1);
    }

    @Test
    void remvoeOneTest() {
        enigma.addSetting1();
        enigma.remove(1);
        assertEquals(enigma.getRotars().size(), 0);
    }

    @Test
    void removeMultipleTest() { 
        enigma.addSetting1();
        enigma.addSetting2();
        enigma.addSetting3();
        enigma.addSetting4();
        enigma.addSetting5();
        enigma.remove(1);
        enigma.remove(1);
        enigma.remove(1);
        enigma.remove(1);
        enigma.remove(1);
        assertEquals(enigma.getRotars().size(), 0);
    }

    @Test
    void cipherTestOneSettingOneLetter() {
        enigma.addSetting1();
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.remove(1);
        enigma.addSetting2();
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.remove(1);
        enigma.addSetting3();
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.remove(1);
        enigma.addSetting4();
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.remove(1);
        enigma.addSetting5();
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");
    }

    @Test
    void multipleTestOneLetter() {
        enigma.addSetting1();
        enigma.addSetting2();

        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.addSetting3();

        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.addSetting4();

        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.addSetting5();
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");
    }

    @Test
    void singleSettingMultipleLetters() {
        enigma.addSetting1();
        assertEquals(enigma.cipher(enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");
    }

    @Test
    void multipleSettingMultipleLetters() {
        enigma.addSetting1();
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");

        enigma.addSetting2();
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");

        enigma.addSetting3();
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");

        enigma.addSetting4();
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");

        enigma.addSetting5();
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");
    }

    @Test
    void invalidInput(){
        enigma.addSetting1();
        assertEquals(-1, enigma.getLetterPosition("^" , enigma.getRotars().get(0)));
        assertEquals(enigma.cipher("~"), "**INVALID CHARACTER**");
    }
}
