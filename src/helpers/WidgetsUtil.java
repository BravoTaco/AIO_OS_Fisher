package helpers;

import org.osbot.rs07.Bot;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;

public final class WidgetsUtil extends MethodProvider {
    private static WidgetsUtil instance;

    private WidgetsUtil() {
    }

    public static void initializeInstance(Bot bot) {
        if (instance == null) {
            instance = new WidgetsUtil();
            instance.exchangeContext(bot);
        }
    }

    public static WidgetsUtil getInstance() {
        return instance;
    }

    public RS2Widget getWidgetContainingText(String text) {
        for (RS2Widget widget : getWidgets().getAll()) {
            if (widget != null && widget.getMessage() != null && widget.getMessage().toLowerCase().contains(text.toLowerCase()))
                return widget;
        }
        return null;
    }
}
