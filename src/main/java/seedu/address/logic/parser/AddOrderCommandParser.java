package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDERNUMBER;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddOrderCommand object.
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {

    public static final String COMMAND_WORD = "addorder";

    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORDERNUMBER, PREFIX_MEDICINENAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ORDERNUMBER, PREFIX_MEDICINENAME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ORDERNUMBER, PREFIX_MEDICINENAME);
        return new AddOrderCommand(index,
                                    argMultimap.getValue(PREFIX_ORDERNUMBER).get(),
                                    argMultimap.getValue(PREFIX_MEDICINENAME).get());

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
