package model;

/**
 * Factory class for creating Chicago-style pizzas with associated crusts for this type of pizza
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Makes a Chicago-style Deluxe pizza (with Deep Dish crust as per directions)
     * @return a Deluxe pizza with Chicago-style crust
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEP_DISH);
    }

    /**
     * Makes a Chicago BBQ Chicken pizza (with Pan crust as per directions)
     * @return a BBQ Chicken pizza with Chicago-style crust
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN);
    }

    /**
     * Makes a Chicago Meatzza pizza (with Stuffed crust as per directions)
     * @return a Meatzza pizza with Chicago-style crust
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED);
    }

    /**
     * Creates a Chicago Build Your Own pizza (with Pan crust as per directions)
     * @return a Build Your Own pizza with Chicago-style crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN);
    }
}
