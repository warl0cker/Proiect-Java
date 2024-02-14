import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManagerFrame extends JPanel {
    private final JButton catalog, angajati;
    private AngajatiPanel angajatiPanel;
    private CatalogPanel catalogPanel;

    public ManagerFrame() {
        setBorder(BorderFactory.createTitledBorder("Manager Control Panel"));
        GestorEvenimenteMF ec = new GestorEvenimenteMF();
        setLayout(new BorderLayout(2, 1));

        JPanel buttons = new JPanel();
        angajatiPanel = new AngajatiPanel();
        populeazaTabelAngajati();

        angajati = new JButton("Angajati");
        JButton back = new JButton("Inapoi");
        catalog = new JButton("Catalog");

        angajati.setPreferredSize(new Dimension(250, 50));
        catalog.setPreferredSize(new Dimension(250, 50));
        back.setPreferredSize(new Dimension(250, 50));

        angajati.addActionListener(ec);
        back.addActionListener(ec);
        catalog.addActionListener(ec);

        buttons.add(angajati);
        buttons.add(catalog);
        buttons.add(back);

        add(buttons, BorderLayout.NORTH);
        add(angajatiPanel, BorderLayout.CENTER);
    }

    private class GestorEvenimenteMF implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == angajati) {
                if (angajatiPanel != null) remove(angajatiPanel);
                if (catalogPanel != null) remove(catalogPanel);
                angajatiPanel = new AngajatiPanel();
                populeazaTabelAngajati();
                add(angajatiPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            } else if (e.getSource() == catalog) {
                if (angajatiPanel != null) remove(angajatiPanel);
                if (catalogPanel != null) remove(catalogPanel);
                catalogPanel = new CatalogPanel();
                populeazaCatalog();
                add(catalogPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            } else MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "fereastraPrincipala");
        }
    }

    public static void populeazaTabelAngajati() {
        for (Angajat angajat : Restaurant.angajati.lista) {
            AngajatiPanel.model.addRow(new Object[]{angajat.internalid, angajat.pozitie, angajat.nume, angajat.prenume});
        }
    }

    public static void populeazaCatalog() {
        for (Produs produs : Restaurant.catalog.lista) {
            CatalogPanel.model.addRow(new Object[]{produs.categorie, produs.denumire, produs.cantitate, produs.unitateMasura, produs.pret});
        }
    }

    public static void resetTabel(DefaultTableModel model) {
        model.setRowCount(0);
    }
}