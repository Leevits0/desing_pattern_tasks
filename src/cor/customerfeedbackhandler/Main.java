package cor.customerfeedbackhandler;

import java.util.List;

/** Minimal-but-thorough demo for Chain of Responsibility. */
public class Main {

    public static void main(String[] args) {
        FeedbackHandler chain = createChain();

        runSuite("Compensation — approve vs. needs review", chain, List.of(
                // APPROVE: has a receipt and small amount
                new FeedbackMessage(MessageType.COMPENSATION_CLAIM, "Flight delay, attaching receipt for €45 taxi.", "alice@example.com"),
                // NEEDS REVIEW: no receipt + likely higher amount
                new FeedbackMessage(MessageType.COMPENSATION_CLAIM, "Lost luggage. Please compensate $250.", "bruce@example.com"),
                // APPROVE: explicitly small claim without currency
                new FeedbackMessage(MessageType.COMPENSATION_CLAIM, "Small claim for parking, proof attached.", "cory@example.com")
        ));

        runSuite("Contact requests — department routing", chain, List.of(
                // Billing
                new FeedbackMessage(MessageType.CONTACT_REQUEST, "Question about invoice and payment.", "dana@example.com"),
                // Technical Support
                new FeedbackMessage(MessageType.CONTACT_REQUEST, "The app shows an error and crashes, need technical help.", "ella@example.com"),
                // HR
                new FeedbackMessage(MessageType.CONTACT_REQUEST, "Career inquiry about an internship position.", "finn@example.com"),
                // Default (Customer Care)
                new FeedbackMessage(MessageType.CONTACT_REQUEST, "I want to talk to someone about delivery times.", "gail@example.com")
        ));

        runSuite("Suggestions — priority levels", chain, List.of(
                // HIGH (urgent/security/compliance)
                new FeedbackMessage(MessageType.SUGGESTION, "Urgent: add 2FA for security compliance.", "hank@example.com"),
                // MEDIUM (improve/nice/could/maybe/idea)
                new FeedbackMessage(MessageType.SUGGESTION, "It would be nice to improve onboarding hints.", "ivy@example.com"),
                // LOW (no priority hints)
                new FeedbackMessage(MessageType.SUGGESTION, "Consider a new icon for the app.", "jules@example.com")
        ));

        runSuite("General feedback — sentiment", chain, List.of(
                // Positive
                new FeedbackMessage(MessageType.GENERAL_FEEDBACK, "Love the new UI, awesome work — thanks!", "kai@example.com"),
                // Negative
                new FeedbackMessage(MessageType.GENERAL_FEEDBACK, "I'm frustrated, the experience is terrible right now.", "li@example.com"),
                // Neutral
                new FeedbackMessage(MessageType.GENERAL_FEEDBACK, "Feedback: notifications feel a bit frequent.", "mira@example.com")
        ));

        runSuite("Fallback — unknown type", chain, List.of(
                new FeedbackMessage(MessageType.UNKNOWN, "???", "mystery@example.com")
        ));
    }

    // --- helpers ---

    private static FeedbackHandler createChain() {
        // compensation -> contact -> suggestion -> general -> fallback
        FeedbackHandler chain = new CompensationHandler();
        chain.setNext(new ContactRequestHandler())
                .setNext(new SuggestionHandler())
                .setNext(new GeneralFeedbackHandler())
                .setNext(new FallbackHandler());
        return chain;
    }

    private static void runSuite(String title, FeedbackHandler chain, List<FeedbackMessage> messages) {
        System.out.println("\n=== " + title + " ===");
        messages.forEach(m -> {
            System.out.println("IN : " + m);
            System.out.println("OUT: " + chain.handle(m));
            System.out.println("----");
        });
    }
}
