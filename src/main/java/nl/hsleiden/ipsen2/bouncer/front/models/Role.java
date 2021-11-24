package nl.hsleiden.ipsen2.bouncer.front.models;

public enum Role {
    WORKER(0),
    MODERATOR(1),
    SITE_ADMIN(2),
    ADMIN(3);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
