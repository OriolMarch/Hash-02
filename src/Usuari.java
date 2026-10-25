// Usuari registrat: només es guarda el nom, la salt i el hash (mai la contrasenya)
public class Usuari {

    private String nom;
    private byte[] salt;
    private String hash;

    public Usuari(String nom, byte[] salt, String hash) {
        this.nom = nom;
        this.salt = salt;
        this.hash = hash;
    }

    public String getNom() {
        return nom;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }
}
