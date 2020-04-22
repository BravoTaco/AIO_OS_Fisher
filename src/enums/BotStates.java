package enums;

import tasks.core.Task;

public enum BotStates {
    RETRIEVE_SUPPLIES(null),
    WALK_TO_FISHING_LOCATION(null),
    FISH(null),
    DROP(null),
    BANK(null);
    Task task;

    BotStates(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
