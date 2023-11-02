package seedu.address.model;

import seedu.address.model.medicine.Medicine;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

import java.util.ArrayDeque;

import static java.util.Objects.requireNonNull;

public class VersionedAddressBook extends AddressBook {
    private final ArrayDeque<AddressBook> undoHistory;
    private final ArrayDeque<AddressBook> redoHistory;
    private final ModelManager modelManager;

    VersionedAddressBook() {
        super();
        undoHistory = new ArrayDeque<>();
        redoHistory = new ArrayDeque<>();
        modelManager = null;
    }
    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied, ModelManager modelManager) {
        super(toBeCopied);
        undoHistory = new ArrayDeque<>();
        redoHistory = new ArrayDeque<>();
        this.modelManager = modelManager;
    }

    private void saveHistory() {
        if (undoHistory.size() > 30) {
            undoHistory.pollLast();
        }
        undoHistory.addFirst(new AddressBook(this));
    }

    public boolean canUndo() {
        return !undoHistory.isEmpty();
    }

    public void undo() {
        assert(!undoHistory.isEmpty());

        AddressBook prev = undoHistory.pollFirst();
        redoHistory.addFirst(new AddressBook(this));
        modelManager.setAddressBook(prev);
    }

    public boolean canRedo() {
        return !redoHistory.isEmpty();
    }

    public void redo() {
        assert(!redoHistory.isEmpty());

        AddressBook next = redoHistory.pollFirst();
        undoHistory.addFirst(new AddressBook(this));
        modelManager.setAddressBook(next);
    }

    private void clearRedoHistory() {
        redoHistory.clear();
    }

    //=========== Undoable functions =============================================================
    @Override
    public void addPerson(Person p) {
        saveHistory();
        super.addPerson(p);
        clearRedoHistory();
    }
    @Override
    public void setPerson(Person target, Person editedPerson) {
        saveHistory();
        super.setPerson(target, editedPerson);
        clearRedoHistory();
    }
    @Override
    public void removePerson(Person key) {
        saveHistory();
        super.removePerson(key);
        clearRedoHistory();
    }
    @Override
    public void addOrder(Order o) {
        saveHistory();
        super.addOrder(o);
        clearRedoHistory();
    }
    @Override
    public void removeOrder(Order key) {
        saveHistory();
        super.removeOrder(key);
        clearRedoHistory();
    }
    @Override
    public void setOrder(Order target, Order editedOrder) {
        saveHistory();
        super.setOrder(target, editedOrder);
        clearRedoHistory();
    }
    @Override
    public void addMedicine(Medicine m) {
        saveHistory();
        super.addMedicine(m);
        clearRedoHistory();
    }
    @Override
    public void removeMedicine(Medicine m) {
        saveHistory();
        super.removeMedicine(m);
        clearRedoHistory();
    }
    @Override
    public void setMedicine(Medicine target, Medicine editedMedicine) {
        saveHistory();
        super.setMedicine(target, editedMedicine);
        clearRedoHistory();
    }
}
