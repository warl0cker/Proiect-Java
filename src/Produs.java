import java.io.Serializable;
import java.util.Objects;

public class Produs implements Serializable {
    public String denumire, unitateMasura;
    public float pret;
    public int cantitate;
    public Categorie categorie;

    enum Categorie {Breakfast, Pizza, Nonalcoolice, Alcoolice, Desert}

    public Produs() {
    }

    public Produs(String categorie, String denumire, int cantitate, String unitateMasura, float pret) {
        this.denumire = denumire;
        this.cantitate = cantitate;
        this.unitateMasura = unitateMasura;
        this.pret = pret;
        switch (categorie) {
            case "Breakfast" -> this.categorie = Categorie.Breakfast;
            case "Pizza" -> this.categorie = Categorie.Pizza;
            case "Nonalcoolice" -> this.categorie = Categorie.Nonalcoolice;
            case "Alcoolice" -> this.categorie = Categorie.Alcoolice;
            case "Desert" -> this.categorie = Categorie.Desert;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Produs other = (Produs) obj;
        return Objects.equals(this.denumire, other.denumire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denumire, unitateMasura, pret, cantitate, categorie);
    }
}