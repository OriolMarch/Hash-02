import java.util.Scanner;

// Part 1: càlcul de resums amb SHA-256
public class MissatgesSimulacio {

    private Scanner scanner;

    public MissatgesSimulacio(Scanner scanner) {
        this.scanner = scanner;
    }

    public void principal() {
        String opcio;

        do {
            System.out.println();
            System.out.println("--- Part 1: Càlcul de resums ---");
            System.out.println("1. Calcular el hash d'un missatge");
            System.out.println("2. Comparar el hash de dos missatges");
            System.out.println("3. Modificar un missatge i comparar");
            System.out.println("4. Calcular el hash diverses vegades");
            System.out.println("0. Tornar");
            System.out.print("Opció: ");
            opcio = scanner.nextLine().trim();

            switch (opcio) {
                case "1":
                    calcularHash();
                    break;
                case "2":
                    compararMissatges();
                    break;
                case "3":
                    modificarMissatge();
                    break;
                case "4":
                    repetirHash();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opció no vàlida");
            }
        } while (!opcio.equals("0"));
    }

    // Opció 1
    private void calcularHash() {
        System.out.print("Entra el missatge: ");
        String missatge = scanner.nextLine();
        System.out.println("Hash: " + UtilsHash.calcularHash(missatge));
    }

    // Opció 2
    private void compararMissatges() {
        System.out.print("Entra el primer missatge: ");
        String hash1 = UtilsHash.calcularHash(scanner.nextLine());
        System.out.print("Entra el segon missatge: ");
        String hash2 = UtilsHash.calcularHash(scanner.nextLine());

        System.out.println("Hash 1: " + hash1);
        System.out.println("Hash 2: " + hash2);

        if (hash1.equals(hash2)) {
            System.out.println("Els hashes coincideixen");
        } else {
            System.out.println("Els hashes NO coincideixen");
        }
    }

    // Opció 3
    private void modificarMissatge() {
        System.out.print("Entra el missatge original: ");
        String hashOriginal = UtilsHash.calcularHash(scanner.nextLine());
        System.out.println("Hash original:   " + hashOriginal);

        System.out.print("Entra el missatge modificat: ");
        String hashModificat = UtilsHash.calcularHash(scanner.nextLine());
        System.out.println("Hash modificat:  " + hashModificat);

        if (hashOriginal.equals(hashModificat)) {
            System.out.println("El missatge no ha canviat, el hash és el mateix");
        } else {
            System.out.println("El missatge ha canviat i el hash és totalment diferent");
        }
    }

    // Opció 4
    private void repetirHash() {
        System.out.print("Entra el missatge: ");
        String missatge = scanner.nextLine();
        System.out.print("Quantes vegades el vols calcular? ");

        int vegades;
        try {
            vegades = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Has d'entrar un número");
            return;
        }
        if (vegades < 1) {
            System.out.println("Ha de ser 1 o més");
            return;
        }

        String primerHash = UtilsHash.calcularHash(missatge);
        boolean igual = true;

        for (int i = 1; i <= vegades; i++) {
            String hash = UtilsHash.calcularHash(missatge);
            System.out.println("Vegada " + i + ": " + hash);
            if (!hash.equals(primerHash)) {
                igual = false;
            }
        }

        if (igual) {
            System.out.println("El hash s'ha mantingut igual totes les vegades");
        } else {
            System.out.println("El hash ha canviat en alguna vegada");
        }
    }
}
