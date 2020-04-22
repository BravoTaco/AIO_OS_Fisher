package helpers;

import enums.ToolTypes;
import org.osbot.rs07.Bot;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.MethodProvider;

public final class FishingUtils extends MethodProvider {
    private static FishingUtils instance;

    private FishingUtils() {
    }

    public static FishingUtils getInstance() {
        return instance;
    }

    public static void initializeInstance(Bot bot) {
        if (instance == null) {
            instance = new FishingUtils();
            instance.exchangeContext(bot);
        }
    }

    public boolean fishingSpotExists(ToolTypes toolType) {
        Object[] closestFishingSpots = getNpcs().getAll().stream().filter((e) -> e.hasAction(toolType.getAction())).toArray();
        return closestFishingSpots.length > 0;
    }

    public NPC getFishingSpot(ToolTypes toolType) {
        final NPC[] npc = new NPC[1];
        getNpcs().getAll().forEach((e) -> {
            if (e.hasAction(toolType.getAction())) {
                npc[0] = e;
            }
        });
        return npc[0];
    }

    public boolean hasFishingSupplies(ToolTypes selectedToolType) {
        if (selectedToolType == null) return false;
        if (selectedToolType.requiresBait()) {
            String toolName = selectedToolType.getToolName();
            String baitName = selectedToolType.getBaitName();
            return getInventory().contains(toolName) && getInventory().contains(baitName);
        } else {
            String toolName = selectedToolType.getToolName();
            return getInventory().contains(toolName);
        }
    }
}
