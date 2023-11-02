package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_SHORT_FORM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEDICINES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medicine.Medicine;


/**
 * A class which represents the adding of a short form to a medicine.
 */
public class AddShortFormCommand extends Command {
    public static final String COMMAND_WORD = "sfm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a short form to the Medicine identified "
            + "by the index number used in the displayed medicine list. "
            + "Existing short form will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
             + PREFIX_MEDICINE_NAME + "SHORTFORM "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MEDICINE_NAME + "pan "
            + " Add the tag " + PREFIX_DELETE_SHORT_FORM + " to delete the short form";

    public static final String MESSAGE_ADD_SHORT_FORM_SUCCESS = "Added Short From: %1$s";

    public static final String MESSAGE_DELETE_SHORT_FORM_SUCCESS = "Deleted Short From: %1$s";
    public static final String MESSAGE_DUPLICATE_SHORT_FORM =
            "This short form/medicine already exists in the address book.";

    private final Index index;

    private final Medicine medicine;

    /**
     * Creates an AddShortFormCommand
     */
    public AddShortFormCommand(Index index, Medicine medicine) {
        requireNonNull(index);
        requireNonNull(medicine);
        this.index = index;
        this.medicine = medicine;
    }

    /**
     * Creates an AddShortForm Command
     */
    public AddShortFormCommand(Index index) {
        requireNonNull(index);
        this.index = index;
        medicine = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Medicine> lastShownList = model.getFilteredMedicineList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEDICINE_DISPLAYED_INDEX);
        }

        if (medicine == null) {
            Medicine medicineToAddShortForm = lastShownList.get(index.getZeroBased());
            Medicine newMedicine = new Medicine(medicineToAddShortForm.getMedicineName(), "");
            model.setMedicine(medicineToAddShortForm, newMedicine);
            model.updateFilteredMedicineList(PREDICATE_SHOW_ALL_MEDICINES);
            return new CommandResult(String.format(MESSAGE_DELETE_SHORT_FORM_SUCCESS, Messages.format(newMedicine)));
        }

        if (model.hasMedicine(medicine)) {
            throw new CommandException(MESSAGE_DUPLICATE_SHORT_FORM);
        }

        Medicine medicineToAddShortForm = lastShownList.get(index.getZeroBased());
        Medicine newMedicine = new Medicine(medicineToAddShortForm.getMedicineName(), medicine.getMedicineName());

        model.setMedicine(medicineToAddShortForm, newMedicine);
        model.updateFilteredMedicineList(PREDICATE_SHOW_ALL_MEDICINES);
        return new CommandResult(String.format(MESSAGE_ADD_SHORT_FORM_SUCCESS, Messages.format(newMedicine)));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddShortFormCommand)) {
            return false;
        }

        AddShortFormCommand otherCommand = (AddShortFormCommand) other;
        return index.equals(otherCommand.index)
                && medicine.equals(otherCommand.medicine);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("medicine", medicine)
                .toString();
    }


}
