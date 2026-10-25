import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;

// Parts 3 i 4: registre i inici de sessió amb hash i salt
public class GestioUsuaris {

    private static final int MIDA_SALT = 16;

    private Scanner scanner;
    private HashMap<String, Usuari> usuaris = new HashMap<>();
    private SecureRandom random = new SecureRandom();

    public GestioUsuaris(Scanner scanner) {
        this.scanner = scanner;
    }

    public void principal() {
        String opcio;

        do {
            System.out.println();
            System.out.println("--- Usuaris ---");
            System.out.println("1. Registrar usuari");
            System.out.println("2. Iniciar sessió");
            System.out.println("3. Prova: iniciar sessió amb una salt diferent");
            System.out.println("0. Tornar");
            System.out.print("Opció: ");
            opcio = scanner.nextLine().trim();

            switch (opcio) {
                case "1":
                    registrarUsuari();
                    break;
                case "2":
                    iniciarSessio(false);
                    break;
                case "3":
                    iniciarSessio(true);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opció no vàlida");
            }
        } while (!opcio.equals("0"));
    }

    // Part 3
    private void registrarUsuari() {
        System.out.print("Nom d'usuari: ");
        String nom = scanner.nextLine().trim();
        if (nom.isEmpty()) {
            System.out.println("El nom no pot estar buit");
            return;
        }
        if (usuaris.containsKey(nom)) {
            System.out.println("L'usuari " + nom + " ja existeix");
            return;
        }

        System.out.print("Contrasenya: ");
        String contrasenya = scanner.nextLine();
        if (contrasenya.isEmpty()) {
            System.out.println("La contrasenya no pot estar buida");
            return;
        }

        // Salt aleatòria i diferent per a cada registre
        byte[] salt = new byte[MIDA_SALT];
        random.nextBytes(salt);

        String hash = calcularHashAmbSalt(salt, contrasenya);
        usuaris.put(nom, new Usuari(nom, salt, hash));

        System.out.println("Usuari registrat:");
        System.out.println("  Nom:  " + nom);
        System.out.println("  Salt: " + UtilsHash.aHexadecimal(salt));
        System.out.println("  Hash: " + hash);
    }

    // Part 4 (i prova de la Part 5 si saltDiferent és true)
    private void iniciarSessio(boolean saltDiferent) {
        System.out.print("Nom d'usuari: ");
        String nom = scanner.nextLine().trim();
        System.out.print("Contrasenya: ");
        String contrasenya = scanner.nextLine();

        Usuari usuari = usuaris.get(nom);
        if (usuari == null) {
            System.out.println("L'usuari " + nom + " no està registrat");
            return;
        }

        // Normalment es fa servir la salt guardada de l'usuari
        byte[] salt = usuari.getSalt();
        if (saltDiferent) {
            salt = new byte[MIDA_SALT];
            random.nextBytes(salt);
            System.out.println("Salt guardada:   " + UtilsHash.aHexadecimal(usuari.getSalt()));
            System.out.println("Salt utilitzada: " + UtilsHash.aHexadecimal(salt));
        }

        String hashCalculat = calcularHashAmbSalt(salt, contrasenya);
        System.out.println("Hash calculat: " + hashCalculat);
        System.out.println("Hash guardat:  " + usuari.getHash());

        if (hashCalculat.equals(usuari.getHash())) {
            System.out.println("Accés permès. Benvingut/da, " + nom);
        } else if (saltDiferent) {
            System.out.println("Accés denegat: amb una salt diferent el hash no coincideix");
        } else {
            System.out.println("Contrasenya incorrecta. Accés denegat");
        }
    }

    // Hash de contrasenya + salt (la salt s'afegeix a la contrasenya, com als apunts)
    private String calcularHashAmbSalt(byte[] salt, String contrasenya) {
        byte[] bytesContrasenya = contrasenya.getBytes(StandardCharsets.UTF_8);
        byte[] dades = new byte[bytesContrasenya.length + salt.length];
        System.arraycopy(bytesContrasenya, 0, dades, 0, bytesContrasenya.length);
        System.arraycopy(salt, 0, dades, bytesContrasenya.length, salt.length);
        return UtilsHash.calcularHash(dades);
    }
}
