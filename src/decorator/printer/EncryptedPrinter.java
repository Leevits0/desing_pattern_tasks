package decorator.printer;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptedPrinter extends AbstractPrinterDecorator {

    private final String key;

    public EncryptedPrinter(Printer wrappee) {
        this(wrappee, "secret"); // default key
    }

    public EncryptedPrinter(Printer wrappee, String key) {
        super(wrappee);
        if (key == null || key.isEmpty()) throw new IllegalArgumentException("key must not be empty");
        this.key = key;
    }

    @Override
    public void print(String message) {
        String encrypted = encrypt(message, key);
        wrappee.print(encrypted);
    }

    // --- Helpers (reversible) ---

    private static String encrypt(String message, String key) {
        byte[] data = message == null ? new byte[0] : message.getBytes(StandardCharsets.UTF_8);
        byte[] k = key.getBytes(StandardCharsets.UTF_8);
        byte[] out = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            out[i] = (byte) (data[i] ^ k[i % k.length]);
        }
        return Base64.getEncoder().encodeToString(out);
    }

    public static String decryptPrinted(String base64Cipher, String key) {
        byte[] enc = Base64.getDecoder().decode(base64Cipher);
        byte[] k = key.getBytes(StandardCharsets.UTF_8);
        byte[] out = new byte[enc.length];
        for (int i = 0; i < enc.length; i++) {
            out[i] = (byte) (enc[i] ^ k[i % k.length]);
        }
        return new String(out, StandardCharsets.UTF_8);
    }
}
