package jt.portfoilo.tourservice.domain;

public enum RoomTypeEnum {

    SINGLE_ROOM("Single Room"),
    TWO_PER_ROOM("Two-Person Room"),
    THREE_PER_ROOM("Three-Person Room"),
    FOUR_PER_ROOM("Four-Person Room");

    private String roomSpec;

    RoomTypeEnum(String roomSpec) {
        this.roomSpec = roomSpec;
    }

    public String spec() {
        return this.roomSpec;
    }
}
