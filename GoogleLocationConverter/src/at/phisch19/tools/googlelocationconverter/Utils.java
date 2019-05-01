package at.phisch19.tools.googlelocationconverter;

public abstract class Utils {

	public static String createHTMLLink(String linkTo, String display) {
		return "<a href=\"" + linkTo + "\" target=\"_blank\">" + display + "</a></br>";
	}

}
