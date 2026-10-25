import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Mètodes per calcular el hash SHA-256
public class UtilsHash {

    // Hash d'un text
    public static String calcularHash(String text) {
        return calcularHash(text.getBytes(StandardCharsets.UTF_8));
    }

    // Hash de bytes (per la contrasenya + salt)
    public static String calcularHash(byte[] dades) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return aHexadecimal(sha256.digest(dades));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No s'ha trobat l'algoritme SHA-256: " + e.getMessage());
        }
    }

    // Passa el hash a hexadecimal
    public static String aHexadecimal(byte[] bytes) {
        StringBuilder textOut = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                textOut.append("-");
            }
            textOut.append(String.format("%02X", bytes[i] & 0xFF));
        }
        return textOut.toString();
    }
}
