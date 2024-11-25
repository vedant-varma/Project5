package model;

import java.util.ArrayList;

/**
 * Represents a pizza order with a unique order number and list of pizzas
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class Order {
    /**
     * Number associated with the order.
     */
    private int number;

    /**
     * Arraylist of pizzas associated with the order
     */
    private ArrayList<Pizza> pizzas;

    /**
     * The total number of Orders created thus far, static and only goes up.
     */
    private static int orderNumberCount = 1;

    /**
     * NJ tax rate: 6.625%
     */
    private static final double TAX_RATE = 0.06625;

    /**
     * Creates a new order with a unique order number
     * For each order, the order number always increases
     */
    public Order() {
        // Set the number of the current order
        this.number = orderNumberCount;

        // Increment order number for next order
        orderNumberCount ++;

        this.pizzas = new ArrayList<>();
    }

    /**
     * Adds a pizza to the order
     * @param pizza the pizza to add to the order
     */
    public void addPizzaToOrder(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a pizza from the order
     * @param pizza the pizza to remove
     * @return true if pizza was removed, if not false is returned
     */
    public boolean removePizzaFromOrder(Pizza pizza) {
        return pizzas.remove(pizza);
    }

    /**
     * Gets this order's number
     * @return the order number for this order
     */
    public int getOrderNumber() {
        return number;
    }

    /**
     * Gets the list of pizzas in the order so far
     * //TODO: MIGHT NEED TO CHANGE TO OBSERVABLE LIST FOR UI
     * @return ArrayList of pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return this.pizzas;
    }

    /**
     * Calculates the subtotal of all pizzas before tax
     * Used to display the order total in parts
     * @return the order subtotal for the orders thus far
     */
    public double getSubtotal() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }
        return total;
    }

    /**
     * Calculates the tax amount for the order
     * Used to display the order total in parts
     * @return the tax amount for the orders thus far
     */
    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    /**
     * Calculates the total price including tax
     * Combines the subtotal and tax amount
     * @return the order total of the order thus far
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }


    /**
     * Removes all the order thus far
     */
    public void clearAllOrders() {
        pizzas.clear();
    }

    /**
     * Creates a string representation of the order
     * @return string with order details
     */
    @Override
    public String toString() {
        return "" + this.number;
    }
}