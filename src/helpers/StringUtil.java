package helpers;

public final class StringUtil {
    public static String convertCapitalStringToNormal(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        chars[0] = String.valueOf(chars[0]).toUpperCase().toCharArray()[0];
        return String.valueOf(chars);
    }
}
