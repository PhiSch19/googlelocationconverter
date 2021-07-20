package at.phisch.googlelocationconverter.json;

import at.phisch.googlelocationconverter.dto.Location;

import java.time.Instant;
import java.util.function.Function;

public class JsonLocation {

    private long timestampMs;
    private String latitudeE7;
    private String longitudeE7;

    public static final Function<JsonLocation, Location> toLocationConverter = jsonLocation -> {
        Location location = new Location();

        location.setTimestamp(Instant.ofEpochMilli(jsonLocation.timestampMs));
        location.setLongitude(Double.parseDouble(jsonLocation.longitudeE7) / 10000000);
        location.setLatitude(Double.parseDouble(jsonLocation.latitudeE7) / 10000000);

        return location;
    };

    public long getTimestampMs() {
        return timestampMs;
    }

    public void setTimestampMs(long timestampMs) {
        this.timestampMs = timestampMs;
    }

    public String getLatitudeE7() {
        return latitudeE7;
    }

    public void setLatitudeE7(String latitudeE7) {
        this.latitudeE7 = latitudeE7;
    }

    public String getLongitudeE7() {
        return longitudeE7;
    }

    public void setLongitudeE7(String longitudeE7) {
        this.longitudeE7 = longitudeE7;
    }

    @Override
    public String toString() {
        return "JsonLocation{" +
                "timestampMs=" + timestampMs +
                ", latitudeE7='" + latitudeE7 + '\'' +
                ", longitudeE7='" + longitudeE7 + '\'' +
                '}';
    }
}
