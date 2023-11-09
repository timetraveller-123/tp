package seedu.pharmhub.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.pharmhub.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the PharmHub.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters, spaces, dots, slashes, hyphens "
                     + ", single-quotes and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[[a-zA-Z]|\\/|\\-|'|.|[0-9]][[A-z]|\\/|\\s|\\-|'|.|[0-9]]*$";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.trim().equalsIgnoreCase(otherName.fullName.trim());
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}