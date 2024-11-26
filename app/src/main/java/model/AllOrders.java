package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages all pizza orders for the store
 * Implements singleton pattern to make sure all classes share the same order data
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class AllOrders {
    private static AllOrders instance = null;
    private final List<Order> orders;
    private Order currentOrder;

    /**
     * Private constructor to prevent direct instantiation
     */
    private AllOrders() {
        orders = new ArrayList<>();
        currentOrder = new Order();
    }

    /**
     * Gets the singleton instance of AllOrders
     * @return the singleton instance
     */
    public static synchronized AllOrders getInstance() {
        if (instance == null) {
            instance = new AllOrders();
        }
        return instance;
    }

    /**
     * Gets all placed orders thus far
     * @return List of all orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Gets the current order that is not complete
     * @return the current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Completes the current order and adds it to placed orders
     * Creates a new current order for future use
     */
    public void placeCurrentOrder() {
        if (!currentOrder.getPizzas().isEmpty()) {
            orders.add(currentOrder);
            currentOrder = new Order();
        }
    }

    /**
     * Returns a specific order by its order number
     * @param orderNumber number associated with order
     * @return order of pizzas
     */
    public Order getOrderByNumber(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    /**
     * Cancels an existing order
     * @param order the order to cancel
     * @return true if order was found and canceled, otherwise return false
     */
    public boolean cancelOrder(Order order) {
        return orders.remove(order);
    }

    /**
     * Clears all orders and creates a new current order
     * Used when resetting the app state
     */
    public void clearAllOrders() {
        orders.clear();
        currentOrder = new Order();
    }
}