package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;
import static quickcache.logic.parser.CliSyntax.PREFIX_ANSWER;
import static quickcache.logic.parser.CliSyntax.PREFIX_QUESTION;
import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;

import quickcache.logic.commands.exceptions.CommandException;
import quickcache.model.Model;
import quickcache.model.flashcard.Flashcard;


/**
 * Adds a person to the address book.
 */
public class AddOpenEndedQuestionCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard to the address book. "
            + "Parameters: "
            + PREFIX_QUESTION + "Question "
            + PREFIX_ANSWER + "Answer "
            + "[" + PREFIX_TAG + "TAG]...\n";

    public static final String MESSAGE_SUCCESS = "New flashcard added: %1$s";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in QuickCache";

    private final Flashcard toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Flashcard}
     */
    public AddOpenEndedQuestionCommand(Flashcard flashcard) {
        requireNonNull(flashcard);
        toAdd = flashcard;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFlashcard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }

        model.addFlashcard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOpenEndedQuestionCommand // instanceof handles nulls
                && toAdd.equals(((AddOpenEndedQuestionCommand) other).toAdd));
    }
}