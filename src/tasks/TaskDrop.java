package tasks;

import enums.ToolTypes;
import org.osbot.rs07.Bot;
import tasks.core.Task;

public class TaskDrop extends Task {
    private boolean isBankingEnabled;
    private ToolTypes toolType;

    public TaskDrop(Bot bot, ToolTypes toolType, boolean isBankingEnabled) {
        super(bot, "Dropping Task");
        this.isBankingEnabled = isBankingEnabled;
        this.toolType = toolType;
    }

    @Override
    protected boolean canExecute() {
        return getInventory().isFull() && !isBankingEnabled;
    }

    @Override
    protected boolean onExecute() {
        return getInventory().dropAllExcept(toolType.getToolName(), toolType.getBaitName());
    }

    @Override
    protected void onEndExecute() {
        String message = (hasFinishedSuccessfully) ? "TaskDrop completed successfully..." : "TaskDrop unable to be completed...";
        log(message);
    }
}
