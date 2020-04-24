package enums;


import helpers.StringUtil;
import org.osbot.rs07.api.map.Position;

public enum Locations {
    LUMBRIDGE_SWAMP(new Position(3242, 3151, 0), false),
    LUMBRIDGE_RIVER(new Position(3241, 3246, 0), false),
    AL_KHARID(new Position(3272, 3144, 0), false),
    DRAYNOR_VILLAGE(new Position(3089, 3229, 0), false),
    BARBARIAN_VILLAGE(new Position(3104, 3430, 0), false),
    KARAMJA(new Position(2924, 3178, 0), true);

    Position position;
    boolean isCoinsRequired;

    Locations(Position position, boolean isCoinsRequired) {
        this.position = position; this.isCoinsRequired = isCoinsRequired;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isCoinsRequired() {
        return isCoinsRequired;
    }

    @Override
    public String toString() {
        return StringUtil.convertCapitalStringToNormal(this.name(), true);
    }
}
