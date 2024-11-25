package model;

/**
 * Represents a BBQ Chicken pizza with predefined toppings specified in the directions
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class BBQChicken extends Pizza {
    // Represents the costs for each size this pizza could be
    // Each cost is in $
    /**
     * Price for a Small BBQ Chicken Pizza
     */
    private static final double SMALL_PRICE = 14.99;
    /**
     * Price for a Medium BBQ Chicken Pizza
     */
    private static final double MEDIUM_PRICE = 16.99;
    /**
     * Price for a Large BBQ Chicken Pizza
     */
    private static final double LARGE_PRICE = 19.99;

    /**
     * Creates a BBQ Chicken pizza with a provided crust type and standard toppings
     * @param crust the type of crust for this pizza
     */
    public BBQChicken(Crust crust) {
        super(crust); // Uses default SMALL size
        addToppingToPizza(Topping.BBQ_CHICKEN);
        addToppingToPizza(Topping.GREEN_PEPPER);
        addToppingToPizza(Topping.PROVOLONE);
        addToppingToPizza(Topping.CHEDDAR);
    }

    /**
     * Calculates the price based on the pizza size
     * @return the price of this pizza
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