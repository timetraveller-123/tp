package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindMedicineCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindMedicineCommand object
 */
public class FindMedicineCommandParser implements Parser<FindMedicineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMedicineCommand
     * and returns a FindMedicineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMedicineCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMedicineCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new FindMedicineCommand(nameKeywords);
    }

}
