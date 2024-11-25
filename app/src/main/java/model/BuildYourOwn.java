package model;

/**
 * Represents a Build Your Own pizza where customers can add their chosen toppings (up to 7 total)
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class BuildYourOwn extends Pizza {
    // Represents the costs for each size this pizza could be
    // Each cost is in $
    /**
     * Price for a Small BYO Chicken Pizza
     */
    private static final double SMALL_PRICE = 8.99;

    /**
     * Price for a Medium BYO Chicken Pizza
     */
    private static final double MEDIUM_PRICE = 10.99;

    /**
     * Price for a Large BYO Chicken Pizza
     */
    private static final double LARGE_PRICE = 12.99;

    /**
     * Cost for each Pizza topping
     */
    private static final double PRICE_PER_TOPPING = 1.69;

    /**
     * Creates a Build Your Own pizza with a provided crust type and size
     * @param crust the type of crust for this pizza
     */
    public BuildYourOwn(Crust crust) {
        super(crust);
    }

    /**
     * Calculates the price based on size and number of toppings
     * gets total toppings from parents class to determine price
     * @return the price of the pizza for the size and toppings
     */
    @Override
    public double price() {
        // Get the price just for the size
        double basePrice;
        switch (getSize()) {
            case SMALL:
                basePrice = SMALL_PRICE;
                break;
            case MEDIUM:
                basePrice = MEDIUM_PRICE;
                break;
            case LARGE:
                basePrice = LARGE_PRICE;
                break;
            default:
                throw new IllegalArgumentException();
        }

        // Return the price of the pizza which is the price of the size added to the cost of all toppings
        return basePrice + (super.getToppings().size() * PRICE_PER_TOPPING);
    }
}