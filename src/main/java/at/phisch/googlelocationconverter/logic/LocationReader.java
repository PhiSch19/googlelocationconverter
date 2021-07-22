package at.phisch.googlelocationconverter.logic;

import at.phisch.googlelocationconverter.dto.Location;
import at.phisch.googlelocationconverter.json.JsonLocation;
import at.phisch.googlelocationconverter.json.JsonLocations;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class LocationReader {

    private final String file;

    public LocationReader(String file) {
        this.file = file;
    }

    public List<Location> read() throws IOException {
        JsonLocations locations;
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            locations = gson.fromJson(reader, JsonLocations.class);
        }
        return locations.getLocations().stream()
                .limit(20)
                .map(JsonLocation.toLocationConverter)
                .collect(Collectors.toList());
    }

}
