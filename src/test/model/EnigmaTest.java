package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnigmaTest {

    Enigma enigma;

    @BeforeEach
    void runBefore() {
        enigma = new Enigma();
    }

    @Test
    void enigmaTest(){
        assertEquals(enigma.rotars.size(), 0);
    }

    @Test
    void addSetting1Test(){
        enigma.addSetting1();
        assertEquals(enigma.rotars.size(), 1);
        assertEquals(enigma.rotars.get(0).getSettingNum(), 1);
    }

    @Test
    void addSetting2Test(){
        enigma.addSetting2();
        assertEquals(enigma.rotars.size(), 1);
        assertEquals(enigma.rotars.get(0).getSettingNum(), 2);
    }

    @Test
    void addSetting3Test(){
        enigma.addSetting3();
        assertEquals(enigma.rotars.size(), 1);
        assertEquals(enigma.rotars.get(0).getSettingNum(), 3);
    }

    @Test
    void addSetting4Test(){
        enigma.addSetting4();
        assertEquals(enigma.rotars.size(), 1);
        assertEquals(enigma.rotars.get(0).getSettingNum(), 4);
    }

    @Test
    void addSetting5Test(){
        enigma.addSetting5();
        assertEquals(enigma.rotars.size(), 1);
        assertEquals(enigma.rotars.get(0).getSettingNum(), 5);
    }

    @Test
    void addMultipleTest(){
        enigma.addSetting1();
        enigma.addSetting2();
        enigma.addSetting3();
        enigma.addSetting4();
        enigma.addSetting5();
        assertEquals(enigma.rotars.size(), 5);
        assertEquals(enigma.rotars.get(0).getSettingNum(), 1);
        assertEquals(enigma.rotars.get(1).getSettingNum(), 2);
        assertEquals(enigma.rotars.get(2).getSettingNum(), 3);
        assertEquals(enigma.rotars.get(3).getSettingNum(), 4);
        assertEquals(enigma.rotars.get(4).getSettingNum(), 5);
    }

    @Test
    void addMultipleSameTest(){
        enigma.addSetting1();
        enigma.addSetting1();
        enigma.addSetting1();
        enigma.addSetting1();
        enigma.addSetting1();
        assertEquals(enigma.rotars.size(), 5);
        assertEquals(enigma.rotars.get(0).getSettingNum(), 1);
        assertEquals(enigma.rotars.get(1).getSettingNum(), 1);
        assertEquals(enigma.rotars.get(2).getSettingNum(), 1);
        assertEquals(enigma.rotars.get(3).getSettingNum(), 1);
        assertEquals(enigma.rotars.get(4).getSettingNum(), 1);
    }

    @Test
    void remvoeOneTest(){
        enigma.addSetting1();
        enigma.remove(1);
        assertEquals(enigma.rotars.size(), 0);
    }

    @Test
    void removeMultipleTest(){
        enigma.addSetting1();
        enigma.addSetting2();
        enigma.addSetting3();
        enigma.addSetting4();
        enigma.addSetting5();
        enigma.remove(1);
        enigma.remove(2);
        enigma.remove(3);
        enigma.remove(4);
        enigma.remove(5);
        assertEquals(enigma.rotars.size(), 0);
    }

    @Test
    void cipherTestOneSettingOneLetter(){
        enigma.addSetting1();
        assertEquals(enigma.cipher("a"), "m");
        assertEquals(enigma.cipher("m"), "a");

        enigma.remove(1);
        enigma.addSetting2();
        assertEquals(enigma.cipher("a"), "o");
        assertEquals(enigma.cipher("o"), "a");

        enigma.remove(1);
        enigma.addSetting3();
        assertEquals(enigma.cipher("a"), "8");
        assertEquals(enigma.cipher("8"), "a");

        enigma.remove(1);
        enigma.addSetting4();
        assertEquals(enigma.cipher("a"), "u");
        assertEquals(enigma.cipher("u"), "a");

        enigma.remove(1);
        enigma.addSetting5();
        assertEquals(enigma.cipher("a"), "s");
        assertEquals(enigma.cipher("s"), "a");
    }

    @Test
    void multipleTestOneLetter(){
        enigma.addSetting1();
        enigma.addSetting2();

        assertEquals(enigma.cipher("a"), "k");
        assertEquals(enigma.cipher("k"), "a");

        enigma.addSetting3();

        assertEquals(enigma.cipher("a"), "9");
        assertEquals(enigma.cipher("9"), "a");

        enigma.addSetting4();

        assertEquals(enigma.cipher("a"), ".");
        assertEquals(enigma.cipher("."), "a");

        enigma.addSetting5();
        assertEquals(enigma.cipher("a"), "e");
        assertEquals(enigma.cipher("e"), "a");
    }

    @Test
    void singleSettingMultipleLetters(){
        enigma.addSetting1();
        assertEquals(enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz"), 
        "mcnsw460wuxnk77bb94q5upae6o.2c2hmsity1.a61 2tqbbhhd.w");

        assertEquals(enigma.cipher("mcnsw460wuxnk77bb94q5upae6o.2c2hmsity1.a61 2tqbbhhd.w"),
        "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    void multipleSettingMultipleLetters(){
        enigma.addSetting1();
        assertEquals(enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz"), 
        "mcnsw460wuxnk77bb94q5upae6o.2c2hmsity1.a61 2tqbbhhd.w");

        assertEquals(enigma.cipher("mcnsw460wuxnk77bb94q5upae6o.2c2hmsity1.a61 2tqbbhhd.w"),
        "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz");

        enigma.addSetting2();
        assertEquals(enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz"), 
        "kujzf7if,rrudf4t1nfspvk5xs,535 jgq p4lb6z0ln4 qvim9wv");

        assertEquals(enigma.cipher("kujzf7if,rrudf4t1nfspvk5xs,535 jgq p4lb6z0ln4 qvim9wv"),
        "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz");

        enigma.addSetting3();
        assertEquals(enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz"), 
        "945rsov1we89vshbz v27xuxo.4y196w5d.,xyu40sn29ycqg vs9");

        assertEquals(enigma.cipher("945rsov1we89vshbz v27xuxo.4y196w5d.,xyu40sn29ycqg vs9"),
        "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz");

        enigma.addSetting4();
        assertEquals(enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz"), 
        ".dai7s7okh5qqvk.pj 14,ocfue7ud ehejgobyedoxe4o54mhh1,");

        assertEquals(enigma.cipher(".dai7s7okh5qqvk.pj 14,ocfue7ud ehejgobyedoxe4o54mhh1,"),
        "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz");

        enigma.addSetting5();
        assertEquals(enigma.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz"), 
        "e..ijvz.ug3sz9cc gjwal4x7uvj6w724keeop07s70lyq.nf,j84");

        assertEquals(enigma.cipher("e..ijvz.ug3sz9cc gjwal4x7uvj6w724keeop07s70lyq.nf,j84"),
        "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz");
    }
}
