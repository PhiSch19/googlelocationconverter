package at.phisch19.tools.googlelocationconverter;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class LocationConverter {

	@NonNull
	private final String path;
	@NonNull
	private final String sourceFileName;
	@Builder.Default
	private String resultFolder = "Converted";
	@Builder.Default
	private String resultFileName = "file";
	@Builder.Default
	private int numberOfLocationsIn1File = 1000;
	@Builder.Default
	private boolean sortByTimestampDesc = false;

	public void convert() throws IOException {
		JSONObject json = readJSON();
		writeJSON(json);
	}

	private JSONObject readJSON() throws IOException {
		JSONObject jsonObject = null;
		try (Reader reader = new InputStreamReader(new FileInputStream((path + sourceFileName + ".json")), "UTF-8")) {
			Gson gson = new Gson();
			jsonObject = gson.fromJson(reader, JSONObject.class);
		}
		return jsonObject;
	}

	private void writeJSON(JSONObject json) throws IOException {

		if (sortByTimestampDesc) {
			json.getLocations().sort((o1, o2) -> o1.getTimestampMs() < o2.getTimestampMs() ? 1 : -1);
		}

		AtomicInteger counter = new AtomicInteger();
		AtomicInteger fileNumber = new AtomicInteger();
		for (List<Location> locationList : json.getLocations().stream()
				.map(location -> location.createLocation())
				.collect(Collectors.groupingBy(it -> counter.getAndIncrement() / numberOfLocationsIn1File))
				.values()) {
			String filename = resultFileName + fileNumber.getAndIncrement() + ".html";
			Writer writer = new BufferedWriter(new FileWriter(path + resultFolder + "\\" + filename));
			locationList.forEach(location -> location.write(writer));
			writer.close();
		}
		writeIndexFile(fileNumber.get());
	}

	private void writeIndexFile(int numberOfFiles) throws IOException {
		Writer writer = new BufferedWriter(new FileWriter(path + resultFolder + "\\index.html"));
		for (int i = 0; i < numberOfFiles; i++) {
			String actualFilename = resultFileName + i;
			writer.write(Utils.createHTMLLink(path + resultFolder + "\\" + actualFilename + ".html", actualFilename));
		}
		writer.close();
	}

}
