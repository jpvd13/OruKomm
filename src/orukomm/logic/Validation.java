package orukomm.logic;

public class Validation {
	
	public static boolean isEmptyOrNull(String input) {
		return input == null || input.trim().isEmpty();
	}
}