package cor.customerfeedbackhandler;

/**
 * Abstract handler in Chain of Responsibility.
 * If this handler can't process, it forwards to next.
 */

public abstract class FeedbackHandler {
    private FeedbackHandler next;

    public FeedbackHandler setNext(FeedbackHandler next) {
        this.next = next;
        return next;
    }

    /** Template method clients call. */
    public final String handle(FeedbackMessage msg) {
        if (canHandle(msg)) return process(msg);
        if (next != null)   return next.handle(msg);
        return "No handler available for: " + msg;
    }

    protected abstract boolean canHandle(FeedbackMessage msg);
    protected abstract String process(FeedbackMessage msg);
}
