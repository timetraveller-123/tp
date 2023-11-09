package seedu.pharmhub.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.pharmhub.model.Model;

/**
 * Clears the pharmHub.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "PharmHub has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearPharmHub();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
