package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderNumber;
import seedu.address.model.order.Status;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


class AddOrderCommandTest {

    private final OrderNumber orderNumber = new OrderNumber("2");

    private final String medicineName = "Panadol";
    private final Medicine medicine = new Medicine(medicineName);
    private final Set<Medicine> medicines = new HashSet<>(List.of(medicine));
    private final Status orderStatus = new Status(Status.OrderStatus.PENDING);



    @Test
    public void execute_unfilteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        AddOrderCommand addOrderCommand = new AddOrderCommand(INDEX_FIRST, orderNumber, medicines, false);
        Order order = new Order(orderNumber, model.getFilteredPersonList().get(0), medicines, orderStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addOrder(order);

        String expectedMessage = String.format(AddOrderCommand.MESSAGE_SUCCESS, Messages.format(order));
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, order);

        assertCommandSuccess(addOrderCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(model, INDEX_FIRST);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());

        AddOrderCommand addOrderCommand = new AddOrderCommand(INDEX_FIRST, orderNumber, medicines, false);


        Order order = new Order(orderNumber, personInFilteredList, medicines, orderStatus);

        String expectedMessage = String.format(AddOrderCommand.MESSAGE_SUCCESS, Messages.format(order));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addOrder(order);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, order);

        assertCommandSuccess(addOrderCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    void execute_allergicToMedicineAndIgnoreAllergy_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person person = new PersonBuilder().withAllergies(medicineName).build();
        model.addPerson(person);
        AddOrderCommand addOrderCommand = new AddOrderCommand(INDEX_FIRST, orderNumber, medicines, true);

        Order order = new Order(orderNumber, model.getFilteredPersonList().get(0), medicines, orderStatus);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addOrder(order);

        String expectedMessage = String.format(AddOrderCommand.MESSAGE_SUCCESS, Messages.format(order));
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, order);

        assertCommandSuccess(addOrderCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        Person person = new PersonBuilder().build();

        AddOrderCommand addOrderCommand = new AddOrderCommand(outOfBoundIndex, orderNumber, medicines, false);

        Order order = new Order(orderNumber, person, medicines, orderStatus);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addOrder(order);

        assertCommandFailure(addOrderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_allergicToMedicineAndNotIgnoreAllergy_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person person = new PersonBuilder().withAllergies(medicineName).build();
        model.addPerson(person);
        AddOrderCommand addOrderCommand = new AddOrderCommand(Index.fromOneBased(model.getFilteredPersonList().size()),
                orderNumber, medicines,
                false);

        Order order = new Order(orderNumber, model.getFilteredPersonList().get(0), medicines, orderStatus);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addOrder(order);

        assertCommandFailure(addOrderCommand, model, Messages.MESSAGE_ALLERGIC_TO_MEDICINE);
    }

    @Test
    public void equals() {
        AddOrderCommand addOrderCommand = new AddOrderCommand(INDEX_FIRST, orderNumber, medicines, false);
        assertTrue(addOrderCommand.equals(addOrderCommand));
        assertFalse(addOrderCommand.equals(null));
    }

    @Test
    public void execute_duplicateOrder_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Order orderInList = model.getFilteredOrderList().get(0);
        assertCommandFailure(new AddOrderCommand(INDEX_FIRST, orderInList.getOrderNumber() , medicines, true),
                model, String.format(AddOrderCommand.MESSAGE_DUPLICATE_ORDER, orderInList.getOrderNumber()));
    }

}
