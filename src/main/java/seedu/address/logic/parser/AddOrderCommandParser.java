package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDERNUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddOrderCommand object.
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_INVALID_ORDERNUMBER = "Order number is not a positive integer.";

    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORDERNUMBER, PREFIX_MEDICINENAME);



        if (argMultimap.getPreamble().isEmpty()
            || argMultimap.getValue(PREFIX_MEDICINENAME).orElse("").isEmpty()
            || argMultimap.getValue(PREFIX_ORDERNUMBER).orElse("").isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ORDERNUMBER, PREFIX_MEDICINENAME);

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

        int orderNumber;
        try {
            orderNumber = Integer.parseInt(argMultimap.getValue(PREFIX_ORDERNUMBER).get());
            if (orderNumber <= 0) {
                throw new ParseException(MESSAGE_INVALID_ORDERNUMBER);
            }
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_ORDERNUMBER);
        }

        return new AddOrderCommand(index, orderNumber, argMultimap.getValue(PREFIX_MEDICINENAME).get());

    }

}
