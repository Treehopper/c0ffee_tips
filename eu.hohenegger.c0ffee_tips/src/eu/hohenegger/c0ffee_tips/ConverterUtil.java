package eu.hohenegger.c0ffee_tips;

public class ConverterUtil {
	private final static String HEX_PREFIX = "0x";
	
	public static String convert(String parameter) {
		String result = "";
		
		try {
			if (parameter.startsWith(HEX_PREFIX)) {
				String numberString = parameter.substring(HEX_PREFIX.length(), parameter.length());
				
				result = long2hex(numberString);
			} else {
				result = hex2long(parameter);
			}
		} catch (NumberFormatException e) {
			//ignore
		}
		return result;
	}


	private static String hex2long(String parameter) {
		return String.format(HEX_PREFIX+"%x", Long.valueOf(parameter));
	}


	private static String long2hex(String numberString) {
		long parseLong = Long.parseLong(numberString, 16);
		return String.format("%d", parseLong);
	}
}
