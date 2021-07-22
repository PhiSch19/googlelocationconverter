package at.phisch.googlelocationconverter.ui;

import at.phisch.googlelocationconverter.dto.Location;
import at.phisch.googlelocationconverter.logic.LocationReader;
import at.phisch.googlelocationconverter.logic.NominatimOSMClient;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Location> locations = new LocationReader("D:\\GoogleTakeouts\\Standortverlauf.json").read();
        for (Location location : locations) {
            NominatimOSMClient.fillIntoLocation(location);
        }
        locations.forEach(System.out::println);
    }

}
