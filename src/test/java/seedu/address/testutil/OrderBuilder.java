package seedu.address.testutil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.order.Order;
import seedu.address.model.order.OrderNumber;
import seedu.address.model.order.Status;
import seedu.address.model.person.Person;


/**
 * A utility class to help with building Person objects.
 */
public class OrderBuilder {

    public static final String DEFAULT_ORDER_NUMBER = "1";
    public static final Set<String> DEFAULT_MEDICINES = new HashSet<>(List.of("Panadol"));
    public static final String DEFAULT_STATUS = "PENDING";
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withAllergies("Aspirin")
            .build();

    private OrderNumber orderNumber;
    private Person person;
    private Set<String> medicines;
    private Status status;
    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public OrderBuilder() {
        orderNumber = new OrderNumber(DEFAULT_ORDER_NUMBER);
        person = ALICE;
        medicines = DEFAULT_MEDICINES;
        status = new Status(Status.toOrderStatus(DEFAULT_STATUS));
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        orderNumber = orderToCopy.getOrderNumber();
        person = orderToCopy.getPerson();
        medicines = orderToCopy.getMedicines();
        status = orderToCopy.getStatus();
    }
    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public OrderBuilder withStatus(String status) {
        this.status = new Status(Status.toOrderStatus(status));
        return this;
    }
    public Order build() {
        return new Order(orderNumber, person, medicines, status);
    }
}
