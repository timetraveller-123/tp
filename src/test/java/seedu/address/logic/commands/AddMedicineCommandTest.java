package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.IBUPROFEN_MEDICINE;
import static seedu.address.testutil.TypicalOrders.PARACETAMOL_MEDICINE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.medicine.Medicine;


public class AddMedicineCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }


    @Test
    public void execute_newMedicine_success() {

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addMedicine(IBUPROFEN_MEDICINE);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(AddMedicineCommand.MESSAGE_SUCCESS, Messages.format(IBUPROFEN_MEDICINE)));

        assertCommandSuccess(new AddMedicineCommand(IBUPROFEN_MEDICINE), model,
                expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_duplicateMedicine_throwsCommandException() {
        Medicine medicineInList = model.getFilteredMedicineList().get(0);
        assertCommandFailure(new AddMedicineCommand(medicineInList), model,
                AddMedicineCommand.MESSAGE_DUPLICATE_MEDICINE);
    }

    @Test
    public void constructor_nullMedicine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMedicineCommand(null));
    }

    @Test
    public void equals() {
        AddMedicineCommand addIbuprofenCommand = new AddMedicineCommand(IBUPROFEN_MEDICINE);
        AddMedicineCommand addParacetamolCommand = new AddMedicineCommand(PARACETAMOL_MEDICINE);

        // same object -> returns true
        assertTrue(addParacetamolCommand.equals(addParacetamolCommand));

        // same values -> returns true
        AddMedicineCommand addParacetamolCommandCopy = new AddMedicineCommand(PARACETAMOL_MEDICINE);
        assertTrue(addParacetamolCommandCopy.equals(addParacetamolCommand));

        // different types -> returns false
        assertFalse(addParacetamolCommand.equals(1));

        // null -> returns false
        assertFalse(addIbuprofenCommand.equals(null));

        // different person -> returns false
        assertFalse(addIbuprofenCommand.equals(addParacetamolCommand));
    }

    @Test
    public void toStringMethod() {
        AddMedicineCommand addCommand = new AddMedicineCommand(IBUPROFEN_MEDICINE);
        String expected = AddMedicineCommand.class.getCanonicalName() + "{toAdd=" + IBUPROFEN_MEDICINE + "}";
        assertEquals(expected, addCommand.toString());
    }

}
