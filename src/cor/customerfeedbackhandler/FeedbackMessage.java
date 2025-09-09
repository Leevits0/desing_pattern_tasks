package cor.customerfeedbackhandler;

public class FeedbackMessage {
    private final MessageType type;
    private final String content;
    private final String senderEmail;

    public FeedbackMessage(MessageType type, String content, String senderEmail) {
        this.type = type;
        this.content = content;
        this.senderEmail = senderEmail;
    }

    public MessageType getType()      { return type; }
    public String getContent()        { return content; }
    public String getSenderEmail()    { return senderEmail; }

    @Override public String toString() {
        return "[" + type + "] from " + senderEmail + " :: " + content;
    }
}
