package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.FindMedicineCommand;
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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STATUS, PREFIX_MEDICINE_NAME);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STATUS, PREFIX_MEDICINE_NAME);

        Status statusToFind = null;
        Set<Medicine> medicineToFind = null;

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            String s = argMultimap.getValue(PREFIX_STATUS).get();
            statusToFind = ParserUtil.parseStatus(Status.shortFormToFull(s));
        }
        if (argMultimap.getValue(PREFIX_MEDICINE_NAME).isPresent()) {
            String medicineArg = argMultimap.getValue(PREFIX_MEDICINE_NAME).get();
            List<String> list = Arrays.asList(medicineArg.split("\\s+"));
            medicineToFind = ParserUtil.parseMedicines(list);
        }

        if (statusToFind == null && medicineToFind == null) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }

        return new FindOrderCommand(statusToFind, medicineToFind);
    }

}
