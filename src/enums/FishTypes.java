package enums;

public enum FishTypes {
    SHRIMP(
            false,
            1,
            new ToolTypes[]{ToolTypes.SMALL_FISHING_NET},
            new Locations[]{Locations.LUMBRIDGE_SWAMP, Locations.AL_KHARID}),

    SARDINE(
            false,
            5,
            new ToolTypes[]{ToolTypes.FISHING_ROD},
            new Locations[]{Locations.LUMBRIDGE_SWAMP, Locations.AL_KHARID}),

    HERRING(
            false,
            10,
            new ToolTypes[]{ToolTypes.FISHING_ROD},
            new Locations[]{Locations.LUMBRIDGE_SWAMP, Locations.AL_KHARID}),

    ANCHOVIES(
            false,
            15,
            new ToolTypes[]{ToolTypes.SMALL_FISHING_NET},
            new Locations[]{Locations.AL_KHARID, Locations.LUMBRIDGE_SWAMP}),

    TROUT(
            false,
            20,
            new ToolTypes[]{ToolTypes.FLY_FISHING_ROD},
            new Locations[]{Locations.BARBARIAN_VILLAGE}),

    PIKE(
            false,
            25,
            new ToolTypes[]{ToolTypes.FISHING_ROD},
            new Locations[]{Locations.LUMBRIDGE_RIVER}),

    SALMON(
            false,
            30,
            new ToolTypes[]{ToolTypes.FLY_FISHING_ROD},
            new Locations[]{Locations.BARBARIAN_VILLAGE}),

    TUNA(
            false,
            35,
            new ToolTypes[]{ToolTypes.HARPOON, ToolTypes.DRAGON_HARPOON},
            new Locations[]{Locations.KARAMJA}),

    LOBSTER(
            false,
            40,
            new ToolTypes[]{ToolTypes.LOBSTER_POT},
            new Locations[]{Locations.KARAMJA}),

    SWORDFISH(
            false,
            50,
            new ToolTypes[]{ToolTypes.HARPOON, ToolTypes.DRAGON_HARPOON},
            new Locations[]{Locations.KARAMJA});

    boolean isMembers;
    int levelRequired;
    ToolTypes[] toolTypes;
    Locations[] supportedLocations;

    FishTypes(boolean isMembers, int levelRequired, ToolTypes[] toolTypes, Locations[] supportedLocations) {
        this.isMembers = isMembers;
        this.levelRequired = levelRequired;
        this.toolTypes = toolTypes;
        this.supportedLocations = supportedLocations;
    }

    public boolean isMembers() {
        return isMembers;
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
