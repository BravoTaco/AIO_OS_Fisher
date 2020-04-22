package tasks.core;

import org.osbot.rs07.Bot;
import org.osbot.rs07.script.MethodProvider;

public abstract class Task extends MethodProvider {

    private String taskName;

    public Task(Bot bot, String taskName) {
        exchangeContext(bot);
        this.taskName = taskName;
    }

    public final boolean execute() throws InterruptedException {
        hasFinishedSuccessfully = false;
        log("Checking requirements for task: " + this.toString());
        if (canExecute()) {
            log("Requirements met... Will execute task.");
            log("Executing task: " + this.toString());
            hasFinishedSuccessfully = onExecute();
            onEndExecute();
            return true;
        }
        onEndExecute();
        return false;
    }

    public boolean shouldExecute() {
        return canExecute();
    }

    protected boolean hasFinishedSuccessfully;

    protected abstract boolean canExecute();

    protected abstract boolean onExecute() throws InterruptedException;

    protected abstract void onEndExecute();

    @Override
    public String toString() {
        return taskName;
    }
}
