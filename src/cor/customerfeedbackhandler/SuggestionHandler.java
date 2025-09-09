package cor.customerfeedbackhandler;
import java.util.Locale;

/** Logs product suggestions with a simple priority heuristic. */
public class SuggestionHandler extends FeedbackHandler {
    @Override protected boolean canHandle(FeedbackMessage msg) {
        return msg.getType() == MessageType.SUGGESTION;
    }

    @Override protected String process(FeedbackMessage msg) {
        String text = msg.getContent().toLowerCase(Locale.ROOT);
        String priority =
                text.matches(".*(urgent|crucial|must|security|compliance).*") ? "HIGH" :
                        text.matches(".*(improve|nice|could|maybe|idea).*")           ? "MEDIUM" :
                                "LOW";
        // pretend we logged to a backlog system
        return "Suggestion logged (priority " + priority + ") — thank you, " + msg.getSenderEmail() + ".";
    }
}
