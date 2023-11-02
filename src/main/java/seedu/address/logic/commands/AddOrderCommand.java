package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_NUMBER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderNumber;
import seedu.address.model.order.Status;
import seedu.address.model.person.Person;

/**
 * A class which represents the adding of order command.
 */
public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to the person identified "
            + "by the index number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ORDER_NUMBER + "ORDER_NUMBER "
            + PREFIX_MEDICINE_NAME + "MEDICINE_NAME+ \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ORDER_NUMBER + "91234567 "
            + PREFIX_MEDICINE_NAME + "panadol "
            + PREFIX_MEDICINE_NAME + "ibu";

    public static final String MESSAGE_SUCCESS = "New order added: #%1$s";

    public static final String MESSAGE_DUPLICATE_ORDER = "Order number '%1$s' already exists in PharmHub";

    private final Index index;
    private final OrderNumber orderNumber;
    private final Set<Medicine> medicines;

    private final Boolean ignoreAllergy;
    private final Status orderStatus;


    /**
     * @param index        of the person in the filtered person to edit.
     * @param orderNumber  of the order.
     * @param medicines    the set of medicines for this order
     */
    public AddOrderCommand(Index index, OrderNumber orderNumber, Set<Medicine> medicines, Boolean ignoreAllergy) {
        requireAllNonNull(index, orderNumber, medicines);
        this.index = index;
        this.orderNumber = orderNumber;
        this.medicines = medicines;
        this.ignoreAllergy = ignoreAllergy;
        this.orderStatus = new Status(Status.OrderStatus.PENDING);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Set<Medicine> convertedMedicines = CommandUtil.getModelMedicine(model, medicines);
        Person person = lastShownList.get(index.getZeroBased());
        Order toAdd = new Order(orderNumber, person, convertedMedicines, orderStatus);

        if (model.hasOrder(toAdd)) {
            throw new CommandException(
                    String.format(MESSAGE_DUPLICATE_ORDER, orderNumber));
        }

        if (person.isAllergicToAny(convertedMedicines) && !ignoreAllergy) {
            throw new CommandException(Messages.MESSAGE_ALLERGIC_TO_MEDICINE);
        }

        model.addOrder(toAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)), toAdd);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddOrderCommand)) {
            return false;
        }

        AddOrderCommand otherAddCommand = (AddOrderCommand) other;
        return index.equals(otherAddCommand.index)
                && orderNumber.equals(otherAddCommand.orderNumber)
                && medicines.equals(otherAddCommand.medicines)
                && orderStatus.equals(otherAddCommand.orderStatus);
    }
}
