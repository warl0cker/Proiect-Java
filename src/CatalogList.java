import java.io.*;
import java.util.*;

public class CatalogList {
    protected ArrayList<Produs> lista = new ArrayList<>();

    public void incarcaProduse() {
        List<String> line = FileManager.readFile(new File("resources/produse.txt"));
        if (line == null) return;
        for (String string : line) {
            String[] temp = string.split(",");
            lista.add(new Produs(temp[0], temp[1], Integer.parseInt(temp[2]), temp[3], Float.parseFloat(temp[4])));
        }
    }

    public void stergeProdus(int n) {
        lista.remove(n);
        ManagerFrame.resetTabel(CatalogPanel.model);
        ManagerFrame.populeazaCatalog();
        FileManager.writeFile(new File("resources/produse.txt"), "CatalogList");
    }

    public void adaugaProdus(String categorie, String denumire, int cantitate, String unitateMasura, float pret) {
        lista.add(new Produs(categorie, denumire, cantitate, unitateMasura, pret));
        FileManager.writeFile(new File("resources/produse.txt"), "CatalogList");
    }

    public void modificaProdus(int pozitie, String categorie, String denumire, int cantitate, String unitateMasura, float pret) {
        lista.set(pozitie, new Produs(categorie, denumire, cantitate, unitateMasura, pret));
        FileManager.writeFile(new File("resources/produse.txt"), "CatalogList");
    }

    public int getIndex(String denumire) {
        for (int i = 0; i < lista.size(); i++) if (lista.get(i).denumire.equals(denumire)) return i;
        return -1;
    }
}