package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalOrders.ASPIRIN_MEDICINE;
import static seedu.address.testutil.TypicalOrders.PANADOL_MEDICINE;
import static seedu.address.testutil.TypicalOrders.STATUS_COMPLETED;
import static seedu.address.testutil.TypicalOrders.STATUS_PENDING;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindOrderCommand findPendingCommand = new FindOrderCommand(STATUS_PENDING, PANADOL_MEDICINE);
        FindOrderCommand findCompletedCommand = new FindOrderCommand(STATUS_COMPLETED, PANADOL_MEDICINE);
        FindOrderCommand findPanadolCommand = new FindOrderCommand(STATUS_COMPLETED, PANADOL_MEDICINE);
        FindOrderCommand findAspirinCommand = new FindOrderCommand(STATUS_COMPLETED, ASPIRIN_MEDICINE);

        // same object -> returns true
        assertTrue(findPendingCommand.equals(findPendingCommand));

        // same values -> returns true
        FindOrderCommand findPendingCommandCopy = new FindOrderCommand(STATUS_PENDING, PANADOL_MEDICINE);
        assertTrue(findPendingCommand.equals(findPendingCommandCopy));

        // different types -> returns false
        assertFalse(findPendingCommand.equals(1));

        // null -> returns false
        assertFalse(findPanadolCommand.equals(null));

        // different status -> returns false
        assertFalse(findPendingCommand.equals(findCompletedCommand));

        // different medicine -> returns false
        assertFalse(findPanadolCommand.equals(findAspirinCommand));
    }
}
