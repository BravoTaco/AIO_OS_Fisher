package enums;


import org.osbot.rs07.api.map.Position;

public enum Locations {
    LUMBRIDGE_SWAMP(new Position(3242, 3151, 0)),
    LUMBRIDGE_RIVER(new Position(3241, 3246, 0)),
    AL_KHARID(new Position(3272, 3144, 0)),
    DRAYNOR_VILLAGE(new Position(3089, 3229, 0)),
    BARBARIAN_VILLAGE(new Position(3104, 3430, 0)),
    KARAMJA(new Position(2924, 3178, 0));

    Position position;

    Locations(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
