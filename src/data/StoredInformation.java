package data;

import enums.BotStates;
import enums.FishTypes;
import enums.Locations;
import enums.ToolTypes;
import paint.PaintButton;
import paint.PaintInformationBase;
import tasks.core.Task;

import java.awt.*;

public class StoredInformation {
    private GeneralStoredInformation generalStoredInformation;
    private PaintStoredInformation paintStoredInformation;

    public StoredInformation() {
        generalStoredInformation = new GeneralStoredInformation();
        paintStoredInformation = new PaintStoredInformation();
    }

    public GeneralStoredInformation getGeneralStoredInformation() {
        return generalStoredInformation;
    }

    public PaintStoredInformation getPaintStoredInformation() {
        return paintStoredInformation;
    }

    public class GeneralStoredInformation {
        private BotStates currentBotState;
        private Task currentTask;
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

    public class PaintStoredInformation {
        private Point[] points = new Point[50];
        private int currentPointToSet;
        private PaintButton paintStateButton;
        private PaintInformationBase paintInformationBase;
        private boolean isPaintEnabled = true;

        public Point[] getPoints() {
            return points;
        }

        public void setPoints(Point[] points) {
            this.points = points;
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
    }
}
