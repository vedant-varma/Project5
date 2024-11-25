package model;

/**
 * Represents a Meatzza pizza with predefined toppings specified in the directions
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class Meatzza extends Pizza {
    // Represents the costs for each size this pizza could be
    // Each cost is in $
    /**
     * Price for a Small Meatzza Chicken Pizza
     */
    private static final double SMALL_PRICE = 17.99;

    /**
     * Price for a Medium Meatzza Chicken Pizza
     */
    private static final double MEDIUM_PRICE = 19.99;

    /**
     * Price for a Large Meatzza Chicken Pizza
     */
    private static final double LARGE_PRICE = 21.99;

    /**
     * Creates a Meatzza pizza with a provided crust type and size, adds standard toppings
     * @param crust the type of crust for this pizza
     */
    public Meatzza(Crust crust) {
        super(crust); // Uses default SMALL size
        addToppingToPizza(Topping.SAUSAGE);
        addToppingToPizza(Topping.PEPPERONI);
        addToppingToPizza(Topping.BEEF);
        addToppingToPizza(Topping.HAM);
    }

    /**
     * Calculates the price based on the pizza size
     * @return the price of the pizza based on size
     */
    @Override
    public double price() {
        switch (getSize()) {
            case SMALL:
                return SMALL_PRICE;
            case MEDIUM:
                return MEDIUM_PRICE;
            case LARGE:
                return LARGE_PRICE;
            default:
                throw new IllegalArgumentException();
        }
    }
}