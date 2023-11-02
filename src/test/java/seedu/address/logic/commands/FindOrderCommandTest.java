package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalOrders.ASPIRIN_MEDICINE;
import static seedu.address.testutil.TypicalOrders.PANADOL_MEDICINE;
import static seedu.address.testutil.TypicalOrders.PARACETAMOL_MEDICINE;
import static seedu.address.testutil.TypicalOrders.STATUS_COMPLETED;
import static seedu.address.testutil.TypicalOrders.STATUS_PENDING;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.medicine.Medicine;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Set<Medicine> medicineSetPanadol = new HashSet<>();
    private Set<Medicine> medicineSetAspirin = new HashSet<>();
    @Test
    public void equals() {
        medicineSetPanadol.add(PANADOL_MEDICINE);
        medicineSetPanadol.add(PARACETAMOL_MEDICINE);
        medicineSetAspirin.add(ASPIRIN_MEDICINE);
        FindOrderCommand findPendingCommand = new FindOrderCommand(STATUS_PENDING, medicineSetPanadol);
        FindOrderCommand findCompletedCommand = new FindOrderCommand(STATUS_COMPLETED, medicineSetPanadol);
        FindOrderCommand findPanadolCommand = new FindOrderCommand(STATUS_COMPLETED, medicineSetPanadol);
        FindOrderCommand findAspirinCommand = new FindOrderCommand(STATUS_COMPLETED, medicineSetAspirin);

        // same object -> returns true
        assertTrue(findPendingCommand.equals(findPendingCommand));

        // same values -> returns true
        FindOrderCommand findPendingCommandCopy = new FindOrderCommand(STATUS_PENDING, medicineSetPanadol);
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
