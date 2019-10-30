package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.result.CommandResult;
import seedu.address.logic.commands.result.ResultInformation;
import seedu.address.logic.commands.result.UiFocus;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

import java.util.Optional;

/**
 * Adds a person to the address book.
 */
public class AddContactCommand extends AddCommand {

    public static final String SECOND_COMMAND_WORD = "contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SECOND_COMMAND_WORD + " "
            + ": Adds a contact to the contact list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + SECOND_COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in the contact list";

    private final Index index;
    private final Contact toAdd;

    /**
     * Creates an AddContactCommand to add the specified {@code contact}.
     */
    public AddContactCommand(Contact contact) {
        requireNonNull(contact);
        toAdd = contact;
        index = null;
    }

    public AddContactCommand(Index index, Contact contact) {
        requireAllNonNull(index, contact);
        toAdd = contact;
        this.index = index;
    }

    public Contact getToAdd() {
        return toAdd;
    }

    @Override
    public String getSecondCommandWord() {
        return SECOND_COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasContact(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }
        if (index == null) {
            model.addContact(toAdd);
        } else {
            model.addContactAtIndex(index, toAdd);
        }
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, toAdd),
                new ResultInformation(toAdd, findIndexOfContact(model, toAdd)),
                UiFocus.CONTACT, UiFocus.INFO
        );
    }

    /**
     * Returns the index of contact in the model.
     * Precondition: the {@code contact} must have been added before this.
     */
    private Index findIndexOfContact(Model model, Contact contact) {
        Optional<Index> indexOfContact = model.getContactIndex(contact);
        if (indexOfContact.isEmpty()) {
            throw new AssertionError("Contact should have been added.");
        }
        return indexOfContact.get();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddContactCommand // instanceof handles nulls
                && toAdd.equals(((AddContactCommand) other).toAdd));
    }
}
