package tasks;

import enums.ToolTypes;
import helpers.Utils;
import org.osbot.rs07.Bot;
import tasks.core.Task;

public class TaskRetrieveSupplies extends Task {

    private ToolTypes selectedToolType;

    public TaskRetrieveSupplies(Bot bot, ToolTypes selectedToolType) {
        super(bot, "Retrieve Supplies Task");
        this.selectedToolType = selectedToolType;
    }

    @Override
    protected boolean canExecute() {
        return !Utils.getInstance().hasFishingSupplies(selectedToolType);
    }

    @Override
    protected boolean onExecute() throws InterruptedException {

        if (needBait() && needTool()) {
            if (Utils.getInstance().retrieveItemFromBank(selectedToolType.getToolName(), 1, false)) {
                log("Retrieved " + selectedToolType.getToolName() + " from the bank...");
                if (Utils.getInstance().retrieveAllItemFromBank(selectedToolType.getBaitName(), true)) {
                    log("Retrieved " + selectedToolType.getBaitName() + " from the bank...");
                    return true;
                } else {
                    log("Unable to retrieve " + selectedToolType.getBaitName() + " from the bank...");
                }
            } else {
                log("Unable to retrieve " + selectedToolType.getToolName() + " from the bank...");
            }
        } else if (needTool()) {
            return Utils.getInstance().retrieveItemFromBank(selectedToolType.getToolName(), 1, true);
        } else if (needBait()) {
            return Utils.getInstance().retrieveAllItemFromBank(selectedToolType.getBaitName(), true);
        }

        return false;
    }

    @Override
    protected void onEndExecute() {
        String message = (hasFinishedSuccessfully) ? "Retrieved supplies." : "Unable to retrieve supplies";
        log(message);
    }

    private boolean needTool() {
        return !getInventory().contains(selectedToolType.getToolName());
    }

    private boolean needBait() {
        return selectedToolType.requiresBait() &&
                !getInventory().contains(selectedToolType.getBaitName());
    }
}
