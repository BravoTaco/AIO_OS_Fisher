package tasks;

import enums.Locations;
import enums.ToolTypes;
import helpers.BankUtils;
import helpers.FishingUtils;
import org.osbot.rs07.Bot;
import tasks.core.Task;

public class TaskRetrieveSupplies extends Task {

    private ToolTypes selectedToolType;
    private Locations selectedLocation;

    public TaskRetrieveSupplies(Bot bot, ToolTypes selectedToolType, Locations selectedLocation) {
        super(bot, "Retrieve Supplies Task");
        this.selectedToolType = selectedToolType;
        this.selectedLocation = selectedLocation;
    }

    @Override
    protected boolean canExecute() {
        return !FishingUtils.getInstance().hasFishingSupplies(selectedToolType);
    }

    @Override
    protected boolean onExecute() throws InterruptedException {

        if (needTool()) {
            BankUtils.getInstance().retrieveItemFromBank(selectedToolType.getToolName(), 1, false);
        }

        if (needBait()) {
            BankUtils.getInstance().retrieveAllItemFromBank(selectedToolType.getBaitName(), false);
        }

        if (needCoins()) {
            if (!BankUtils.getInstance().retrieveItemFromBank("Coins", 15000, true)) {
                BankUtils.getInstance().retrieveAllItemFromBank("Coins", true);
            }
        }

        return !needTool() && !needBait() && !needCoins();
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

    private boolean needCoins() {
        return selectedLocation.isCoinsRequired() && !getInventory().contains("Coins");
    }
}
