package tasks;

import enums.Locations;
import enums.ToolTypes;
import helpers.FishingUtils;
import org.osbot.rs07.Bot;
import tasks.core.Task;

public class TaskWalkToFishingLocation extends Task {
    private Locations selectedLocation;
    private ToolTypes selectedToolType;

    public TaskWalkToFishingLocation(Bot bot, Locations selectedLocation, ToolTypes selectedToolType) {
        super(bot, "Walk To Fishing Location Task");
        this.selectedLocation = selectedLocation;
        this.selectedToolType = selectedToolType;
    }

    @Override
    protected boolean canExecute() {
        return FishingUtils.getInstance().hasFishingSupplies(selectedToolType) &&
                !selectedLocation.getPosition().isOnMiniMap(getBot()) && !getInventory().isFull();
    }

    @Override
    protected boolean onExecute() {
        log("Attempting to walk to fishing location...");
        if (getWalking().webWalk(selectedLocation.getPosition())) {
            log("Walked to fishing location.");
            return true;
        } else {
            log("Unable to walk to fishing location.");
            return false;
        }
    }

    @Override
    protected void onEndExecute() {
        String message = (hasFinishedSuccessfully) ? "Finished walking task." : "Was unable to finish walking task.";
        log(message);
    }
}
