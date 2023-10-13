package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDERNUMBER;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * A class which represents the adding of order command.
 */
public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to the person identified "
            + "by the index number used in the displayed person list. \n "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ORDERNUMBER + "ORDERNUMBER] "
            + "[" + PREFIX_MEDICINENAME + "MEDICINENAME] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ORDERNUMBER + "91234567 "
            + PREFIX_MEDICINENAME + "panadol";

    private final Index index;
    private final String orderNumber;
    private final String medicineName;


    /**
     * @param index of the person in the filtered person to edit.
     * @param orderNumber of the order.
     * @param medicineName represents the name of medicine.
     */
    public AddOrderCommand(Index index, String orderNumber, String medicineName) {
        requireAllNonNull(index, orderNumber, medicineName);
        this.index = index;
        this.orderNumber = orderNumber;
        this.medicineName = medicineName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Integer i;
        try {
            i = Integer.parseInt(orderNumber);
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid ordernumber");
        }

        Person person = lastShownList.get(index.getZeroBased());
        model.addOrder(new Order(i, person, medicineName));

        return new CommandResult("Order added");
    }
}
