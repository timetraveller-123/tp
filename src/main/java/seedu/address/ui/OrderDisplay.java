package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private Order order;
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/OrderDisplay.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String test = " Enter An order to view the details";
        orderNumber.setText(test);
    }

    /**
     * Shows the order on the UI.
     *
     * @param order The order that will be displayed
     */
    public void showOrder(Order order) {
        requireNonNull(order);
        String display = order.toString();
        orderNumber.setText(display);
    }

}
