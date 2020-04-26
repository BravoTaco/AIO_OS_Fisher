package helpers;

import data.StoredInformation;
import org.osbot.rs07.Bot;
import org.osbot.rs07.script.MethodProvider;

import java.io.*;

public class SaveLoadUtil extends MethodProvider {
    private static SaveLoadUtil instance;
    private String playerName, dirPath;

    private SaveLoadUtil() {

    }

    public static void initializeInstance(Bot bot) {
        if (instance == null) {
            instance = new SaveLoadUtil();
            instance.exchangeContext(bot);
            instance.playerName = instance.myPlayer().getName();
            instance.dirPath = instance.getBot().getScriptExecutor().getCurrent().getDirectoryData() + "\\AIO OS Fisher";
        }
    }

    public static SaveLoadUtil getInstance() {
        return instance;
    }

    public boolean save(StoredInformation storedInformation) {
        try {
            if (directoryExists() && fileExists()) {
                return saveFile(storedInformation);
            } else if (!fileExists() && createFile()) {
                if (!directoryExists() && makeDirectory()) {
                    return saveFile(storedInformation);
                }
                return saveFile(storedInformation);
            } else if (!directoryExists() && makeDirectory()) {
                if (!fileExists() && createFile()) {
                    return saveFile(storedInformation);
                }
                return saveFile(storedInformation);
            }
        } catch (Exception e) {
            log(e);
            return false;
        }
        return false;
    }

    public StoredInformation loadFile() {
        try {
            FileInputStream fis = new FileInputStream(dirPath + "\\" + playerName + " Save Data.save");
            ObjectInputStream ois = new ObjectInputStream(fis);
            StoredInformation storedInformation = (StoredInformation) ois.readObject();
            if (storedInformation != null) {
                log("Loaded save file...");
                return storedInformation;
            } else {
                return null;
            }
        } catch (Exception e) {
            log(e);
            return null;
        }
    }

    private boolean saveFile(StoredInformation storedInformation) {
        try {
            FileOutputStream fos = new FileOutputStream(dirPath + "\\" + playerName + " Save Data.save");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(storedInformation);
            oos.close();
            fos.close();
            log("Saved file...");
            return true;
        } catch (Exception e) {
            log(e);
            return false;
        }
    }

    private boolean makeDirectory() {
        File file = new File(dirPath);
        return file.mkdir() || file.mkdirs();
    }

    private boolean directoryExists() {
        File file = new File(dirPath);
        return file.exists();
    }

    private boolean createFile() {
        File file = new File(dirPath + "\\" + playerName + " Save Data.save");
        try {
            return file.createNewFile();
        } catch (IOException e) {
            log(e);
            return false;
        }
    }

    private boolean fileExists() {
        File file = new File(dirPath + "\\" + playerName + " Save Data.save");
        return file.exists();
    }
}
