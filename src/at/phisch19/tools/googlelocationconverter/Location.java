package at.phisch19.tools.googlelocationconverter;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Location {
	@Getter
	@Setter
	private Timestamp timestamp;

	@Getter
	@Setter
	private BigDecimal latitude;

	@Getter
	@Setter
	private BigDecimal longitude;

	private String toHtml() {
//		return "<a href=\"https://latlong.net/c/?lat=" + latitude + "&long=" + longitude + "\" target=\"_blank\">"
//				+ timestamp + "</a></br>";
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
