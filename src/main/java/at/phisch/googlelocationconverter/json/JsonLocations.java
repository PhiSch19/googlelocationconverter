package at.phisch.googlelocationconverter.json;

import java.util.ArrayList;
import java.util.List;

public class JsonLocations {

    private List<JsonLocation> locations = new ArrayList<>();

    public List<JsonLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<JsonLocation> locations) {
        this.locations = locations;
    }
}
