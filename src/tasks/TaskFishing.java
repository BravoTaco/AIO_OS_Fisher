package tasks;

import enums.FishTypes;
import enums.ToolTypes;
import helpers.FishingUtils;
import helpers.SleepUtils;
import org.osbot.rs07.Bot;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import tasks.core.Task;

public class TaskFishing extends Task {

    private FishTypes selectedFishType;
    private ToolTypes selectedToolType;

    public TaskFishing(Bot bot, FishTypes selectedFishType, ToolTypes selectedToolType) {
        super(bot, "Fishing Task");
        this.selectedFishType = selectedFishType;
        this.selectedToolType = selectedToolType;
    }

    @Override
    protected boolean canExecute() {
        if (selectedFishType == null) return false;
        return FishingUtils.getInstance().hasFishingSupplies(selectedToolType) &&
                FishingUtils.getInstance().fishingSpotExists(selectedToolType) &&
                !getInventory().isFull();
    }

    @Override
    protected boolean onExecute() {
        if (myPlayer().isAnimating()) {
            getMouse().move(random(10, 800), random(10, 800));
            int randomChance = random(1, 100);
            if (randomChance <= 40) {
                getCamera().movePitch(random(10, 90));
                getCamera().moveYaw(random(10, 90));
            }
            getMouse().moveOutsideScreen();
            SleepUtils.getInstance().sleepUntil(() -> !myPlayer().isAnimating(), random(10000, 80000), 100);
            if (!myPlayer().isAnimating()) SleepUtils.getInstance().sleep(3000, 12000);
            return true;
        } else if (!myPlayer().isAnimating()) {
            NPC fishingSpot = FishingUtils.getInstance().getFishingSpot(selectedToolType);
            if (interact(fishingSpot, selectedToolType.getAction())) {
                return SleepUtils.getInstance().sleepUntil(() -> myPlayer().isAnimating(), 10000, 100);
            } else if (fishingSpot == null) {
                log("Fishing spot does not exist...");
                return false;
            } else if (!interact(fishingSpot, selectedToolType.getAction())) {
                log("Unable to interact with fishing spot...");
                return false;
            }
        }
        return false;
    }

    @Override
    protected void onEndExecute() {
        String message = (hasFinishedSuccessfully) ? "TaskFishing completed successfully..." : "TaskFishing unable to be completed...";
        log(message);
    }

    private boolean interact(Entity entity, String action) {
        if (entity != null && entity.getActions() != null) {
            for (String s : entity.getActions()) {
                if (s != null && s.toLowerCase().contains(action.toLowerCase()))
                    return entity.interact(s);
            }
        }
        return false;
    }
}
