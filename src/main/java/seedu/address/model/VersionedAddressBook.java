package seedu.address.model;

import seedu.address.model.medicine.Medicine;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

import java.util.ArrayDeque;

import static java.util.Objects.requireNonNull;

public class VersionedAddressBook extends AddressBook {
    private final ArrayDeque<AddressBook> history;
    private final ModelManager modelManager;

    VersionedAddressBook() {
        super();
        history = new ArrayDeque<>();
        modelManager = null;
    }
    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied, ModelManager modelManager) {
        super(toBeCopied);
        history = new ArrayDeque<>();
        this.modelManager = modelManager;
    }

    private void saveHistory() {
        if (history.size() > 30) {
            history.pollLast();
        }
        history.addFirst(new AddressBook(this));
    }

    public boolean canUndo() {
        return !history.isEmpty();
    }

    public void undo() {
        assert(!history.isEmpty());

        AddressBook prev = history.pollFirst();
        modelManager.setAddressBook(prev);
    }

    //=========== Undoable functions =============================================================
    @Override
    public void addPerson(Person p) {
        saveHistory();
        super.addPerson(p);
    }
    @Override
    public void setPerson(Person target, Person editedPerson) {
        saveHistory();
        super.setPerson(target, editedPerson);
    }
    @Override
    public void removePerson(Person key) {
        saveHistory();
        super.removePerson(key);
    }
    @Override
    public void addOrder(Order o) {
        saveHistory();
        super.addOrder(o);
    }
    @Override
    public void removeOrder(Order key) {
        saveHistory();
        super.removeOrder(key);
    }
    @Override
    public void setOrder(Order target, Order editedOrder) {
        saveHistory();
        super.setOrder(target, editedOrder);
    }
    @Override
    public void addMedicine(Medicine m) {
        saveHistory();
        super.addMedicine(m);
    }
    @Override
    public void removeMedicine(Medicine m) {
        saveHistory();
        super.removeMedicine(m);
    }
    @Override
    public void setMedicine(Medicine target, Medicine editedMedicine) {
        saveHistory();
        super.setMedicine(target, editedMedicine);
    }
}
