package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.order.Order;
import seedu.address.model.order.Status;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindOrderCommand extends Command {

    public static final String COMMAND_WORD = "findo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all orders whose order information contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Status statusToFind;
    private final Set<Medicine> medicineToFind;

    /**
     * Constructor method for the find order class.
     * @param statusToFind The status to find.
     * @param medicineToFind The Medicine to find.
     */
    public FindOrderCommand(Status statusToFind, Set<Medicine> medicineToFind) {
        this.statusToFind = statusToFind;
        this.medicineToFind = medicineToFind;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (statusToFind == null && medicineToFind == null) {
            return new CommandResult(FindOrderCommand.MESSAGE_USAGE);
        }

        Predicate<Order> statusMatches = order -> statusToFind == null
                || order.getStatus().getStatus() == statusToFind.getStatus();
        Predicate<Order> medicineMatches = order -> medicineToFind == null
                || Stream.of(medicineToFind)
                .anyMatch(medicineName ->
                        order.getMedicines().stream()
                                .anyMatch(orderMedicine ->
                                        medicineName.stream().anyMatch(
                                                orderMedicine::isSameMedicine)));

        Predicate<Order> combined = statusMatches.and(medicineMatches);

        model.updateFilteredOrderList(combined);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()),
                CommandResult.ListPanelEffects.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindOrderCommand)) {
            return false;
        }

        FindOrderCommand otherFindCommand = (FindOrderCommand) other;
        return statusToFind.equals(otherFindCommand.statusToFind)
                && medicineToFind.equals(otherFindCommand.medicineToFind);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("status", statusToFind)
                .add("medicine", medicineToFind)
                .toString();
    }
}
