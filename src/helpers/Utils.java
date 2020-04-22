package helpers;

import core.OSBotScript;
import enums.BotStates;
import enums.ToolTypes;
import org.osbot.rs07.Bot;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.function.BooleanSupplier;

public final class Utils extends MethodProvider {

    private static Utils instance;

    private Area[] banks = new Area[]{Banks.LUMBRIDGE_UPPER, Banks.VARROCK_WEST, Banks.VARROCK_EAST,
            Banks.FALADOR_WEST, Banks.FALADOR_EAST, Banks.DRAYNOR, Banks.EDGEVILLE, Banks.AL_KHARID};

    private Utils(Bot bot) {
        exchangeContext(bot);
    }

    public static void initializeInstance(Bot bot) {
        if (instance == null)
            instance = new Utils(bot);
    }

    public static Utils getInstance() {
        return instance;
    }

    public BotStates getCurrentBotState() {
        for (BotStates state : BotStates.values()) {
            if (state.getTask().shouldExecute())
                return state;
        }
        return null;
    }

    public boolean fishingSpotExists(ToolTypes toolType) {
        Object[] closestFishingSpots = getNpcs().getAll().stream().filter((e) -> e.hasAction(toolType.getAction())).toArray();
        return closestFishingSpots.length > 0;
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

    public boolean retrieveItemFromBank(String itemName, int amount, boolean closeBank) throws InterruptedException {
        if (getBank().isOpen() && getBank().contains(itemName) && getBank().withdraw(itemName, amount)) {
            if (closeBank) getBank().close();
        } else if (!getBank().isOpen()) {
            if (getBank().open()) {
                retrieveItemFromBank(itemName, amount, closeBank);
            } else {
                if (walkToNearestBank()) {
                    retrieveItemFromBank(itemName, amount, closeBank);
                } else {
                    log("Unable to walk to the closest bank.");
                    getBot().getScriptExecutor().stop(false);
                    return false;
                }
            }
        } else if (!getBank().contains(itemName) || !getBank().withdraw(itemName, amount)) {
            if (getBank().close()) {
                log("Bank does not contain required fishing tools.");
                getBot().getScriptExecutor().stop(false);
                return false;
            }
        }
        return getInventory().contains(itemName);
    }

    public boolean retrieveAllItemFromBank(String itemName, boolean closeBank) throws InterruptedException {
        if (getBank().isOpen() && getBank().contains(itemName) && getBank().withdrawAll(itemName)) {
            if (closeBank) getBank().close();
            return true;
        } else if (!getBank().isOpen()) {
            if (getBank().open()) {
                retrieveAllItemFromBank(itemName, closeBank);
            } else {
                if (walkToNearestBank()) {
                    retrieveAllItemFromBank(itemName, closeBank);
                } else {
                    log("Unable to walk to the closest bank.");
                    getBot().getScriptExecutor().stop(false);
                    return false;
                }
            }
        } else if (!getBank().contains(itemName) || !getBank().withdrawAll(itemName)) {
            if (getBank().close()) {
                log("Bank does not contain required fishing tools.");
                getBot().getScriptExecutor().stop(false);
                return false;
            }
        }
        return getInventory().contains(itemName);
    }

    public boolean depositAllItemsExcept(String... itemsToKeep) throws InterruptedException {
        if (getBank().isOpen() && getBank().depositAllExcept(itemsToKeep)) {
            return getBank().close();
        } else if (!getBank().isOpen()) {
            if (getBank().open()) {
                depositAllItemsExcept(itemsToKeep);
            } else {
                if (walkToNearestBank()) {
                    depositAllItemsExcept(itemsToKeep);
                } else {
                    log("Unable to walk to the closest bank.");
                    getBot().getScriptExecutor().stop(false);
                    return false;
                }
            }
        } else if (!getBank().depositAllExcept(itemsToKeep)) {
            if (getBank().close()) {
                log("Unable to deposit items.");
                getBot().getScriptExecutor().stop(false);
                return false;
            }
        }
        return false;
    }

    public Point getMousePoint() {
        int x = MouseInfo.getPointerInfo().getLocation().x - getBot().getCanvas().getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - getBot().getCanvas().getLocationOnScreen().y;
        return new Point(x, y);
    }

    public void drawCenteredString(Graphics2D g, Rectangle2D rectangle2D, String text, Color textColor) {
        g.setColor(textColor);
        FontMetrics metrics = g.getFontMetrics();
        int x = (int) (rectangle2D.getX() + (rectangle2D.getWidth() - metrics.stringWidth(text)) / 2);
        int y = (int) (rectangle2D.getY() + (rectangle2D.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(text, x, y);
    }

    public void debugLog(String message) {
        if (OSBotScript.debugMode) {
            log("[DEBUG] - " + message);
        }
    }

    public boolean walkToNearestBank() {
        return getWalking().webWalk(banks);
    }
}
