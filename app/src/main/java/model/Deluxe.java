package model;

/**
 * Represents a Deluxe pizza with predefined toppings specified in the directions
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class Deluxe extends Pizza {
    // Represents the costs for each size this pizza could be
    // Each cost is in $
    /**
     * Price for a Small Deluxe Chicken Pizza
     */
    private static final double SMALL_PRICE = 16.99;

    /**
     * Price for a Medium Deluxe Chicken Pizza
     */
    private static final double MEDIUM_PRICE = 18.99;

    /**
     * Price for a Large Deluxe Chicken Pizza
     */
    private static final double LARGE_PRICE = 20.99;

    /**
     * Creates a Deluxe pizza with a provided crust type and size, adds standard toppings
     * @param crust the type of crust for this pizza
     */
    public Deluxe(Crust crust) {
        super(crust); // Uses default size
        addToppingToPizza(Topping.SAUSAGE);
        addToppingToPizza(Topping.PEPPERONI);
        addToppingToPizza(Topping.GREEN_PEPPER);
        addToppingToPizza(Topping.ONION);
        addToppingToPizza(Topping.MUSHROOM);
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