package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());

        assert model.getAddressBook().getPersonList().isEmpty() : "PharmHub PersonList should be empty";
        assert model.getAddressBook().getMedicineList().isEmpty() : "PharmHub MedicineList should be empty";
        assert model.getAddressBook().getOrderList().isEmpty() : "PharmHub OrderList should be empty";

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
