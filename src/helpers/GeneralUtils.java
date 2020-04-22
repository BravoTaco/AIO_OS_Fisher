package helpers;

import core.OSBotScript;
import enums.BotStates;
import org.osbot.rs07.Bot;
import org.osbot.rs07.script.MethodProvider;

public final class GeneralUtils extends MethodProvider {

    private static GeneralUtils instance;

    private GeneralUtils() {
    }

    public static GeneralUtils getInstance() {
        return instance;
    }

    public static void initializeInstance(Bot bot) {
        if (instance == null) {
            instance = new GeneralUtils();
            instance.exchangeContext(bot);
        }
    }

    public BotStates getCurrentBotState() {
        for (BotStates state : BotStates.values()) {
            if (state.getTask().shouldExecute())
                return state;
        }
        return null;
    }

    public void debugLog(String message) {
        if (OSBotScript.debugMode) {
            log("[DEBUG] - " + message);
        }
    }
}
