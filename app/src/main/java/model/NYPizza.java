package model;

/**
 * Factory class for creating New York-style pizzas with associated crusts for this type of pizza
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a NY Deluxe pizza (with Brooklyn crust as per directions)
     * @return a Deluxe pizza with NY-style crust
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN);
    }

    /**
     * Creates a NY BBQ Chicken pizza (with Thin crust as per directions)
     * @return a BBQ Chicken pizza with NY-style crust
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN);
    }

    /**
     * Creates a NY Meatzza pizza (with Hand Tossed crust as per directions)
     * @return a Meatzza pizza with NY-style crust
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HAND_TOSSED);
    }

    /**
     * Creates a NY Build Your Own pizza (with Hand-Tossed crust as per directions)
     * @return a Build Your Own pizza with NY-style crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HAND_TOSSED);
    }
}