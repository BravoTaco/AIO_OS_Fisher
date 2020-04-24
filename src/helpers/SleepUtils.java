package helpers;

import org.osbot.rs07.Bot;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

import java.util.function.BooleanSupplier;

public final class SleepUtils extends MethodProvider {
    private static SleepUtils instance;

    private SleepUtils() {
    }

    public static SleepUtils getInstance() {
        return instance;
    }

    public static void initializeInstance(Bot bot) {
        if (instance == null) {
            instance = new SleepUtils();
            instance.exchangeContext(bot);
        }
    }

    public boolean sleepUntil(BooleanSupplier condition, int maxTime, int checkInterval) {
        new ConditionalSleep(maxTime, checkInterval) {
            @Override
            public boolean condition() throws InterruptedException {
                return condition.getAsBoolean();
            }
        }.sleep();
        return condition.getAsBoolean();
    }

    public boolean sleep(int minTime, int maxTime) {
        new ConditionalSleep(random(minTime, maxTime), 500) {
            @Override
            public boolean condition() throws InterruptedException {
                return false;
            }
        };
        return true;
    }
}
