package at.phisch19.tools.googlelocationconverter;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Location {
	private Timestamp timestamp;
	private BigDecimal latitude;
	private BigDecimal longitude;

	private String toHtml() {
		String linkTo = "https://latlong.net/c/?lat=" + latitude + "&long=" + longitude;
		return Utils.createHTMLLink(linkTo, timestamp.toString());
	}

	public void write(Writer writer) {
		try {
			writer.write(toHtml());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
