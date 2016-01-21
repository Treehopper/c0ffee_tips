package eu.hohenegger.c0ffee_tips;

public class ConverterUtil {
	private final static String HEX_PREFIX = "0x";
	
	public static String convert(String parameter) {
		String numberString = parameter.substring(HEX_PREFIX.length(), parameter.length());
		String result;
		try {
			result = String.format("%d", Long.parseLong(numberString, 16));
		} catch (NumberFormatException e) {
			result = null;
		}
		return result;
	}
}
