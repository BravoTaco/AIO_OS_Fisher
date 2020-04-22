package enums;

public enum FishTypes {
    SHRIMP(
            false,
            false,
            1,
            new ToolTypes[]{ToolTypes.SMALL_FISHING_NET},
            new Locations[]{Locations.LUMBRIDGE_SWAMP, Locations.AL_KHARID}),

    SARDINE(
            false,
            false,
            5,
            new ToolTypes[]{ToolTypes.FISHING_ROD},
            new Locations[]{Locations.LUMBRIDGE_SWAMP, Locations.AL_KHARID}),

    HERRING(
            false,
            false,
            10,
            new ToolTypes[]{ToolTypes.FISHING_ROD},
            new Locations[]{Locations.LUMBRIDGE_SWAMP, Locations.AL_KHARID}),

    ANCHOVIES(
            false,
            false,
            15,
            new ToolTypes[]{ToolTypes.SMALL_FISHING_NET},
            new Locations[]{Locations.AL_KHARID, Locations.LUMBRIDGE_SWAMP}),

    TROUT(
            false,
            false,
            20,
            new ToolTypes[]{ToolTypes.FLY_FISHING_ROD},
            new Locations[]{Locations.BARBARIAN_VILLAGE}),

    PIKE(
            false,
            false,
            25,
            new ToolTypes[]{ToolTypes.FISHING_ROD},
            new Locations[]{Locations.LUMBRIDGE_RIVER}),

    SALMON(
            false,
            false,
            30,
            new ToolTypes[]{ToolTypes.FLY_FISHING_ROD},
            new Locations[]{Locations.BARBARIAN_VILLAGE}),

    TUNA(
            false,
            true,
            35,
            new ToolTypes[]{ToolTypes.HARPOON, ToolTypes.DRAGON_HARPOON},
            new Locations[]{Locations.KARAMJA}),

    LOBSTER(
            false,
            true,
            40,
            new ToolTypes[]{ToolTypes.LOBSTER_POT},
            new Locations[]{Locations.KARAMJA}),

    SWORDFISH(
            false,
            true,
            50,
            new ToolTypes[]{ToolTypes.HARPOON, ToolTypes.DRAGON_HARPOON},
            new Locations[]{Locations.KARAMJA});

    boolean isMembers;
    boolean requiresCoins;
    int levelRequired;
    ToolTypes[] toolTypes;
    Locations[] supportedLocations;

    FishTypes(boolean isMembers, boolean requiresCoins, int levelRequired, ToolTypes[] toolTypes, Locations[] supportedLocations) {
        this.isMembers = isMembers;
        this.requiresCoins = requiresCoins;
        this.levelRequired = levelRequired;
        this.toolTypes = toolTypes;
        this.supportedLocations = supportedLocations;
    }

    public boolean isMembers() {
        return isMembers;
    }

    public boolean requiresCoins() {
        return requiresCoins;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public ToolTypes[] getToolTypes() {
        return toolTypes;
    }

    public Locations[] getSupportedLocations() {
        return supportedLocations;
    }
}
