package ui;
import model.Enigma;

public class Main {
    public static void main(String[] args) throws Exception {
        Enigma kaviET = new Enigma();

        kaviET.addSetting3();
        kaviET.addSetting2();
        kaviET.addSetting3();

        System.out.println(kaviET.cipher("abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz"));
        System.out.println(kaviET.cipher(".u,quxyi0uq88h89sps6x8j, xm82p7yhe fw 22fkou99r hlt4o"));
    }
}
