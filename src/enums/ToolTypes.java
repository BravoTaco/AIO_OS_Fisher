package enums;

import helpers.StringUtil;

public enum ToolTypes {
    SMALL_FISHING_NET(false, false, "Small fishing net", "", "Net"),
    FISHING_ROD(false, true, "Fishing rod", "Fishing bait", "Bait"),
    FLY_FISHING_ROD(false, true, "Fly fishing rod", "Feather", "Bait"),
    HARPOON(false, false, "Harpoon", "", "Harpoon"),
    DRAGON_HARPOON(true, false, "Dragon harpoon", "", "Harpoon"),
    LOBSTER_POT(false, false, "Lobster pot", "", "Cage");

    boolean isMembers, requiresBait;
    String toolName, baitName, action;

    ToolTypes(boolean isMembers, boolean requiresBait, String toolName, String baitName, String action) {
        this.isMembers = isMembers;
        this.requiresBait = requiresBait;
        this.toolName = toolName;
        this.baitName = baitName;
        this.action = action;
    }

    public boolean isMembers() {
        return isMembers;
    }

    public boolean requiresBait() {
        return requiresBait;
    }

    public String getToolName() {
        return toolName;
    }

    public String getBaitName() {
        return baitName;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return StringUtil.convertCapitalStringToNormal(this.name(), true);
    }
}
