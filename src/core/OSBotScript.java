package core;

import data.Int4;
import data.StoredInformation;
import enums.BotStates;
import enums.FishTypes;
import enums.Locations;
import enums.ToolTypes;
import gui.core.MainDialog;
import helpers.*;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import paint.components.PaintButton;
import paint.core.PaintInformationBase;
import tasks.*;

import java.awt.*;

@ScriptManifest(info = "AIO OS Fisher.", version = 0.04, logo = "https://i.imgur.com/4aiCsAb.png",
        author = "BravoTaco", name = "AIO OS Fisher")
public class OSBotScript extends Script {

    private StoredInformation storedInformation;

    @Override
    public void onStart() throws InterruptedException {
        storedInformation = new StoredInformation();
        initializeUtils();
        MainDialog.getInstance().show();
        runInitialChecks();
        initializeTasks();
        initializeButtons();
        initializeInformationPaint();
        getExperienceTracker().start(Skill.FISHING);
        storedInformation.getPaintStoredInformation().setOldXpAmount(getSkills().getExperience(Skill.FISHING));
        storedInformation.getPaintStoredInformation().setStartTime(System.currentTimeMillis());
        storedInformation.getGeneralStoredInformation().setInitializationsComplete(true);
    }

    @Override
    public int onLoop() throws InterruptedException {

        storedInformation.getGeneralStoredInformation().setCurrentBotState(GeneralUtils.getInstance().getCurrentBotState());

        if (storedInformation.getGeneralStoredInformation().getCurrentBotState() != null) {
            storedInformation.getGeneralStoredInformation().
                    setCurrentTask(storedInformation.getGeneralStoredInformation().getCurrentBotState().getTask());
        } else {
            warn("Current bot state is NULL");
        }

        if (storedInformation.getGeneralStoredInformation().getCurrentTask() != null) {
            storedInformation.getGeneralStoredInformation().getCurrentTask().execute();
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
        if (storedInformation.getGeneralStoredInformation().isInitializationsComplete()) {
            storedInformation.getPaintStoredInformation().setRunTime(
                    System.currentTimeMillis() - storedInformation.getPaintStoredInformation().getStartTime());
            storedInformation.getPaintStoredInformation().setXpPerHour(getExperienceTracker().getGainedXPPerHour(Skill.FISHING));
            if (storedInformation.getPaintStoredInformation().getOldXpAmount() < getSkills().getExperience(Skill.FISHING)) {
                storedInformation.getPaintStoredInformation().setFishCaught(storedInformation.getPaintStoredInformation().getFishCaught() + 1);
                storedInformation.getPaintStoredInformation().setOldXpAmount(getSkills().getExperience(Skill.FISHING));
            }
            if (storedInformation.getPaintStoredInformation().isPaintEnabled()) {
                drawMouse(g);
                storedInformation.getPaintStoredInformation().getPaintInformationBase().drawComponent(g);
            }
            storedInformation.getPaintStoredInformation().getPaintStateButton().drawComponent(g);
        }
    }

    private void drawMouse(Graphics2D g) {
        int currentPointToSet = storedInformation.getPaintStoredInformation().getCurrentPointToSet();
        Point[] points = storedInformation.getPaintStoredInformation().getPoints();
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
        storedInformation.getPaintStoredInformation().setCurrentPointToSet(currentPointToSet + 1);
    }

    private void initializeTasks() throws InterruptedException {
        ToolTypes selectedToolType = storedInformation.getGeneralStoredInformation().getSelectedToolType();
        Locations selectedLocation = storedInformation.getGeneralStoredInformation().getSelectedLocation();
        FishTypes selectedFishType = storedInformation.getGeneralStoredInformation().getSelectedFishType();
        boolean isBankingEnabled = storedInformation.getGeneralStoredInformation().isBankingEnabled();
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
        storedInformation.getPaintStoredInformation().setPaintStateButton(new PaintButton(new Int4(15, 300, 100, 30),
                "Disable Paint", bot) {
            @Override
            public void onClick() {
                storedInformation.getPaintStoredInformation().setPaintEnabled(
                        !storedInformation.getPaintStoredInformation().isPaintEnabled());
                setText((storedInformation.getPaintStoredInformation().isPaintEnabled()) ? "Disable Paint" : "Enable Paint");
            }
        });
    }

    private void initializeInformationPaint() {
        storedInformation.getPaintStoredInformation().setPaintInformationBase(
                new PaintInformationBase(new Int4(15, 60, 180, 230), storedInformation));
    }

    private void runInitialChecks() {
        if (!MainDialog.getInstance().wasConfirmClicked()) {
            stop(false);
            return;
        } else {
            FishTypes selectedFishType = (FishTypes) MainDialog.getInstance().getFishSelector().getFishTypesJComboBox().getSelectedItem();
            Locations selectedLocation = (Locations) MainDialog.getInstance().getLocationSelector().getLocationsJComboBox().getSelectedItem();
            ToolTypes selectedToolType = (ToolTypes) MainDialog.getInstance().getToolSelector().getToolTypesJComboBox().getSelectedItem();
            boolean isBankingEnabled = MainDialog.getInstance().getFishingModeSelector().getBankingCB().isSelected();
            storedInformation.getGeneralStoredInformation().setSelectedFishType(selectedFishType);
            storedInformation.getGeneralStoredInformation().setSelectedLocation(selectedLocation);
            storedInformation.getGeneralStoredInformation().setSelectedToolType(selectedToolType);
            storedInformation.getGeneralStoredInformation().setBankingEnabled(isBankingEnabled);
        }
        if (storedInformation.getGeneralStoredInformation().getSelectedFishType().getLevelRequired()
                > getSkills().getStatic(Skill.FISHING)) {
            stop(false);
            log("You do not have the fishing level required for the selected fish type.");
        }
        if (storedInformation.getGeneralStoredInformation().getSelectedFishType().isMembers() && !getWorlds().isMembersWorld()
                || storedInformation.getGeneralStoredInformation().getSelectedToolType().isMembers() && !getWorlds().isMembersWorld()) {
            stop(false);
            log("The fish type or the selected tool is members only and you are on a F2P world.");
        }
    }

    private void initializeUtils() {
        GeneralUtils.initializeInstance(bot);
        BankUtils.initializeInstance(bot);
        SleepUtils.initializeInstance(bot);
        ArrayUtils.initializeInstance(bot);
        PaintUtils.initializeInstance(bot);
        FishingUtils.initializeInstance(bot);
    }
}