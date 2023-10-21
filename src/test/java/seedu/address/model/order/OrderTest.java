package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

class OrderTest {
    private final Order order = new Order(new OrderNumber("1"), ALICE, "panadol");
    @Test
    public void toStringMethod() {
        String expected = Order.class.getCanonicalName() + "{orderNumber=" + order.getOrderNumber()
                + ", person=" + order.getPerson().toString()
                + ", medicineName=" + order.getMedicineName() + "}";
        assertEquals(expected, order.toString());
    }

    @Test
    public void equals() {

        //same object -> returns true
        assertTrue(order.equals(order));

        //null -> returns false
        assertFalse(order.equals(null));

        Order newOrder = new Order(new OrderNumber("1"), ALICE, "panadol");

        //all attributes same -> return true
        assertTrue(order.equals(newOrder));
    }

}
