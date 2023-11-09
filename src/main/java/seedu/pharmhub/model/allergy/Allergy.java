package seedu.pharmhub.model.allergy;

import static java.util.Objects.requireNonNull;

import seedu.pharmhub.model.medicine.Medicine;


/**
 * Represents a Allergy of a patient.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAllergyName(String)}
 */
public class Allergy {

    public static final String MESSAGE_CONSTRAINTS = "Allergy names should be alphanumeric";

    public final Medicine allergy;

    /**
     * Constructs a {@code Allergy}.
     *
     * @param allergy A valid medicine.
     */
    public Allergy(Medicine allergy) {
        requireNonNull(allergy);
        this.allergy = allergy;
    }

    /**
     * Returns true if a given medicine is a valid medicine.
     */
    public static boolean isValidAllergyName(String test) {
        return Medicine.isValidMedicineName(test);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Allergy)) {
            return false;
        }

        Allergy otherAllergy = (Allergy) other;
        return allergy.equals(otherAllergy.allergy);
    }

    @Override
    public int hashCode() {
        return allergy.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + allergy.getMedicineName() + ']';
    }

    /**
     * Retruns a copy of the allergic medicine.
     */
    public Medicine getAllery() {
        return new Medicine(allergy.getMedicineName(), allergy.getShortForm());
    }

}
