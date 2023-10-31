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
    public final String medicineName;

    public final String shorfForm;
    /**
     * Every field must be present and not null.
     */
    public Medicine(String medicineName) {
        requireNonNull(medicineName);
        this.medicineName = medicineName;
        this.shorfForm = "";
    }

    /**
     * Every field must be present and not null.
     */
    public Medicine(String medicineName, String shorfForm) {
        requireAllNonNull(medicineName, shorfForm);
        this.medicineName = medicineName;
        this.shorfForm = shorfForm;
    }

    public String getMedicineName() {
        return this.medicineName;
    }

    public String getShorfForm() {
        return this.shorfForm;
    }


    /**
     * Returns true if both medicines have the same medicine name.
     * This defines a weaker notion of equality between two medicines.
     */
    public boolean isSameMedicine(Medicine m) {
        if (m == this) {
            return true;
        }

        return m != null && m.medicineName.equals(this.medicineName);
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
        return medicineName.equals(otherMedicine.medicineName) && shorfForm.equals(otherMedicine.shorfForm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineName, shorfForm);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("medicineName", medicineName)
                .add("shortForm", shorfForm)
                .toString();
    }
}
