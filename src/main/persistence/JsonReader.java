// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Enigma;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads enigma from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads enigma from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Enigma read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEnigma(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Enigma parseEnigma(JSONObject jsonObject) {
        Enigma enigma = new Enigma();
        addRotors(enigma, jsonObject);
        return enigma;
    }

    // MODIFIES: enigma
    // EFFECTS: parses rotors from JSON object and adds them to enigma
    private void addRotors(Enigma enigma, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("rotors");
        for (Object json : jsonArray) {
            JSONObject nextRotor = (JSONObject) json;
            addRotor(enigma, nextRotor);
        }
    }

    // MODIFIES: enigma
    // EFFECTS: parses rotor from JSON object and adds it to enigma
    private void addRotor(Enigma enigma, JSONObject jsonObject) {
        int i = jsonObject.getInt("i");
        int initialPosition = jsonObject.getInt("initialPosition");
        enigma.addSetting(i, initialPosition);
    }

}
