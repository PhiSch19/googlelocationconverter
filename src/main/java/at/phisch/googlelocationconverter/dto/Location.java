package at.phisch.googlelocationconverter.dto;

import java.time.Instant;

public class Location {

    private Instant timestamp;
    private double latitude;
    private double longitude;
    private String displayInformation;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDisplayInformation() {
        return displayInformation;
    }

    public void setDisplayInformation(String displayInformation) {
        this.displayInformation = displayInformation;
    }

    @Override
    public String toString() {
        return "Location{" +
                "timestamp=" + timestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", displayInformation='" + displayInformation + '\'' +
                '}';
    }
}
