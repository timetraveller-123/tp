package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINENAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddShortFormCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.medicine.Medicine;

/**
 * Parses input arguments and creates a new AddShortFormCommand object
 */
public class AddShortFormCommandParser implements Parser<AddShortFormCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddShortFormCommand
     * and returns an AddShortFormCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddShortFormCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEDICINENAME);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortFormCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_MEDICINENAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortFormCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MEDICINENAME);
        String shortForm = argMultimap.getValue(PREFIX_MEDICINENAME).get().trim();
        if (!Medicine.isValidShortForm(shortForm)) {
            throw new ParseException(Medicine.MESSAGE_SHORT_FORM_CONSTRAINTS);
        }

        return new AddShortFormCommand(index, new Medicine(shortForm));
    }

}
