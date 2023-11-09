package seedu.pharmhub.model.allergy;

import static seedu.pharmhub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.pharmhub.model.medicine.Medicine;

public class AllergyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Allergy(null));
    }

    @Test
    public void constructor_invalidAllergyName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Allergy(new Medicine("")));
    }

    @Test
    public void isValidAllergyName() {
        // null allergy name
        assertThrows(NullPointerException.class, () -> Allergy.isValidAllergyName(null));
    }

    @Test
    public void equals_sameAllergyName_returnsTrue() {
        Allergy allergy = new Allergy(new Medicine("Aspirin"));
        Allergy allergyCopy = new Allergy(new Medicine("Aspirin"));
        assert(allergy.equals(allergyCopy));
    }

}
