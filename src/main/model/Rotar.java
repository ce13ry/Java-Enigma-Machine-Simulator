package model;

import org.json.JSONObject;

// represents a rotar with initial position
public class Rotar {

    private String[] setting;
    private int settingNum;
    private int rotationPosition;
    private int initialPosition;

    static final String[] SETTING1 = ("p7JzgSxbAK0?D8uYReE)H3tl2vkZB,#o!FX5n(sIG9*COTi1qNU rMhWd4.yLmaPfcQ6wVj")
            .split("");
    static final String[] SETTING2 = ("tN7jQr6ObyGpxYU,E!f#gM2F Tn5VL(DXv)*1?leJ3z.AdKCk0SsuWiIBaZ4hH8qPcoR9wm")
            .split("");
    static final String[] SETTING3 = ("IbnaRt3sHu)Mk6j.ZeE2xTyFr 1?DNYziLUdqOG!VB74pSXc9Co,Qm(A#50hvPlJw8*KWgf")
            .split("");
    static final String[] SETTING4 = ("rPo5sk*DneQdM3y,CLlz!RW19xi(EVvh0uSKBU#F.26Xwb?GTJYpjq7AafOtH)cm4NI gZ8")
            .split("");
    static final String[] SETTING5 = ("ZzaYwU5ndNIl2HtTxi#u.16vhq?g3pJfBESyO(V9oKG0Wc4F!LCQXb7smM)DrR,A *k8Pje")
            .split("");

    static final int NUMOFCHARS = Enigma.ALPHABET.length();

    // Effect: Creates a rotar with the given setting (out of 5 possible settings)
    // and sets canRotate to false
    // Requires: setting is an integer between 1 and 5
    @SuppressWarnings("methodlength")
    public Rotar(int setting, int initialPosition) {
        this.setting = new String[NUMOFCHARS];
        switch (setting) {
            case 1:
                copy(this.setting, SETTING1);
                settingNum = 1;
                break;
            case 2:
                copy(this.setting, SETTING2);
                settingNum = 2;
                break;
            case 3:
                copy(this.setting, SETTING3);
                settingNum = 3;
                break;
            case 4:
                copy(this.setting, SETTING4);
                settingNum = 4;
                break;
            case 5:
                copy(this.setting, SETTING5);
                settingNum = 5;
                break;
            default:
                break;
        }
        rotationPosition = 0;
        this.initialPosition = initialPosition;
        for (int i = 0; i < initialPosition; i++) {
            rotate();
        }
    }

    // Effect: Resets the rotar to its default setting
    // Modifies: this
    // Requires: r is a valid rotar
    public static void reset(Rotar r) {
        int i = r.getSettingNum();
        switch (i) {
            case 1:
                copy(r.setting, SETTING1);
                break;
            case 2:
                copy(r.setting, SETTING2);
                break;
            case 3:
                copy(r.setting, SETTING3);
                break;
            case 4:
                copy(r.setting, SETTING4);
                break;
            default:
                copy(r.setting, SETTING5);
                break;
        }
        r.rotationPosition = 0;
        for (int j = 0; j < r.initialPosition; j++) {
            r.rotate();
        }
    }

    // Effect: copies default SETTING to setting
    // Modifies: this
    public static void copy(String[] setting, String[] defaultSetting) {
        for (int i = 0; i < NUMOFCHARS; i++) {
            setting[i] = defaultSetting[i];
        }
    }

    // Effect: Rotates the rotar by one position
    // Modifies: this
    public void rotate() {
        String first = setting[0];
        for (int i = 0; i < setting.length - 1; i++) {
            setting[i] = setting[i + 1];
        }
        setting[setting.length - 1] = first;
        rotationPosition++;
        if (rotationPosition > NUMOFCHARS) {
            rotationPosition = 0;
        }
    }

    public int getSettingNum() {
        return this.settingNum;
    }

    public String[] getSetting() {
        return this.setting;
    }

    public String getLetter(int i) {
        return setting[i];
    }

    public int getRotatePosition() {
        return this.rotationPosition;
    }

    public int getInitialPosition() {
        return this.initialPosition;
    }

    public static int getNumOfChars() {
        return NUMOFCHARS;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("i", settingNum);
        json.put("initialPosition", initialPosition);
        return json;
    }
}
