import java.io.*;
import java.util.*;

public class AngajatiList {
    protected ArrayList<Angajat> lista = new ArrayList<>();

    public void adaugaAngajat(int id, String pozitie, String nume, String prenume) {
        lista.add(new Angajat(id, pozitie, nume, prenume));
        FileManager.writeFile(new File("resources/angajati.txt"), "AngajatiList");
    }

    public void stergeAngajat(int n) {
        lista.remove(n);
        ManagerFrame.resetTabel(AngajatiPanel.model);
        ManagerFrame.populeazaTabelAngajati();
        FileManager.writeFile(new File("resources/angajati.txt"), "AngajatiList");
    }

    public void modificaAngajat(int internalid, String pozitie, String nume, String prenume) {
        int cnt = 0;
        for (Angajat angajat : lista) {
            if (angajat.internalid == internalid) {
                lista.set(cnt, new Angajat(internalid, pozitie, nume, prenume));
            }
            cnt++;
        }
        FileManager.writeFile(new File("resources/angajati.txt"), "AngajatiList");
    }

    public void incarcaAngajati() {
        List<String> line = FileManager.readFile(new File("resources/angajati.txt"));
        if (line == null) return;
        for (String string : line) {
            String[] temp = string.split(",");
            lista.add(new Angajat(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3]));
        }
    }
}