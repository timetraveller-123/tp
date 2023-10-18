package seedu.address.logic.parser;

import seedu.address.logic.commands.ViewOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ViewOrderCommandParser implements Parser<ViewOrderCommand> {

    public ViewOrderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !trimmedArgs.matches("^\\d+$")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewOrderCommand.MESSAGE_USAGE));
        }

        return new ViewOrderCommand(Integer.parseInt(trimmedArgs));
    }
}
