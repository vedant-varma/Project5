package model;

/**
 * Enum that represents the different crusts a pizza could have
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public enum Crust {
    DEEP_DISH("Deep Dish"),
    PAN("Pan"),
    STUFFED("Stuffed"),
    BROOKLYN("Brooklyn"),
    THIN("Thin"),
    HAND_TOSSED("Hand Tossed");

    /**
     * Name of the type of Crust.
     */
    private final String name;

    /**
     * Constructs a Crust constant with a name
     * @param name The name of the crust
     */
    Crust(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the Crust as a string
     * @return The name of the crust
     */
    @Override
    public String toString() {
        return name;
    }
}