package cor.customerfeedbackhandler;

import java.util.Locale;

/** Handles compensation claims. */
public class CompensationHandler extends FeedbackHandler {
    @Override protected boolean canHandle(FeedbackMessage msg) {
        return msg.getType() == MessageType.COMPENSATION_CLAIM;
    }

    @Override protected String process(FeedbackMessage msg) {
        String text = msg.getContent().toLowerCase(Locale.ROOT);
        boolean hasReceipt = text.contains("receipt") || text.contains("proof");
        boolean smallClaim = text.contains("€") || text.contains("$") ? text.matches(".*\\b([1-9]\\d?|100)\\b.*") : text.contains("small");
        boolean approve = hasReceipt || smallClaim;

        return approve
                ? "Compensation APPROVED for " + msg.getSenderEmail() + " — ticket created."
                : "Compensation NEEDS REVIEW for " + msg.getSenderEmail() + " — missing receipt or high amount.";
    }
}
