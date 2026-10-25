import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

public class EnviamentMissatges {

    private static final String FITXER_MISSATGE = "missatge.txt";
    private static final String FITXER_HASH = "missatge.hash";

    private Scanner scanner;

    public EnviamentMissatges(Scanner scanner) {
        this.scanner = scanner;
    }

    public void principal() {
        String opcio;

        do {
            System.out.println();
            System.out.println("--- Integritat dels missatges ---");
            System.out.println("1. Enviar missatge (emissor)");
            System.out.println("2. Rebre missatge (receptor)");
            System.out.println("0. Tornar");
            System.out.print("Opció: ");
            opcio = scanner.nextLine().trim();

            switch (opcio) {
                case "1":
                    enviarMissatge();
                    break;
                case "2":
                    rebreMissatge();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opció no vàlida");
            }
        } while (!opcio.equals("0"));
    }

    // Emissor: desa el missatge i el seu hash
    private void enviarMissatge() {
        System.out.print("Entra el missatge a enviar: ");
        String missatge = scanner.nextLine();
        String hash = UtilsHash.calcularHash(missatge);

        try {
            Files.writeString(Path.of(FITXER_MISSATGE), missatge);
            Files.writeString(Path.of(FITXER_HASH), hash);
            System.out.println("Missatge desat a " + FITXER_MISSATGE);
            System.out.println("Hash desat a " + FITXER_HASH + ": " + hash);
        } catch (IOException e) {
            System.out.println("No s'ha pogut desar el missatge: " + e.getMessage());
        }
    }

    // Receptor: calcula el hash del fitxer rebut i el compara amb l'original
    private void rebreMissatge() {
        String hashOriginal;
        try {
            hashOriginal = Files.readString(Path.of(FITXER_HASH)).trim();
        } catch (IOException e) {
            System.out.println("Primer has d'enviar un missatge (no existeix " + FITXER_HASH + ")");
            return;
        }

        System.out.println("Simulant la recepció del missatge...");
        System.out.print("Nom del fitxer rebut (" + FITXER_MISSATGE + " o missatgeCanviat.txt): ");
        String nomFitxer = scanner.nextLine().trim();

        String missatgeRebut;
        try {
            missatgeRebut = Files.readString(Path.of(nomFitxer));
        } catch (NoSuchFileException e) {
            System.out.println("No s'ha trobat el fitxer " + nomFitxer);
            return;
        } catch (IOException e) {
            System.out.println("No s'ha pogut llegir el fitxer " + nomFitxer + ": " + e.getMessage());
            return;
        }

        String hashRebut = UtilsHash.calcularHash(missatgeRebut);
        System.out.println("Missatge rebut: " + missatgeRebut);
        System.out.println("Hash original: " + hashOriginal);
        System.out.println("Hash rebut:    " + hashRebut);

        if (hashRebut.equals(hashOriginal)) {
            System.out.println("Els hashes coincideixen: el missatge és íntegre");
        } else {
            System.out.println("Els hashes NO coincideixen: s'ha detectat una modificació");
        }
    }
}
