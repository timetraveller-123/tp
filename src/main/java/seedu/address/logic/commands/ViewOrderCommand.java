package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * Finds and displays the order with the specified order number.
 */
public class ViewOrderCommand extends Command {

    public static final String COMMAND_WORD = "vieworder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the order which has the specified order number "
            + "displays it on the info panel.\n"
            + "Parameters: ORDER_NUMBER\n"
            + "Example: " + COMMAND_WORD + " 12345";

    public static final String MESSAGE_ORDER_NOT_FOUND = "No order found with order number #%d";

    public static final String MESSAGE_VIEW_ORDER_SUCCESS = "Displayed Order #%d";

    private final int orderNumber;

    public ViewOrderCommand(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Order> orderOptional = model.getOrder(orderNumber);
        if (orderOptional.isEmpty()) {
                throw new CommandException(String.format(MESSAGE_ORDER_NOT_FOUND, orderNumber));
        } else {
            return new CommandResult(
                    String.format(MESSAGE_VIEW_ORDER_SUCCESS, orderNumber),
                    false,
                    false,
                    orderOptional.get()
            );
        }
    }




}
