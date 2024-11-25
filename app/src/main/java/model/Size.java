package model;

/**
 * Enum that represents the different sizes a pizza could be
 * There are currently three offered sizes: small, medium, and large
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public enum Size {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    /**
     * SMALL, MEDIUM, LARGE
     */
    private final String name;

    /**
     * Constructs a Size constant with a name
     * @param name The name of the size
     */
    Size(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the size as a string
     * @return The name of the size
     */
    @Override
    public String toString() {
        return name;
    }
}