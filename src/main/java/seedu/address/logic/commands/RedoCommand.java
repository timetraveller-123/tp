package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redoes the last undo
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redo the last undo made to PharmHub\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Redo successful.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.canRedo()) {
            throw new CommandException(Messages.MESSAGE_NO_VALID_PREVSTATE);
        }

        model.redo();
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }
}
