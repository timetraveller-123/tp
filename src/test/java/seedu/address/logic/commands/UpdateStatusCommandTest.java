package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalOrders.STATUS_COMPLETED;
import static seedu.address.testutil.TypicalOrders.STATUS_PENDING;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.UpdateStatusCommand.EditOrderDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderNumber;
import seedu.address.testutil.EditOrderDescriptorBuilder;
import seedu.address.testutil.OrderBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class UpdateStatusCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Order editedOrder = new OrderBuilder().withStatus("PREPARING").build();
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(editedOrder).build();
        UpdateStatusCommand updateStatusCommand = new UpdateStatusCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(
                UpdateStatusCommand.MESSAGE_EDIT_ORDER_STATUS_SUCCESS,
                Messages.formatStatus(editedOrder));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertCommandSuccess(updateStatusCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        UpdateStatusCommand.EditOrderDescriptor editOrderDescriptor =
                new UpdateStatusCommand.EditOrderDescriptor();
        UpdateStatusCommand updateStatusCommand = new UpdateStatusCommand(index, editOrderDescriptor);
        String expected = UpdateStatusCommand.class.getCanonicalName() + "{index=" + index + ", editOrderDescriptor="
                + editOrderDescriptor + "}";
        assertEquals(expected, updateStatusCommand.toString());
    }
    @Test
    public void execute_invalidIndex_throwsCommandException() {
        int outOfBoundIndex = INDEX_SECOND.getZeroBased() + 10;
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withStatus(STATUS_PENDING.toString())
                .build();
        UpdateStatusCommand updateStatusCommand =
                new UpdateStatusCommand(Index.fromZeroBased(outOfBoundIndex), descriptor);

        // Ensure the index provided is out of bounds
        assertCommandFailure(updateStatusCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }
    @Test
    public void isAnyFieldEdited_someFieldsEdited_returnsTrue() {
        EditOrderDescriptor descriptor = new EditOrderDescriptor();
        descriptor.setOrderNumber(new OrderNumber("1"));

        // At least one field in the descriptor is edited
        assertTrue(descriptor.isAnyFieldEdited());
    }
    @Test
    public void equals_sameDescriptor_returnsTrue() {
        EditOrderDescriptor descriptor = new EditOrderDescriptor();
        EditOrderDescriptor sameDescriptor = new EditOrderDescriptor();

        // Both descriptors have the same field values, so they are considered equal
        assertEquals(descriptor, sameDescriptor);
    }

    @Test
    public void equals_differentDescriptors_returnsFalse() {
        EditOrderDescriptor descriptor = new EditOrderDescriptor();
        EditOrderDescriptor differentDescriptor = new EditOrderDescriptor();
        differentDescriptor.setOrderNumber(new OrderNumber("1"));

        // The descriptors have different field values, so they are not considered equal
        assertNotEquals(descriptor, differentDescriptor);
    }
    @Test
    public void equals() {
        EditOrderDescriptor descriptor = new EditOrderDescriptor();
        EditOrderDescriptor otherDescriptor = new EditOrderDescriptor();
        otherDescriptor.setStatus(STATUS_COMPLETED);
        final UpdateStatusCommand standardCommand = new UpdateStatusCommand(INDEX_FIRST, descriptor);

        // same values -> returns true
        UpdateStatusCommand commandWithSameValues = new UpdateStatusCommand(INDEX_FIRST, descriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_FIRST, new EditCommand.EditPersonDescriptor()));

        // different index -> returns false
        assertNotEquals(standardCommand, new UpdateStatusCommand(INDEX_SECOND, descriptor));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new UpdateStatusCommand(INDEX_FIRST, otherDescriptor));
    }
}