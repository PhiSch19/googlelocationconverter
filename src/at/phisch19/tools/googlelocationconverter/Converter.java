package at.phisch19.tools.googlelocationconverter;

import java.io.IOException;

/**
 * @author Philipp Schermann
 */
public class Converter {

	// BEGIN MANDATORY Configurations
	private static String path = "D:\\LocationData\\";
	private static String source = "data";
	// END MANDATORY Configurations

	public static void main(String[] args) throws IOException {
		LocationConverter.builder()
				.path(path)
				.sourceFileName(source)
				.build()
				.convert();
		System.out.println("Convertation finished");
	}

}
