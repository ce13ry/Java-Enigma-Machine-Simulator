package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        assertEquals(enigma.getRotors().size(), 0);
    }

    @Test
    void addSetting1Test() {
        enigma.addSetting(1, 0);
        assertEquals(enigma.getRotors().size(), 1);
        assertEquals(enigma.getRotors().get(0).getSettingNum(), 1);
    }

    @Test
    void addSetting2Test() {
        enigma.addSetting(2, 0);
        assertEquals(enigma.getRotors().size(), 1);
        assertEquals(enigma.getRotors().get(0).getSettingNum(), 2);
    }

    @Test
    void addSetting3Test() {
        enigma.addSetting(3, 0);
        assertEquals(enigma.getRotors().size(), 1);
        assertEquals(enigma.getRotors().get(0).getSettingNum(), 3);
    }

    @Test
    void addSetting4Test() {
        enigma.addSetting(4, 0);
        assertEquals(enigma.getRotors().size(), 1);
        assertEquals(enigma.getRotors().get(0).getSettingNum(), 4);
    }

    @Test
    void addSetting5Test() {
        enigma.addSetting(5, 0);
        assertEquals(enigma.getRotors().size(), 1);
        assertEquals(enigma.getRotors().get(0).getSettingNum(), 5);
    }

    @Test
    void addMultipleTest() {
        enigma.addSetting(1, 0);
        enigma.addSetting(2, 0);
        enigma.addSetting(3, 0);
        enigma.addSetting(4, 0);
        enigma.addSetting(5, 0);
        assertEquals(enigma.getRotors().size(), 5);
        assertEquals(enigma.getRotors().get(0).getSettingNum(), 1);
        assertEquals(enigma.getRotors().get(1).getSettingNum(), 2);
        assertEquals(enigma.getRotors().get(2).getSettingNum(), 3);
        assertEquals(enigma.getRotors().get(3).getSettingNum(), 4);
        assertEquals(enigma.getRotors().get(4).getSettingNum(), 5);
    }

    @Test
    void addMultipleSameTest() {
        enigma.addSetting(1, 0);
        enigma.addSetting(1, 0);
        enigma.addSetting(1, 0);
        enigma.addSetting(1, 0);
        enigma.addSetting(1, 0);
        assertEquals(enigma.getRotors().size(), 5);
        assertEquals(enigma.getRotors().get(0).getSettingNum(), 1);
        assertEquals(enigma.getRotors().get(1).getSettingNum(), 1);
        assertEquals(enigma.getRotors().get(2).getSettingNum(), 1);
        assertEquals(enigma.getRotors().get(3).getSettingNum(), 1);
        assertEquals(enigma.getRotors().get(4).getSettingNum(), 1);
    }

    @Test
    void remvoeOneTest() {
        enigma.addSetting(1, 0);
        enigma.remove(1);
        assertEquals(enigma.getRotors().size(), 0);
    }

    @Test
    void removeMultipleTest() { 
        enigma.addSetting(1, 0);
        enigma.addSetting(2, 0);
        enigma.addSetting(3, 0);
        enigma.addSetting(4, 0);
        enigma.addSetting(5, 0);
        enigma.remove(1);
        enigma.remove(1);
        enigma.remove(1);
        enigma.remove(1);
        enigma.remove(1);
        assertEquals(enigma.getRotors().size(), 0);
    }

    @Test
    void cipherTestOneSettingOneLetter() {
        enigma.addSetting(1, 0);
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.remove(1);
        enigma.addSetting(2, 0);
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.remove(1);
        enigma.addSetting(3, 0);

        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.remove(1);
        enigma.addSetting(4, 0);
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.remove(1);
        enigma.addSetting(5, 0);
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");
    }

    @Test
    void multipleTestOneLetter() {
        enigma.addSetting(1, 0);
        enigma.addSetting(2, 0);

        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.addSetting(3, 0);

        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.addSetting(4, 0);

        assertEquals(enigma.cipher(enigma.cipher("a")), "a");

        enigma.addSetting(5, 0);
        assertEquals(enigma.cipher(enigma.cipher("a")), "a");
    }

    @Test
    void singleSettingMultipleLetters() {
        enigma.addSetting(1, 0);
        assertEquals(enigma.cipher(enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");
    }

    @Test
    void multipleSettingMultipleLetters() {
        enigma.addSetting(1, 0);
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");

        enigma.addSetting(2, 0);
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");

        enigma.addSetting(3, 0);
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");

        enigma.addSetting(4, 0);
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");

        enigma.addSetting(5, 0);
        assertEquals(enigma.cipher(enigma.cipher("ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!")), 
                                        "ABCabcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");
    }

    @Test
    void invalidInput(){
        enigma.addSetting(1, 0);
        assertEquals(-1, enigma.getLetterPosition("^" , enigma.getRotors().get(0)));
        assertEquals(enigma.cipher("~"), "**INVALID CHARACTER**");
    }

    @Test
    void differentInitial(){
        enigma.addSetting(1, 0);
        String s = enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");
        assertEquals(0, enigma.getRotors().get(0).getInitialPosition());
        enigma.remove(1);

        enigma.addSetting(1, 1);
        String s2 = enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz0123456789.,?!");
        assertEquals(1, enigma.getRotors().get(0).getInitialPosition());
        assertFalse(s.equals(s2));
    }
}
