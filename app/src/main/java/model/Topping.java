package model;

/**
 * Enum that represents the different toppings a pizza could have
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public enum Topping {
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREEN_PEPPER("Green Pepper"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    BBQ_CHICKEN("BBQ Chicken"),
    PROVOLONE("Provolone"),
    BEEF("Beef"),
    HAM("Ham"),
    CHEDDAR("Cheddar"),
    PINEAPPLE("Pineapple"),
    BLACK_OLIVE("Black Olive"),
    SPINACH("Spinach");

    /**
     * Name of the topping.
     */
    private final String name;

    /**
     * Constructs a Topping constant with a name
     * @param name The name of the topping
     */
    Topping(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the topping as a string
     * @return The name of the topping
     */
    @Override
    public String toString() {
        return name;
    }
}