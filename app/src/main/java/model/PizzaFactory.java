package model;

/**
 * Interface defining the factory methods for creating different types of pizzas
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public interface PizzaFactory {

    /**
     * Creates a Deluxe pizza with style crust
     * @return a Deluxe pizza of the specific style
     */
    Pizza createDeluxe();

    /**
     * Creates a Meatzza pizza with style crust
     * @return a Meatzza pizza of the specific style
     */
    Pizza createMeatzza();

    /**
     * Creates a BBQ Chicken pizza with style crust
     * @return a BBQ Chicken pizza of the specific style
     */
    Pizza createBBQChicken();

    /**
     * Creates a Build Your Own pizza with style crust
     * @return a Build Your Own pizza of the specific style
     */
    Pizza createBuildYourOwn();
}