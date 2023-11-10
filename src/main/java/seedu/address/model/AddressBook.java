package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.medicine.MedicineList;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    private final OrderList orders;

    private final MedicineList medicines;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        orders = new OrderList();
        medicines = new MedicineList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the order list with {@code orders}.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Replaces the contents of the medicine list with {@code medicines}.
     */
    public void setMedicines(List<Medicine> medicines) {
        this.medicines.setOrders(medicines);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setOrders(newData.getOrderList());
        setMedicines(newData.getMedicineList());

        updatePersonOrders();
    }

    private void updatePersonOrders() {
        getPersonList().forEach(Person::clearOrders);
        getOrderList().forEach(Order::addOrderToPerson);
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public void clear() {
        resetData(new AddressBook());
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        orders.editOrdersWithPerson(target, editedPerson);

        updatePersonOrders();
    }

    /**
     * Removes {@code key} and the corresponding orders from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        orders.removeOrdersWithPerson(key);
        persons.remove(key);

        updatePersonOrders();
    }


    //// order-level operations

    /**
     * Returns true if an order with the same identity as {@code order} exists in the address book.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }

    /**
     * Adds an order to the address book.
     */
    public void addOrder(Order o) {
        orders.add(o);

        updatePersonOrders();
    }

    /**
     * Removes {@code key} {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeOrder(Order key) {
        orders.remove(key);

        updatePersonOrders();
    }


    /**
     * Retrieves the order with the specified order number
     * @return Order with specified orderNumer
     */
    public Optional<Order> getOrder(String orderNumber) {
        return orders.getOrder(orderNumber);
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the address book.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the address book.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);

        orders.setOrder(target, editedOrder);

        updatePersonOrders();
    }

    //// medicine-level operations

    /**
     * Returns true if a medicine with the same identity as {@code medicine} exists in the address book.
     */
    public boolean hasMedicine(Medicine medicine) {
        requireNonNull(medicine);
        return medicines.contains(medicine);
    }

    /**
     * Adds a medicine to the address book.
     */
    public void addMedicine(Medicine m) {
        medicines.add(m);
    }

    /**
     * Removes {@code key} {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeMedicine(Medicine m) {
        medicines.remove(m);
    }

    /**
     * Retrieves the medicine with the same identity as the given one.
     */
    public Optional<Medicine> getMedicine(Medicine medicine) {
        return medicines.getMedicine(medicine);
    }

    /**
     * Replaces the given medicine {@code target} in the list with {@code editedMedicine}.
     * {@code target} must exist in the address book.
     * The medicine identity of {@code editedMedicine} must not be the same as another existing medicine
     * in the address book.
     */
    public void setMedicine(Medicine target, Medicine editedMedicine) {
        requireNonNull(editedMedicine);

        medicines.setMedicine(target, editedMedicine);
    }


    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("orders", orders)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Medicine> getMedicineList() {
        return medicines.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
                && orders.equals(otherAddressBook.orders)
                && medicines.equals(otherAddressBook.medicines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, orders);
    }

}
