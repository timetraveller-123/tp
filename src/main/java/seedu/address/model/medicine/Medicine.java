package seedu.address.model.medicine;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Represents a Medicine in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Medicine {

    public static final String MESSAGE_MEDICINE_NAME_CONSTRAINTS =
            "Medicine Names should not be empty"; // temporarily this will change soon

    public static final String MESSAGE_SHORT_FORM_CONSTRAINTS =
            "Medicine fhort forms should not be empty"; // temporarily this will change soon



    public final String medicineName;

    public final String shortForm;
    /**
     * Every field must be present and not null.
     */
    public Medicine(String medicineName) {
        requireNonNull(medicineName);
        this.medicineName = medicineName;
        this.shortForm = "";
    }

    /**
     * Every field must be present and not null.
     */
    public Medicine(String medicineName, String shorfForm) {
        requireAllNonNull(medicineName, shorfForm);
        this.medicineName = medicineName;
        this.shortForm = shorfForm;
    }

    public String getMedicineName() {
        return this.medicineName;
    }

    public String getShorfForm() {
        return this.shortForm;
    }


    /**
     * Returns true if both medicines have the same medicine name.
     * This defines a weaker notion of equality between two medicines.
     */
    public boolean isSameMedicine(Medicine m) {
        if (m == this) {
            return true;
        }

        if (m != null && medicineName.equals(m.medicineName)) {
            return true;
        }

        if (m != null && !shortForm.isEmpty() && shortForm.equals(m.shortForm)) {
            return true;
        }

        //for use in short form expansion
        if (m != null && medicineName.equals(m.shortForm)) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if both orders have the same identity and data fields.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Medicine)) {
            return false;
        }

        Medicine otherMedicine = (Medicine) o;
        return medicineName.equals(otherMedicine.medicineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("medicineName", medicineName)
                .add("shortForm", shortForm)
                .toString();
    }

    public static boolean isValidMedicineName(String s) {
        return true; // temporarily left to true will change in future commit
    }

    public static boolean isValidShortForm(String s) {
        return true; //same as above
    }
}
