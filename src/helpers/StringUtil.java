package helpers;

public final class StringUtil {
    public static String convertCapitalStringToNormal(String string, boolean hasUnderscores) {
        char[] chars = string.toLowerCase().toCharArray();
        chars[0] = String.valueOf(chars[0]).
                toUpperCase().
                toCharArray()[0];

        if (hasUnderscores) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '_') {
                    chars[i] = ' ';
                    chars[i + 1] = String.valueOf(
                            chars[i + 1]).toUpperCase().
                            toCharArray()[0];
                }
            }
        }

        return String.valueOf(chars);
    }
}
