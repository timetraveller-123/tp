package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class ViewOrderCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validOrderNumber_orderIsFound() {
        int orderNumber = 1234;
        ViewOrderCommand command = new ViewOrderCommand(orderNumber);
        Order expectedOrder = model.getAddressBook().getOrder(orderNumber).get();
        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewOrderCommand.MESSAGE_VIEW_ORDER_SUCCESS, orderNumber),
                false,
                false,
                expectedOrder
        );
        assertCommandSuccess(command, model, expectedCommandResult, model);
    }

    @Test
    public void execute_nonExistentOrderNumber_throwsCommandException() {
        int orderNumber = 51920341;
        ViewOrderCommand command = new ViewOrderCommand(orderNumber);
        assertCommandFailure(command, model, String.format(ViewOrderCommand.MESSAGE_ORDER_NOT_FOUND, orderNumber));
    }
}