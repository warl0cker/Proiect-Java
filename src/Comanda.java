import java.io.Serializable;
import java.util.*;

public class Comanda implements Serializable {
    public Map<Produs, Integer> lista = new HashMap<>();
    public int masa;
    public float pret;
    public int chelnerid;

    public Comanda(int masa, float pret, int chelnerid) {
        this.masa = masa;
        this.pret = pret;
        this.chelnerid = chelnerid;
    }

    public Comanda() {
    }

    public void adaugaProdus(String denumire, Float pret) {
        this.pret = pret;
        int index = Restaurant.catalog.getIndex(denumire);
        int count = lista.getOrDefault(Restaurant.catalog.lista.get(index), -1);
        if (count != -1) {
            lista.put(Restaurant.catalog.lista.get(index), count + 1);
            FileManager.encodeFile();
            return;
        }
        lista.put(Restaurant.catalog.lista.get(index), 1);
        FileManager.encodeFile();
    }

    public void stergeProdus(String denumire, Float pret) {
        this.pret = pret;
        int index = Restaurant.catalog.getIndex(denumire);
        int count = lista.getOrDefault(Restaurant.catalog.lista.get(index), -1);
        if (count != 1) {
            lista.replace(Restaurant.catalog.lista.get(index), count - 1);
            FileManager.encodeFile();
            return;
        }
        lista.remove(Restaurant.catalog.lista.get(index));
        FileManager.encodeFile();
    }

    public void schimbaMasa(int masa) {
        this.masa = masa;
        FileManager.encodeFile();
    }
}