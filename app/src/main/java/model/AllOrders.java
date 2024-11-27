package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages all pizza orders for the store (All classes should share this list).
 * Holds a list of orders and handles operations like adding, removing,
 * and exporting orders to a text file.
 */
public class AllOrders {
    /**
     * Singleton instance of AllOrders.
     */
    private static AllOrders instance;

    /**
     * List of all placed orders.
     */
    private List<Order> orders;

    /**
     * Current ongoing order.
     */
    private Order currentOrder;

    /**
     * Private constructor to prevent instantiation from other classes.
     */
    private AllOrders() {
        orders = new ArrayList<>();
        currentOrder = new Order();
    }

    /**
     * Provides access to the singleton instance of AllOrders.
     * Uses lazy initialization and is thread-safe.
     *
     * @return the singleton instance of AllOrders.
     */
    public static synchronized AllOrders getInstance() {
        if (instance == null) {
            instance = new AllOrders();
        }
        return instance;
    }

    /**
     * Gets all placed orders thus far.
     *
     * @return List of all orders.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Gets the current order that is not complete.
     *
     * @return the current order.
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Completes the current order and adds it to placed orders.
     * Creates a new current order for future use to be added to.
     */
    public void placeCurrentOrder() {
        if (!currentOrder.getPizzas().isEmpty()) {
            // Add current order to the list of orders
            orders.add(currentOrder);
            // Create a new order for future use
            currentOrder = new Order();
        }
    }

    /**
     * Returns a specific order retrieved by its order number.
     *
     * @param orderNumber number associated with the order.
     * @return the order with the specified order number, or null if not found.
     */
    public Order getOrderByNumber(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null; // Return null if no order with the given number is found
    }

    /**
     * Cancels an existing order.
     *
     * @param order the order to cancel.
     * @return true if the order was found and canceled, otherwise false.
     */
    public boolean cancelOrder(Order order) {
        return orders.remove(order);
    }

}
