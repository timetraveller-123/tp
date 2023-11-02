package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medicine.Medicine;


/**
 * A class which represents the adding of a medicine.
 */
public class AddMedicineCommand extends Command {

    public static final String COMMAND_WORD = "addm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the given medicine \n"
            + "Parameters:  "
            + PREFIX_MEDICINE_NAME + "MEDICINENAME (Full name of Medicine) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEDICINE_NAME + "Panadol";

    public static final String MESSAGE_SUCCESS = "New medicine added: #%1$s";
    public static final String MESSAGE_DUPLICATE_MEDICINE = "This medicine already exists in the address book";

    private final Medicine toAdd;

    /**
     * Creates an AddMedicineCommand to add the specified {@code Medicine}
     */
    public AddMedicineCommand(Medicine medicine) {
        requireNonNull(medicine);
        toAdd = medicine;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasMedicine(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEDICINE);
        }

        model.addMedicine(toAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMedicineCommand)) {
            return false;
        }

        AddMedicineCommand otherAddCommand = (AddMedicineCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }


}
