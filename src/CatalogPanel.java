import java.awt.*;
import java.awt.event.*;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CatalogPanel extends JPanel {
    public static DefaultTableModel model;
    private final JTable tabel;
    private final JButton printeaza, adauga, modifica, sterge;
    private final JTextField denumire, cantitate, masura, pret;
    private final JComboBox categorie;
    private final JScrollPane scrollPane;

    public CatalogPanel() {
        GestorEvenimenteCP ec = new GestorEvenimenteCP();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        tabel = new JTable();
        tabel.setDefaultEditor(Object.class, null);
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String[] coloane = {"Categorie", "Denumire", "Cantitate", "Unitate Masura", "Pret"};
        model = new DefaultTableModel(coloane, 0);
        tabel.setModel(model);

        scrollPane = new JScrollPane(tabel);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(scrollPane, gbc);

        JLabel categorieLabel = new JLabel("Categorie", SwingConstants.CENTER);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(categorieLabel, gbc);

        JLabel denumireLabel = new JLabel("Denumire", SwingConstants.CENTER);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(denumireLabel, gbc);

        JLabel cantitateLabel = new JLabel("Cantitate", SwingConstants.CENTER);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(cantitateLabel, gbc);

        JLabel masuraLabel = new JLabel("Unitate Masura", SwingConstants.CENTER);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(masuraLabel, gbc);

        JLabel pretLabel = new JLabel("Pret", SwingConstants.CENTER);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(pretLabel, gbc);

        String[] categorii = {"selectati", "Breakfast", "Pizza", "Nonalcoolice", "Alcoolice", "Desert"};
        categorie = new JComboBox(categorii);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(categorie, gbc);

        denumire = new JTextField(10);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(denumire, gbc);

        cantitate = new JTextField(10);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(cantitate, gbc);

        masura = new JTextField(10);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(masura, gbc);

        pret = new JTextField(10);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(pret, gbc);

        JPanel buttons = new JPanel();
        printeaza = new JButton("Printeaza Catalog");
        printeaza.setFocusable(false);
        printeaza.addActionListener(ec);
        buttons.add(printeaza);

        adauga = new JButton("Adauga");
        adauga.setFocusable(false);
        adauga.addActionListener(ec);
        buttons.add(adauga);

        modifica = new JButton("Modifica");
        modifica.setFocusable(false);
        modifica.addActionListener(ec);
        modifica.setActionCommand("disable");
        buttons.add(modifica);

        sterge = new JButton("Sterge");
        sterge.setFocusable(false);
        sterge.addActionListener(ec);
        buttons.add(sterge);
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        add(buttons, gbc);
    }

    private class GestorEvenimenteCP implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == printeaza) {
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                printerJob.setJobName("printeaza-catalog");
                printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
                    if (pageIndex > 0) return Printable.NO_SUCH_PAGE;
                    Graphics2D graphics2D = (Graphics2D) graphics;
                    graphics2D.translate(pageFormat.getImageableX() * 2, pageFormat.getImageableY() * 2);
                    double scaleX = pageFormat.getImageableWidth() / scrollPane.getWidth();
                    double scaleY = pageFormat.getImageableHeight() / scrollPane.getHeight();
                    double scale = Math.min(scaleX, scaleY);
                    graphics2D.scale(scale, scale);
                    scrollPane.paint(graphics2D);
                    return Printable.PAGE_EXISTS;
                });
                boolean returnResult = printerJob.printDialog();
                if (returnResult) {
                    try {
                        printerJob.print();
                    } catch (PrinterException printerException) {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(null, printerException.getMessage(), "Eroare la printare!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (e.getSource() == modifica) {
                if ("disable".equals(e.getActionCommand())) {
                    if (tabel.getSelectedRow() == -1) {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(null, "Nu ati selectat nici un produs!", "Eroare la modificare produs!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        categorie.setSelectedItem(tabel.getValueAt(tabel.getSelectedRow(), 0));
                        denumire.setText(tabel.getValueAt(tabel.getSelectedRow(), 1).toString());
                        cantitate.setText(tabel.getValueAt(tabel.getSelectedRow(), 2).toString());
                        masura.setText(tabel.getValueAt(tabel.getSelectedRow(), 3).toString());
                        pret.setText(tabel.getValueAt(tabel.getSelectedRow(), 4).toString());
                        adauga.setEnabled(false);
                        sterge.setEnabled(false);
                        modifica.setText("Salveaza");
                        modifica.setActionCommand("enable");
                    }
                } else {
                    if (String.valueOf(categorie.getSelectedItem()).equals("selectati") || denumire.getText().isBlank() || cantitate.getText().isBlank() || masura.getText().isBlank() || pret.getText().isBlank()) {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(null, "Campuri invalide!", "Eroare la modificare!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int pozitie = 0;
                        for (Produs produs : Restaurant.catalog.lista) {
                            if ((tabel.getValueAt(tabel.getSelectedRow(), 1).toString()).equals(produs.denumire)) break;
                            pozitie++;
                        }
                        model.setValueAt(String.valueOf(categorie.getSelectedItem()), tabel.getSelectedRow(), 0);
                        model.setValueAt(denumire.getText().trim(), tabel.getSelectedRow(), 1);
                        model.setValueAt(cantitate.getText().trim(), tabel.getSelectedRow(), 2);
                        model.setValueAt(masura.getText().trim(), tabel.getSelectedRow(), 3);
                        model.setValueAt(pret.getText().trim(), tabel.getSelectedRow(), 4);
                        adauga.setEnabled(true);
                        sterge.setEnabled(true);
                        modifica.setText("Modifica");
                        modifica.setActionCommand("disable");
                        Restaurant.catalog.modificaProdus(pozitie, String.valueOf(categorie.getSelectedItem()), denumire.getText().trim(), Integer.parseInt(cantitate.getText().trim()), masura.getText().trim(), Float.parseFloat(pret.getText().trim()));
                        categorie.setSelectedIndex(0);
                        denumire.setText("");
                        cantitate.setText("");
                        masura.setText("");
                        pret.setText("");
                    }
                }
            } else if (e.getSource() == adauga) {
                if (String.valueOf(categorie.getSelectedItem()).equals("selectati") || denumire.getText().isBlank() || cantitate.getText().isBlank() || masura.getText().isBlank() || pret.getText().isBlank()) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    JOptionPane.showMessageDialog(null, "Campuri goale!", "Eroare la adaugare produs!", JOptionPane.ERROR_MESSAGE);
                } else {
                    Restaurant.catalog.adaugaProdus(String.valueOf(categorie.getSelectedItem()), denumire.getText().trim(), Integer.parseInt(cantitate.getText().trim()), masura.getText().trim(), Float.parseFloat(pret.getText().trim()));
                    model.addRow(new Object[]{String.valueOf(categorie.getSelectedItem()), denumire.getText().trim(), Integer.parseInt(cantitate.getText().trim()), masura.getText().trim(), Float.parseFloat(pret.getText().trim())});
                }
                categorie.setSelectedIndex(0);
                denumire.setText("");
                cantitate.setText("");
                masura.setText("");
                pret.setText("");
            } else if (e.getSource() == sterge) {
                if (tabel.getSelectedRow() == -1) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    JOptionPane.showMessageDialog(null, "Nu ati selectat nici un produs!", "Eroare la stergere produs!", JOptionPane.ERROR_MESSAGE);
                } else {
                    Restaurant.catalog.stergeProdus(tabel.getSelectedRow());
                }
                categorie.setSelectedIndex(0);
                denumire.setText("");
                cantitate.setText("");
                masura.setText("");
                pret.setText("");
            }
        }
    }
}