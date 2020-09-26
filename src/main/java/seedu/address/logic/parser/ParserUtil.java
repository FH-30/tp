package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Mcq;
import seedu.address.flashcard.OpenEndedQuestion;
import seedu.address.flashcard.Option;
import seedu.address.flashcard.Question;
import seedu.address.flashcard.Tag;
import seedu.address.logic.commands.AddMultipleChoiceQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String question} into a {@code Question}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code question} is invalid.
     */
    public static Question parseQuestion(String question) throws ParseException {
        requireNonNull(question);
        String trimmedQuestion = question.trim();
        if (!OpenEndedQuestion.isValidQuestion(trimmedQuestion)) {
            throw new ParseException(OpenEndedQuestion.MESSAGE_CONSTRAINTS);
        }
        return new OpenEndedQuestion(trimmedQuestion);
    }

    /**
     * Parses a {@code String question} into a {@code Question}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if answer is less than choices and question is invalid.
     */
    public static Question parseMultipleChoiceQuestion(String question,
                                                       String[] choices) throws ParseException {
        requireNonNull(question);
        String trimmedQuestion = question.trim();
        if (!Mcq.isValidQuestion(trimmedQuestion)) {
            throw new ParseException(Mcq.MESSAGE_CONSTRAINTS);
        }
        return new Mcq(trimmedQuestion, choices);
    }

    /**
     * Parses a {@code String answer} into a {@code Answer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code answer} is invalid.
     */
    public static Answer parseAnswer(String answer) throws ParseException {
        requireNonNull(answer);
        String trimmedAnswer = answer.trim();
        if (!Answer.isValidAnswer(trimmedAnswer)) {
            throw new ParseException(Answer.MESSAGE_CONSTRAINTS);
        }
        return new Answer(trimmedAnswer);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static String parseChoice(String choice) throws ParseException {
        requireNonNull(choice);
        String trimmedChoice = choice.trim();
        if (choice.equals(" ")) {
            throw new ParseException("Choices cannot be empty");
        }
        return trimmedChoice;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static String[] parseChoices(Collection<String> choices) throws ParseException {
        requireNonNull(choices);
        List<String> choicesList = new ArrayList<>();
        for (String choice : choices) {
            choicesList.add(parseChoice(choice));
        }
        String[] result = new String[choicesList.size()];
        if (result.length <= 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMultipleChoiceQuestionCommand.MESSAGE_USAGE));
        }
        choicesList.toArray(result);
        return result;
    }
    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String option} into a {@code Option}.
     * Leading and trailing white spaces will be trimmed.
     *
     * @throws ParseException if the given {@code option} is invalid.
     */
    public static Option parseOption(String option) throws ParseException {
        requireNonNull(option);
        String trimmedOption = option.trim();
        if (!Option.isValidOption(trimmedOption)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Option(trimmedOption);
    }
}
