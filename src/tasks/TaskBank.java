package tasks;

import enums.FishTypes;
import enums.Locations;
import enums.ToolTypes;
import helpers.BankUtils;
import org.osbot.rs07.Bot;
import tasks.core.Task;

public class TaskBank extends Task {
    private boolean isBankingEnabled;
    private ToolTypes toolType;
    private Locations selectedLocation;

    public TaskBank(Bot bot, ToolTypes toolType, Locations selectedLocation, boolean isBankingEnabled) {
        super(bot, "Banking Task");
        this.isBankingEnabled = isBankingEnabled;
        this.toolType = toolType;
        this.selectedLocation = selectedLocation;
    }

    @Override
    protected boolean canExecute() {
        return getInventory().isFull() && isBankingEnabled;
    }

    @Override
    protected boolean onExecute() throws InterruptedException {
        String[] itemsToKeep = (selectedLocation.isCoinsRequired()) ?
                new String[]{toolType.getToolName(), toolType.getBaitName(), "Coins"} :
                new String[]{toolType.getToolName(), toolType.getBaitName()};
        return BankUtils.getInstance().depositAllItemsExcept(itemsToKeep);
    }

    @Override
    protected void onEndExecute() {

    }
}
