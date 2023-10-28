package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderNumber;
import seedu.address.model.person.Person;


/**
 * Jackson-friendly version of {@link Order}.
 */
public class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final String orderNumber;

    private final JsonAdaptedPerson person;

    private final String medicineName;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("orderNumber") String orderNumber,
                            @JsonProperty("person") JsonAdaptedPerson person,
                            @JsonProperty("medicineName") String medicineName) {
        this.orderNumber = orderNumber;
        this.person = person;
        this.medicineName = medicineName;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order order) {
        this.orderNumber = order.getOrderNumber().value;
        this.person = new JsonAdaptedPerson(order.getPerson());
        this.medicineName = order.getMedicineName();
    }


    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {

        if (person == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        final Person p = person.toModelType();


        if (orderNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderNumber.class.getSimpleName()));
        }

        if (!OrderNumber.isValidOrderNumber(orderNumber)) {
            throw new IllegalValueException(OrderNumber.MESSAGE_CONSTRAINTS);
        }

        if (medicineName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "medicineName"));
        }

        return new Order(new OrderNumber(orderNumber), p, medicineName);
    }


}
