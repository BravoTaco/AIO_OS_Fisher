package helpers;


public final class FormatUtils {
    private FormatUtils(){}

    public static String formatTime(final long time) {
        final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
    }

    public static String formatValue(final long l) {
        return (l > 1_000_000) ? String.format("%.2fm", ((double) l / 1_000_000))
                : (l > 1000) ? String.format("%.1fk", ((double) l / 1000))
                : l + "";
    }

    public static long calculateItemsPerHour(float runTimeInMillis, float gainedItems) {
        if (gainedItems == 0) return 0;
        float seconds = runTimeInMillis / 1000;
        return (long) ((gainedItems / seconds) * 60 * 60);
    }
}
