package cor.customerfeedbackhandler;

import java.util.Locale;

/** Routes contact requests to a department by keyword. */

public class ContactRequestHandler extends FeedbackHandler {
    @Override protected boolean canHandle(FeedbackMessage msg) {
        return msg.getType() == MessageType.CONTACT_REQUEST;
    }

    @Override protected String process(FeedbackMessage msg) {
        String text = msg.getContent().toLowerCase(Locale.ROOT);
        String dept =
                text.matches(".*(invoice|billing|refund|payment).*") ? "Billing" :
                        text.matches(".*(crash|bug|error|technical|api).*")   ? "Technical Support" :
                                text.matches(".*(career|job|intern).*")               ? "HR" :
                                        "Customer Care";
        return "Contact request forwarded to " + dept + " — we’ll email " + msg.getSenderEmail() + ".";
    }
}
