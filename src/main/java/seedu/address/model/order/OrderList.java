package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * A list of orders that does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 */
public class OrderList implements Iterable<Order> {
    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    /**
     * Adds an order to the list.
     */
    public void add(Order order) {
        requireNonNull(order);
        internalList.add(order);
    }

    /**
     * Gets an order from the orderList based on its orderNumber.
     * @param orderNumber
     * @return Order with specified orderNumber, if any.
     */
    public Optional<Order> getOrder(int orderNumber) {
        Stream<Order> filtered = internalList.stream().filter(order ->
                order.getOrderNumber().getIntValue() == orderNumber);
        return filtered.findFirst();
    }

    public void setOrders(OrderList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code orders}.
     */
    public void setOrders(List<Order> orders) {
        requireAllNonNull(orders);
        internalList.setAll(orders);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Order> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Order> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }


        // instanceof handles nulls
        if (!(other instanceof OrderList)) {
            return false;
        }

        OrderList orderList = (OrderList) other;
        return internalList.equals(orderList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}
