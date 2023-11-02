package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMedicineCommand;

public class FindMedicineCommandParserTest {

    private FindMedicineCommandParser parser = new FindMedicineCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMedicineCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String[] keyWords = {"ol", "en"};
        FindMedicineCommand expectedFindCommand =
                new FindMedicineCommand(keyWords);
        assertParseSuccess(parser, "ol en", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n ol \n \t en  \t", expectedFindCommand);
    }

}
