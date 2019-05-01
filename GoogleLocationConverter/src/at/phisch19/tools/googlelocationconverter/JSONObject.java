package at.phisch19.tools.googlelocationconverter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.Function;

import lombok.Data;
import lombok.Getter;

@Data
public class JSONObject {

	@Getter
	private List<JSONLocation> locations;

	@Data
	public static class JSONLocation {
		@Getter
		private long timestampMs;
		@Getter
		private String latitudeE7;
		@Getter
		private String longitudeE7;

		protected Location createLocation() {
			Location location = new Location();
			location.setLatitude(gpsConverter.apply(latitudeE7));
			location.setLongitude(gpsConverter.apply(longitudeE7));
			location.setTimestamp(new Timestamp(timestampMs));
			return location;
		}

		protected final static Function<String, BigDecimal> gpsConverter = t -> new BigDecimal(t)
				.divide(new BigDecimal("10000000"));

	}

}
