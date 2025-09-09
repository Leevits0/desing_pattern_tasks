package cor.customerfeedbackhandler;

/** Last link in the chain: catches unknown or unsupported messages. */
public class FallbackHandler extends FeedbackHandler {
    @Override protected boolean canHandle(FeedbackMessage msg) {
        return true; // Always handles if reached here
    }

    @Override protected String process(FeedbackMessage msg) {
        return "Unhandled message type (" + msg.getType() + "). Our team will manually review it.";
    }
}
