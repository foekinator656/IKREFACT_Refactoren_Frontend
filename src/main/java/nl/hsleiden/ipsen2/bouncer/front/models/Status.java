package nl.hsleiden.ipsen2.bouncer.front.models;

public enum Status {
    DECLINED(0),
    APPROVED(1),
    EXPIRED(2),
    ACTION_REQUIRED(3),
    PENDING(4);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
