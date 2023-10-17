package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;


/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class OrderDisplay extends UiPart<Region> {

    private static final String FXML = "OrderDisplay.fxml";

    private final Person testJane = new Person(new Name("Jane"), new Phone("98765432"),
            new Email("jane@mail.com"), new Address("woodlands"), null );
    private final Order order = new Order(2, testJane, "panadol");
    @FXML
    private TextArea resultDisplay;
    @FXML
    private Label orderNumber;
    @FXML
    private Label orderPerson;
    @FXML
    private Label orderDetails;

    /**
     * Creates the order display.
     */
    public OrderDisplay() {
        super(FXML);
        orderNumber.setText(order.toString());
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

}
