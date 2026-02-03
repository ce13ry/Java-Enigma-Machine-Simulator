// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;
import persistence.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Enigma enigma = new Enigma();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyenigma() {
        try {
            Enigma enigma = new Enigma();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(enigma);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            enigma = reader.read();
            assertEquals(0, enigma.getRotors().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Enigma enigma = new Enigma();
            enigma.addSetting(3, 14);
            enigma.addSetting(2, 71);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(enigma);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            enigma = reader.read();
            List<Rotor> rotors = enigma.getRotors();
            assertEquals(2, rotors.size());
            checkRotor(3, 14, rotors.get(0));
            checkRotor(2, 71, rotors.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
