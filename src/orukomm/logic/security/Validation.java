package orukomm.logic.security;

public class Validation {

    public static boolean isEmptyOrNull(String input) {
        return input == null || input.isEmpty();
    }

    // Check if input matches 24-hour time format, i.e. H[H]:mm.
    public static boolean is24HourFormat(String time) {
        return time.matches("(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]");
    }
    
    public static boolean wordLength(String input, int maxLength) {
        return (input.length() > maxLength);
    }

}
