package tasks;

import enums.ToolTypes;
import helpers.Utils;
import org.osbot.rs07.Bot;
import tasks.core.Task;

public class TaskBank extends Task {
    private boolean isBankingEnabled = false;
    private ToolTypes toolType;

    public TaskBank(Bot bot, ToolTypes toolType, boolean isBankingEnabled) {
        super(bot, "Banking Task");
        this.isBankingEnabled = isBankingEnabled;
        this.toolType = toolType;
    }

    @Override
    protected boolean canExecute() {
        return getInventory().isFull() && isBankingEnabled;
    }

    @Override
    protected boolean onExecute() throws InterruptedException {
        return Utils.getInstance().depositAllItemsExcept(toolType.getToolName(), toolType.getBaitName());
    }

    @Override
    protected void onEndExecute() {

    }
}
