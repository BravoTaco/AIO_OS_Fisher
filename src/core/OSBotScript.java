package core;

import data.Int4;
import enums.BotStates;
import enums.FishTypes;
import enums.Locations;
import enums.ToolTypes;
import gui.core.MainDialog;
import helpers.Utils;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import paint.PaintButton;
import tasks.*;
import tasks.core.Task;

import java.awt.*;

@ScriptManifest(info = "AIO OS Fisher.", version = 0.04, logo = "https://i.imgur.com/4aiCsAb.png",
        author = "BravoTaco", name = "AIO OS Fisher")
public class OSBotScript extends Script {

    private static BotStates currentBotState;
    private static Task currentTask;
    private static FishTypes selectedFishType;
    private static Locations selectedLocation;
    private static boolean isBankingEnabled, paintEnabled = true, initializationsComplete;
    private static ToolTypes selectedToolType;

    private Point[] points = new Point[50];
    private int currentPointToSet;
    private PaintButton paintStateButton;

    public static boolean debugMode = true;

    @Override
    public void onStart() throws InterruptedException {
        Utils.initializeInstance(bot);
        MainDialog.getInstance().show();
        runInitialChecks();
        initializeTasks();
        initializeButtons();
        initializationsComplete = true;
    }

    @Override
    public int onLoop() throws InterruptedException {

        currentBotState = Utils.getInstance().getCurrentBotState();

        if (currentBotState != null) {
            currentTask = currentBotState.getTask();
        } else {
            warn("Current bot state is NULL");
        }

        if (currentTask != null) {
            currentTask.execute();
        } else {
            warn("Current task is NULL");
        }

        return random(800, 2600);
    }

    @Override
    public void onExit() throws InterruptedException {

    }

    @Override
    public void onPaint(Graphics2D g) {
        if (initializationsComplete) {
            if (paintEnabled) {
                drawMouse(g);
            }
            paintStateButton.drawButton(g);
        }
    }

    private void drawMouse(Graphics2D g) {
        if (currentPointToSet == points.length)
            currentPointToSet = 0;
        Point mousePoint = getMouse().getPosition();
        points[currentPointToSet] = mousePoint;
        g.setColor(Color.red);
        g.drawOval(mousePoint.x - 3, mousePoint.y - 3, 6, 6);
        g.setColor(Color.cyan);
        g.drawOval(mousePoint.x - 6, mousePoint.y - 6, 12, 12);
        g.setColor(Color.cyan.darker());
        g.drawOval(mousePoint.x - 10, mousePoint.y - 10, 20, 20);
        g.setColor(Color.cyan);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] != null && points[i + 1] != null) {
                g.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
            }
            if (i + 1 == points.length - 1 && points[i + 1] != null && points[0] != null) {
                g.drawLine(points[i + 1].x, points[i + 1].y, points[0].x, points[0].y);
            }
        }
        currentPointToSet++;
    }

    private void initializeTasks() throws InterruptedException {
        log("Initializing tasks...");
        TaskRetrieveSupplies taskRetrieveSupplies = new TaskRetrieveSupplies(bot, selectedToolType);
        BotStates.RETRIEVE_SUPPLIES.setTask(taskRetrieveSupplies);
        TaskWalkToFishingLocation taskWalkToFishingLocation = new TaskWalkToFishingLocation(bot, selectedLocation, selectedToolType);
        BotStates.WALK_TO_FISHING_LOCATION.setTask(taskWalkToFishingLocation);
        TaskFishing taskFishing = new TaskFishing(bot, selectedFishType, selectedToolType);
        BotStates.FISH.setTask(taskFishing);
        TaskDrop taskDrop = new TaskDrop(bot, selectedToolType, isBankingEnabled);
        BotStates.DROP.setTask(taskDrop);
        TaskBank taskBank = new TaskBank(bot, selectedToolType, selectedFishType, isBankingEnabled);
        BotStates.BANK.setTask(taskBank);
        log("Initialization complete.");
    }

    private void initializeButtons() {
        paintStateButton = new PaintButton(new Int4(410, 300, 100, 30), "Disable Paint", bot) {
            @Override
            public void onClick() {
                paintEnabled = !paintEnabled;
                setText((paintEnabled) ? "Disable Paint" : "Enable Paint");
            }
        };
    }

    private void runInitialChecks() {
        if (!MainDialog.getInstance().wasConfirmClicked()) {
            stop(false);
            return;
        } else {
            selectedFishType = (FishTypes) MainDialog.getInstance().getFishSelector().getFishTypesJComboBox().getSelectedItem();
            selectedLocation = (Locations) MainDialog.getInstance().getLocationSelector().getLocationsJComboBox().getSelectedItem();
            isBankingEnabled = MainDialog.getInstance().getFishingModeSelector().getBankingCB().isSelected();
            selectedToolType = (ToolTypes) MainDialog.getInstance().getToolSelector().getToolTypesJComboBox().getSelectedItem();
        }
        if (selectedFishType.getLevelRequired() > getSkills().getStatic(Skill.FISHING)) stop(false);
        if (selectedFishType.isMembers() && !getWorlds().isMembersWorld() || selectedToolType.isMembers() && !getWorlds().isMembersWorld())
            stop(false);
    }
}