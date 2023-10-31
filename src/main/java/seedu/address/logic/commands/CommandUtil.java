package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medicine.Medicine;

public class CommandUtil {

    /**
     * Converts the given set of medicines to another set of medicines with medicine short forms expanded to
     * the full name of the medicine as it is in the given model.
     * Throws command exception if the given medicine name or its short form cannot be found in the given model.
     */
    public static Set<Medicine> getModelMedicine(Model model, Set<Medicine> medicines) throws CommandException {
        Set<Medicine> convertedMedicines = new HashSet<>();
        for (Medicine medicine : medicines) {
            Optional<Medicine> m = model.getMedicine(medicine);
            if (m.isEmpty()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEDICINE);
            }
            convertedMedicines.add(m.get());
        }
        return convertedMedicines;
    }
}
