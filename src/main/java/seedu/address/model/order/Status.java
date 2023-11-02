package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represents a Status of a order.
 * Guarantees: Status is valid as declared in {@link #isValidOrderStatus(OrderStatus)}
 */
public class Status {
    /**
     * Represents the status that are available for the orders.
     */
    public enum OrderStatus {
        PENDING,
        PREPARING,
        COMPLETED,
        CANCELLED
    }

    public static final String MESSAGE_CONSTRAINTS = "Order Status can only be"
            + OrderStatus.PENDING + ("/PD, ")
            + OrderStatus.PREPARING + ("/PR, ")
            + OrderStatus.COMPLETED + ("/CP, ")
            + OrderStatus.CANCELLED + ("/CC");
    private final OrderStatus orderStatus;

    /**
     * Constructs a {@code Status}.
     *
     * @param orderStatus A valid status for an order.
     */
    public Status(OrderStatus orderStatus) {
        requireNonNull(orderStatus);
        assert isValidOrderStatus(orderStatus)
                : "OrderStatus can only be PENDING/PREPARING/COMPLETED/OTHERS";
        checkArgument(isValidOrderStatus(orderStatus), MESSAGE_CONSTRAINTS);
        this.orderStatus = orderStatus;
    }

    /**
     * Gets the OrderStatus of the Status.
     *
     * @return The orderStatus.
     */
    public OrderStatus getStatus() {
        return orderStatus;
    }
    /**
     * Converts String to order Status.
     *
     * @param status The status of the order.
     */
    public static OrderStatus toOrderStatus(String status) {
        assert isValidOrderStatus(status)
                : "OrderStatus can only be PENDING/PREPARING/COMPLETED/OTHERS";
        checkArgument(isValidOrderStatus(status), MESSAGE_CONSTRAINTS);
        for (OrderStatus validStatus : OrderStatus.values()) {
            if (validStatus.toString().equals(status.toUpperCase())) {
                return validStatus;
            }
        }
        return null;
    }
    /**
     * Returns true if a given orderStatus is a valid status.
     */
    public static boolean isValidOrderStatus(OrderStatus orderStatus) {
        for (OrderStatus validStatus : OrderStatus.values()) {
            if (orderStatus == validStatus) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns true if a given orderStatus is a valid status.(For String)
     */
    public static boolean isValidOrderStatus(String orderStatus) {
        for (OrderStatus validStatus : OrderStatus.values()) {
            if (Objects.equals(validStatus.toString(), orderStatus)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns where the status input is in chronological order.
     *
     * @param oldStatus The old Status.
     * @param newStatus The new Status.
     * @return Whether the New Status is valid.
     */
    public static boolean isValidChronologicalStatus(OrderStatus oldStatus, OrderStatus newStatus) {
        switch (oldStatus) {
        case PENDING:
            return newStatus != OrderStatus.PENDING;

        case PREPARING:
            return !(newStatus == OrderStatus.PENDING
                    || newStatus == OrderStatus.PREPARING);

        case COMPLETED:
            return !(newStatus == OrderStatus.PENDING
                    || newStatus == OrderStatus.PREPARING
                        || newStatus == OrderStatus.COMPLETED);

        case CANCELLED:
            return false;

        default:
            return true;
        }
    }

    /**
     * Returns a OrderStatus from the short form provided.
     *
     * @param shortForm The short form given.
     * @return The orderStatus it converted to.
     * @throws CommandException If the short form provided is not valid.
     */
    public OrderStatus shortFormToFull(String shortForm) throws CommandException {
        requireNonNull(shortForm);
        assert !shortForm.equals("") : "short form should not be empty";
        String sf = shortForm.toLowerCase().trim();
        switch (sf) {
        case "pd":
            return OrderStatus.PENDING;

        case "pr":
            return OrderStatus.PREPARING;

        case "cp":
            return OrderStatus.COMPLETED;

        case "cc":
            return OrderStatus.CANCELLED;

        default:
            throw new CommandException(MESSAGE_CONSTRAINTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Status)) {
            return false;
        }

        Status otherAllergy = (Status) other;
        return orderStatus.equals(otherAllergy.orderStatus);
    }

    @Override
    public int hashCode() {
        return orderStatus.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return orderStatus.toString();
    }

}
