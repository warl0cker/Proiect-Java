import java.io.*;
import java.util.*;
import javax.swing.*;
import java.nio.file.Files;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public class FileManager {
    public static List<String> readFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (Exception e) {
            JDialog.setDefaultLookAndFeelDecorated(true);
            JOptionPane.showMessageDialog(null, "Eroare la citirea fisierului " + file);
        }
        return null;
    }

    public static void writeFile(File file, String panel) {
        PrintWriter writer = null;
        if (panel.equals("AngajatiList")) {
            try {
                writer = new PrintWriter(new FileWriter(file));
                for (Angajat angajat : Restaurant.angajati.lista) {
                    writer.printf("%s,%s,%s,%s", angajat.internalid, angajat.pozitie, angajat.nume, angajat.prenume);
                    writer.println();
                }
            } catch (Exception e) {
                JDialog.setDefaultLookAndFeelDecorated(true);
                JOptionPane.showMessageDialog(null, "Eroare la citirea fisierului " + file);
            }
        } else {
            try {
                writer = new PrintWriter(new FileWriter(file));
                for (Produs produs : Restaurant.catalog.lista) {
                    writer.printf("%s,%s,%s,%s,%s", produs.categorie, produs.denumire, produs.cantitate, produs.unitateMasura, produs.pret);
                    writer.println();
                }
            } catch (Exception e) {
                JDialog.setDefaultLookAndFeelDecorated(true);
                JOptionPane.showMessageDialog(null, "Eroare la citirea fisierului " + file);
            }
        }
        if (writer != null) writer.close();
    }

    public static void encodeFile() {
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream("resources/comenzi.xml"));
            encoder.writeObject(Restaurant.comenzi.lista);
            encoder.close();
        } catch (IOException e) {
            JDialog.setDefaultLookAndFeelDecorated(true);
            JOptionPane.showMessageDialog(null, "Eroare la scrierea fisierului de comenzi!");
        }
    }

    public static ArrayList<Comanda> decodeFile() {
        try {
            XMLDecoder decoder = new XMLDecoder(new FileInputStream("resources/comenzi.xml"));
            ArrayList<Comanda> temp = (ArrayList<Comanda>) decoder.readObject();
            decoder.close();
            return temp;
        } catch (IOException e) {
            return null;
        }
    }
}