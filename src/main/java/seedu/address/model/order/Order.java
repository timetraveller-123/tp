package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.InfoObject;
import seedu.address.model.person.Person;



/**
 * Represents an Order in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order implements InfoObject {
    private final OrderNumber orderNumber;
    private final Person person;
    private final Set<String> medicines = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Order(OrderNumber orderNumber, Person person, Set<String> medicines) {
        requireAllNonNull(orderNumber, person, medicines);
        this.orderNumber = orderNumber;
        this.person = person;
        this.medicines.addAll(medicines);
    }

    public OrderNumber getOrderNumber() {
        return orderNumber;
    }

    public Person getPerson() {
        return person;
    }

    public Set<String> getMedicines() {
        return Collections.unmodifiableSet(medicines);
    }


    /**
     * Returns true if both orders have the same order number.
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder != null
                && otherOrder.getOrderNumber().equals(getOrderNumber());
    }

    /**
     * Returns true if both orders have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return orderNumber.equals(otherOrder.orderNumber)
                && medicines.equals(otherOrder.medicines)
                && person.equals(otherOrder.person);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(orderNumber, person, medicines);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("orderNumber", orderNumber)
                .add("person", person)
                .add("medicines", medicines)
                .toString();
    }
}
