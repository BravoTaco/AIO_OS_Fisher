package tasks;

import enums.FishTypes;
import enums.ToolTypes;
import helpers.BankUtils;
import org.osbot.rs07.Bot;
import tasks.core.Task;

public class TaskBank extends Task {
    private boolean isBankingEnabled = false;
    private ToolTypes toolType;
    private FishTypes selectedFishType;

    public TaskBank(Bot bot, ToolTypes toolType, FishTypes selectedFishType, boolean isBankingEnabled) {
        super(bot, "Banking Task");
        this.isBankingEnabled = isBankingEnabled;
        this.toolType = toolType;
        this.selectedFishType = selectedFishType;
    }

    @Override
    protected boolean canExecute() {
        return getInventory().isFull() && isBankingEnabled;
    }

    @Override
    protected boolean onExecute() throws InterruptedException {
        String[] itemsToKeep = (selectedFishType.requiresCoins()) ?
                new String[]{toolType.getToolName(), toolType.getBaitName(), "Coins"} :
                new String[]{toolType.getToolName(), toolType.getBaitName()};
        return BankUtils.getInstance().depositAllItemsExcept(itemsToKeep);
    }

    @Override
    protected void onEndExecute() {

    }
}
