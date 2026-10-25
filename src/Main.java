import java.util.Scanner;

public class Main {

    // Un sol Scanner per a tot el programa
    private Scanner scanner = new Scanner(System.in);

    // Es crea un sol cop perquè no es perdin els usuaris en tornar al menú
    private GestioUsuaris gestioUsuaris = new GestioUsuaris(scanner);

    public static void main(String[] args) {
        Main p = new Main();
        p.principal();
    }

    public void principal() {
        String opcio;

        do {
            System.out.println();
            System.out.println("===== PE02 - Hash i Salt =====");
            System.out.println("1. Part 1: Càlcul de resums");
            System.out.println("2. Part 2: Integritat dels missatges");
            System.out.println("3. Parts 3 i 4: Usuaris");
            System.out.println("0. Sortir");
            System.out.print("Opció: ");
            opcio = scanner.nextLine().trim();

            switch (opcio) {
                case "1":
                    new MissatgesSimulacio(scanner).principal();
                    break;
                case "2":
                    new EnviamentMissatges(scanner).principal();
                    break;
                case "3":
                    gestioUsuaris.principal();
                    break;
                case "0":
                    System.out.println("Fins aviat!");
                    break;
                default:
                    System.out.println("Opció no vàlida");
            }
        } while (!opcio.equals("0"));

        scanner.close();
    }
}
