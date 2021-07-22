package at.phisch.googlelocationconverter.logic;

import at.phisch.googlelocationconverter.dto.Location;
import at.phisch.googlelocationconverter.json.NominatimOSMAddress;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Locale;

public class NominatimOSMClient {

    //https://nominatim.openstreetmap.org/ui/search.html?q=47.0068016%2C16.205074
    //https://nominatim.openstreetmap.org/search.php?q=47.0068016%2C16.205074&format=jsonv2

    public static void fillIntoLocation(Location location) throws IOException, InterruptedException {

        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(
                URI.create(String.format(Locale.US,"https://nominatim.openstreetmap.org/search.php?q=%f,%f&format=jsonv2", location.getLatitude(), location.getLongitude())))
                .header("accept", "application/json")
                .build();

        // use the client to send the request
        var response = client.send(request, new JsonBodyHandler<>(NominatimOSMAddress[].class));

        NominatimOSMAddress[] nominatimOSMData = response.body().get();
        if(nominatimOSMData.length > 0) {
            location.setDisplayInformation(nominatimOSMData[0].getDisplay_name());
        }

    }

}
