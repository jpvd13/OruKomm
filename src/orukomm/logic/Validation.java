package orukomm.logic;

public class Validation {

    public static boolean isEmptyOrNull(String input) {
        return input == null || input.isEmpty();
    }

    
    public static boolean wordLength(String input, int maxLength)
    {
        return (input.length() > maxLength);
    }

   
}
