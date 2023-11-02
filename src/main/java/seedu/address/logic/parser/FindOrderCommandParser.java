package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.order.Status;



/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindOrderCommandParser implements Parser<FindOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STATUS, PREFIX_MEDICINENAME);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STATUS, PREFIX_MEDICINENAME);

        Status statusToFind = null;
        Medicine medicineToFind = null;

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            statusToFind = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        }
        if (argMultimap.getValue(PREFIX_MEDICINENAME).isPresent()) {
            medicineToFind = ParserUtil.parseMedicine(argMultimap.getValue(PREFIX_MEDICINENAME).get());
        }

        return new FindOrderCommand(statusToFind, medicineToFind);
    }

}
