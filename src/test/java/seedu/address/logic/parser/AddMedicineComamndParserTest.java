package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDERNUMBER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMedicineCommand;



public class AddMedicineComamndParserTest {

    private static final String VALID_MEDICINE_NAME = "Panadol";
    private static final String INVALID_MEDICINE_NAME = "";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedicineCommand.MESSAGE_USAGE);


    private final Parser<AddMedicineCommand> parser = new AddMedicineCommandParser();

    @Test
    public void parse_missingParts_failure() {


        //Missing medicineName
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);

        //Wrong prefix
        assertParseFailure(parser, PREFIX_ORDERNUMBER + " panadol", MESSAGE_INVALID_FORMAT);
    }
}
