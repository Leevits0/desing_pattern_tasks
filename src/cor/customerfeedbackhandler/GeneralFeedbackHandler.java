package cor.customerfeedbackhandler;

import java.util.Locale;

/** Handles generic feedback with a tiny sentiment-ish rule. */

public class GeneralFeedbackHandler extends FeedbackHandler {
    @Override protected boolean canHandle(FeedbackMessage msg) {
        return msg.getType() == MessageType.GENERAL_FEEDBACK;
    }

    @Override protected String process(FeedbackMessage msg) {
        String text = msg.getContent().toLowerCase(Locale.ROOT);
        String reply =
                text.matches(".*(love|great|awesome|thanks|thank you).*") ? "Thanks for the kind words!" :
                        text.matches(".*(bad|terrible|angry|frustrated|hate).*")  ? "We’re sorry for the trouble — we’ll look into this." :
                                "Thanks for your feedback!";
        return "General feedback noted — " + reply;
    }
}
