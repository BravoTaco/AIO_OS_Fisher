package data;

import enums.BotStates;
import enums.FishTypes;
import enums.Locations;
import enums.ToolTypes;
import legacy_paint.components.PaintButton;
import legacy_paint.core.PaintInformationBase;
import tasks.core.Task;

import java.awt.*;
import java.io.*;

public class StoredInformation implements Serializable {
    private final static long serialVersionUID = 1L;
    private final GeneralStoredInformation generalStoredInformation;
    private transient PaintStoredInformation paintStoredInformation;

    public StoredInformation() {
        generalStoredInformation = new GeneralStoredInformation();
        paintStoredInformation = new PaintStoredInformation();
    }

    public GeneralStoredInformation getGeneralStoredInformation() {
        return generalStoredInformation;
    }

    public PaintStoredInformation getPaintStoredInformation() {
        if (paintStoredInformation == null)
            paintStoredInformation = new PaintStoredInformation();
        return paintStoredInformation;
    }

    public static class GeneralStoredInformation implements Serializable {
        private final static long serialVersionUID = 1L;
        private transient BotStates currentBotState;
        private transient Task currentTask;
        private FishTypes selectedFishType;
        private Locations selectedLocation;
        private ToolTypes selectedToolType;
        private boolean isBankingEnabled, isInitializationsComplete;

        public BotStates getCurrentBotState() {
            return currentBotState;
        }

        public void setCurrentBotState(BotStates currentBotState) {
            this.currentBotState = currentBotState;
        }

        public Task getCurrentTask() {
            return currentTask;
        }

        public void setCurrentTask(Task currentTask) {
            this.currentTask = currentTask;
        }

        public FishTypes getSelectedFishType() {
            return selectedFishType;
        }

        public void setSelectedFishType(FishTypes selectedFishType) {
            this.selectedFishType = selectedFishType;
        }

        public Locations getSelectedLocation() {
            return selectedLocation;
        }

        public void setSelectedLocation(Locations selectedLocation) {
            this.selectedLocation = selectedLocation;
        }

        public ToolTypes getSelectedToolType() {
            return selectedToolType;
        }

        public void setSelectedToolType(ToolTypes selectedToolType) {
            this.selectedToolType = selectedToolType;
        }

        public boolean isBankingEnabled() {
            return isBankingEnabled;
        }

        public void setBankingEnabled(boolean bankingEnabled) {
            isBankingEnabled = bankingEnabled;
        }

        public boolean isInitializationsComplete() {
            return isInitializationsComplete;
        }

        public void setInitializationsComplete(boolean initializationsComplete) {
            isInitializationsComplete = initializationsComplete;
        }
    }

    public static class PaintStoredInformation {
        private Point[] points = new Point[50];
        private int currentPointToSet;
        private transient PaintButton paintStateButton;
        private transient PaintInformationBase paintInformationBase;
        private boolean isPaintEnabled = true;
        private long xpPerHour, fishCaught, runTime, startTime;

        public Point[] getPoints() {
            return points;
        }

        public void increaseMouseTrail(int increaseAmount) {
            int newAmount = this.points.length + increaseAmount;
            this.points = new Point[newAmount];
        }

        public void decreaseMouseTrail(int decreaseAmount) {
            int newAmount = this.points.length - decreaseAmount;
            if (newAmount <= 0)
                newAmount = 1;
            this.points = new Point[newAmount];
        }

        public int getCurrentPointToSet() {
            return currentPointToSet;
        }

        public void setCurrentPointToSet(int currentPointToSet) {
            this.currentPointToSet = currentPointToSet;
        }

        public PaintButton getPaintStateButton() {
            return paintStateButton;
        }

        public void setPaintStateButton(PaintButton paintStateButton) {
            this.paintStateButton = paintStateButton;
        }

        public PaintInformationBase getPaintInformationBase() {
            return paintInformationBase;
        }

        public void setPaintInformationBase(PaintInformationBase paintInformationBase) {
            this.paintInformationBase = paintInformationBase;
        }

        public boolean isPaintEnabled() {
            return isPaintEnabled;
        }

        public void setPaintEnabled(boolean paintEnabled) {
            isPaintEnabled = paintEnabled;
        }

        public long getXpPerHour() {
            return xpPerHour;
        }

        public void setXpPerHour(long xpPerHour) {
            this.xpPerHour = xpPerHour;
        }

        public long getFishCaught() {
            return fishCaught;
        }

        public void setFishCaught(long fishCaught) {
            this.fishCaught = fishCaught;
        }

        public long getRunTime() {
            return runTime;
        }

        public void setRunTime(long runTime) {
            this.runTime = runTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }
    }
}
