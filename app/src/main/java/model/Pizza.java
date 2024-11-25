package model;

import java.util.ArrayList;


/**
 * Abstract class representing pizza that could have different size, crust, and toppings
 * Behaves as the base class for all pizza on the menu
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public abstract class Pizza {
    /**
     * ArrayList of toppings associated with the specific Pizza
     */
    private ArrayList<Topping> toppings;

    /**
     * Crust of the given Pizza
     */
    private Crust crust;

    /**
     * Size associated with the given pizza
     */
    private Size size;

    /**
     * Represents the max number of toppings for a pizza as defined by the directions
     */
    protected static final int MAX_TOPPINGS = 7;

    /**
     * Parameterized constructor for the Pizza class
     * @param crust the type of crust for the pizza
     * @param size the size of the crust for the pizza
     */
    public Pizza(Crust crust, Size size) {
        this.crust = crust;
        this.toppings = new ArrayList<>();
        this.size = size;
    }

    /**
     * Constructor with only crust specified for pizza
     * Defaults to SMALL
     * @param crust the crust type of the pizza
     */
    public Pizza(Crust crust) {
        this(crust, Size.SMALL);
    }

    /**
     * Sets the size of the pizza
     * @param size the size to set the pizza
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Gets the current size of the pizza
     * @return the current size of the pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * Gets the crust type of the pizza
     * @return the crust type of the pizza
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Gets the list of toppings on the pizza
     * @return ArrayList of toppings for the pizza
     */
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    /**
     * Adds a topping to the pizza if maximum not reached
     * @param topping the topping to attempt to add
     * @return true if topping was added, false if maximum toppings reached and cannot add
     */
    public boolean addToppingToPizza(Topping topping) {
        if (toppings.size() < MAX_TOPPINGS) {
            return toppings.add(topping);
        }
        return false;
    }

    /**
     * Removes a topping from the pizza
     * @param topping the topping to remove (if it exists)
     * @return true if topping was removed, false if topping was not on pizza
     */
    public boolean removeToppingFromPizza(Topping topping) {
        return toppings.remove(topping);
    }

    /**
     * Calculates the price of the pizza based on size, toppings, etc
     * @return the calculated price of the pizza
     */
    public abstract double price();

    /**
     * String representation of a Pizza object.
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s %s Pizza With %s, $%.2f",
                size,
                crust,
                toppings.toString(),
                price());
    }
}
