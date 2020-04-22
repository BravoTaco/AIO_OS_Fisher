package helpers;

import org.osbot.rs07.Bot;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.MethodProvider;

public final class BankUtils extends MethodProvider {
    private static BankUtils instance;

    private Area[] banks = new Area[]{Banks.LUMBRIDGE_UPPER, Banks.VARROCK_WEST, Banks.VARROCK_EAST,
            Banks.FALADOR_WEST, Banks.FALADOR_EAST, Banks.DRAYNOR, Banks.EDGEVILLE, Banks.AL_KHARID};

    private Area[] depositBoxLocations = new Area[]{new Area(3044, 3236, 3047, 3235)};

    private Area[] combinedArrays;

    private BankUtils() { }

    public static BankUtils getInstance() {
        return instance;
    }

    public static void initializeInstance(Bot bot) {
        if (instance == null) {
            instance = new BankUtils();
            instance.exchangeContext(bot);
        }
    }

    public boolean walkToNearestBank() {
        return getWalking().webWalk(banks);
    }

    public boolean walkToNearestDepositSpot() {
        if (combinedArrays == null)
            combinedArrays = ArrayUtils.getInstance().addAreaArrays(banks, depositBoxLocations);
        return getWalking().webWalk(combinedArrays);
    }

    public boolean openBankOrDepositBox() throws InterruptedException {
        return getBank().open() || getDepositBox().open();
    }

    public boolean bankOrDepositBoxIsOpen() {
        return getBank().isOpen() || getDepositBox().isOpen();
    }

    public boolean bankOrDepositBoxDepositAllExcept(String... itemNames) {
        return getBank().depositAllExcept(itemNames) || getDepositBox().depositAllExcept(itemNames);
    }

    public boolean closeBankOrDepositBox() {
        return getBank().close() || getDepositBox().close();
    }

    public boolean retrieveItemFromBank(String itemName, int amount, boolean closeBank) throws InterruptedException {
        if (getBank().isOpen() && getBank().contains(itemName) && getBank().withdraw(itemName, amount)) {
            if (closeBank) getBank().close();
        } else if (!getBank().isOpen()) {
            if (getBank().open()) {
                retrieveItemFromBank(itemName, amount, closeBank);
            } else {
                if (walkToNearestBank()) {
                    retrieveItemFromBank(itemName, amount, closeBank);
                } else {
                    log("Unable to walk to the closest bank.");
                    getBot().getScriptExecutor().stop(false);
                    return false;
                }
            }
        } else if (!getBank().contains(itemName) || !getBank().withdraw(itemName, amount)) {
            if (getBank().close()) {
                log("Bank does not contain required fishing tools.");
                getBot().getScriptExecutor().stop(false);
                return false;
            }
        }
        return getInventory().contains(itemName);
    }

    public boolean retrieveAllItemFromBank(String itemName, boolean closeBank) throws InterruptedException {
        if (getBank().isOpen() && getBank().contains(itemName) && getBank().withdrawAll(itemName)) {
            if (closeBank) getBank().close();
            return true;
        } else if (!getBank().isOpen()) {
            if (getBank().open()) {
                retrieveAllItemFromBank(itemName, closeBank);
            } else {
                if (walkToNearestBank()) {
                    retrieveAllItemFromBank(itemName, closeBank);
                } else {
                    log("Unable to walk to the closest bank.");
                    getBot().getScriptExecutor().stop(false);
                    return false;
                }
            }
        } else if (!getBank().contains(itemName) || !getBank().withdrawAll(itemName)) {
            if (getBank().close()) {
                log("Bank does not contain required fishing tools.");
                getBot().getScriptExecutor().stop(false);
                return false;
            }
        }
        return getInventory().contains(itemName);
    }

    public boolean depositAllItemsExcept(String... itemsToKeep) throws InterruptedException {
        if (bankOrDepositBoxIsOpen() && bankOrDepositBoxDepositAllExcept(itemsToKeep)) {
            closeBankOrDepositBox();
            return getInventory().isEmptyExcept(itemsToKeep);
        } else if (!bankOrDepositBoxIsOpen()) {
            if (openBankOrDepositBox()) {
                depositAllItemsExcept(itemsToKeep);
            } else {
                if (walkToNearestDepositSpot()) {
                    depositAllItemsExcept(itemsToKeep);
                } else {
                    log("Unable to walk to the closest deposit spot.");
                    getBot().getScriptExecutor().stop(false);
                    return false;
                }
            }
        }
        return false;
    }
}
