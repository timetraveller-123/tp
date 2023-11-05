package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "findp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Predicate<Person> nameContainsKeywordsPredicate;
    private final Phone phoneToFind;
    private final Email emailToFind;
    private final Set<Tag> tagsToFind;
    private final Set<Allergy> allergiesToFind;

    /**
     * Constructor method for the find person class.
     * @param nameContainsKeywordsPredicate The predicate to find the person.
     * @param phoneToFind The phone to find.
     * @param emailToFind The email to find.
     * @param tagsToFind The tags to find.
     * @param allergiesToFind The allergies to find.
     */
    public FindPersonCommand(Predicate<Person> nameContainsKeywordsPredicate,
                             Phone phoneToFind, Email emailToFind, Set<Tag> tagsToFind, Set<Allergy> allergiesToFind) {
        this.nameContainsKeywordsPredicate = nameContainsKeywordsPredicate;
        this.phoneToFind = phoneToFind;
        this.emailToFind = emailToFind;
        this.tagsToFind = tagsToFind;
        this.allergiesToFind = allergiesToFind;
    }

    public FindPersonCommand(
            NameContainsKeywordsPredicate nameContainsKeywordsPredicate
    ) {
        this(nameContainsKeywordsPredicate, null, null, null, null);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);


        Predicate<Person> phoneMatches = person -> phoneToFind == null
                || person.getPhone().equals(phoneToFind);

        Predicate<Person> emailMatches = person -> emailToFind == null
                || person.getEmail().equals(emailToFind);

        Predicate<Person> tagMatches = person -> tagsToFind == null
                || person.getTags().stream()
                .anyMatch(tag -> tagsToFind.stream()
                        .anyMatch(checkTag -> checkTag.equals(tag)));

        Predicate<Person> allergyMatches = person -> allergiesToFind == null
                || person.getAllergies().stream()
                .anyMatch(allergy -> allergiesToFind.stream()
                        .anyMatch(checkAllergy -> checkAllergy.equals(allergy)));

        Predicate<Person> combined =
                nameContainsKeywordsPredicate
                        .and(phoneMatches)
                        .and(emailMatches)
                        .and(tagMatches)
                        .and(allergyMatches);

        model.updateFilteredPersonList(combined);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                CommandResult.ListPanelEffects.PERSON);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindPersonCommand)) {
            return false;
        }

        FindPersonCommand otherFindCommand = (FindPersonCommand) other;
        return nameContainsKeywordsPredicate.equals(otherFindCommand.nameContainsKeywordsPredicate)
                && ((phoneToFind == null && otherFindCommand.phoneToFind == null)
                || phoneToFind.equals(otherFindCommand.phoneToFind))
                && ((emailToFind == null && otherFindCommand.emailToFind == null)
                || emailToFind.equals(otherFindCommand.emailToFind))
                && ((tagsToFind == null && otherFindCommand.tagsToFind == null)
                || tagsToFind.equals(otherFindCommand.tagsToFind))
                && ((allergiesToFind == null && otherFindCommand.allergiesToFind == null)
                || allergiesToFind.equals(otherFindCommand.allergiesToFind));
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);

        if (nameContainsKeywordsPredicate != null) {
            toStringBuilder.add("predicate", nameContainsKeywordsPredicate);
        }
        if (phoneToFind != null) {
            toStringBuilder.add("phone", phoneToFind);
        }
        if (emailToFind != null) {
            toStringBuilder.add("email", emailToFind);
        }
        if (tagsToFind != null) {
            toStringBuilder.add("tags", tagsToFind);
        }
        if (allergiesToFind != null) {
            toStringBuilder.add("allergies", allergiesToFind);
        }

        return toStringBuilder.toString();
    }
}
