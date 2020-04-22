package helpers;

import org.osbot.rs07.Bot;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;

public final class ArrayUtils extends MethodProvider {
    private static ArrayUtils instance;

    private ArrayUtils() {
    }

    public static ArrayUtils getInstance() {
        return instance;
    }

    public static void initializeInstance(Bot bot) {
        if (instance == null) {
            instance = new ArrayUtils();
            instance.exchangeContext(bot);
        }
    }

    public Area[] addAreaArrays(Area[]... arrays) {
        int totalSize = 0;
        for (Area[] temp : arrays) {
            totalSize += temp.length;
        }
        int i = 0;
        Area[] tempArray = new Area[totalSize];
        for (Area[] temp : arrays) {
            for (Area area : temp) {
                tempArray[i] = area;
                i++;
            }
        }
        return tempArray;
    }
}
