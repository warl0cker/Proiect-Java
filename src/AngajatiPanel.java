import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AngajatiPanel extends JPanel {
    public static DefaultTableModel model;
    private final JTable tabel;
    private final JButton adauga;
    private final JButton modifica;
    private final JButton sterge;
    private final JTextField internalid;
    private final JTextField nume;
    private final JTextField prenume;
    private final JComboBox pozitie;

    public AngajatiPanel() {
        GestorEvenimenteAP ec = new GestorEvenimenteAP();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        tabel = new JTable();
        tabel.setDefaultEditor(Object.class, null);
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String[] coloane = {"ID", "Pozitie", "Nume", "Prenume"};
        model = new DefaultTableModel(coloane, 0);
        tabel.setModel(model);

        JScrollPane scrollPane = new JScrollPane(tabel);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(scrollPane, gbc);

        JLabel internalidLabel = new JLabel("ID", SwingConstants.CENTER);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(internalidLabel, gbc);

        JLabel pozitieLabel = new JLabel("Pozitie", SwingConstants.CENTER);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(pozitieLabel, gbc);

        JLabel numeLabel = new JLabel("Nume", SwingConstants.CENTER);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(numeLabel, gbc);

        JLabel prenumeLabel = new JLabel("Prenume", SwingConstants.CENTER);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(prenumeLabel, gbc);

        internalid = new JTextField(10);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(internalid, gbc);

        String[] pozitii = {"selectati", "manager", "chelner"};
        pozitie = new JComboBox(pozitii);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(pozitie, gbc);

        nume = new JTextField(10);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(nume, gbc);

        prenume = new JTextField(10);
        gbc.gridx++;
        gbc.gridwidth = 1;
        add(prenume, gbc);

        JPanel buttons = new JPanel();
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
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(buttons, gbc);
    }

    private class GestorEvenimenteAP implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == modifica) {
                if ("disable".equals(e.getActionCommand())) {
                    if (tabel.getSelectedRow() == -1) {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(null, "Nu ati selectat nici un angajat!", "Eroare la modificare!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        internalid.setText(tabel.getValueAt(tabel.getSelectedRow(), 0).toString());
                        internalid.setEditable(false);
                        pozitie.setSelectedItem(tabel.getValueAt(tabel.getSelectedRow(), 1));
                        nume.setText(tabel.getValueAt(tabel.getSelectedRow(), 2).toString());
                        prenume.setText(tabel.getValueAt(tabel.getSelectedRow(), 3).toString());
                        adauga.setEnabled(false);
                        sterge.setEnabled(false);
                        modifica.setText("Salveaza");
                        modifica.setActionCommand("enable");
                    }
                } else {
                    if (String.valueOf(pozitie.getSelectedItem()).equals("selectati") || nume.getText().isBlank() || prenume.getText().isBlank()) {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(null, "Campuri invalide!", "Eroare la modificare!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        model.setValueAt(String.valueOf(pozitie.getSelectedItem()), tabel.getSelectedRow(), 1);
                        model.setValueAt(nume.getText().trim(), tabel.getSelectedRow(), 2);
                        model.setValueAt(prenume.getText().trim(), tabel.getSelectedRow(), 3);
                        internalid.setEditable(true);
                        adauga.setEnabled(true);
                        sterge.setEnabled(true);
                        modifica.setText("Modifica");
                        modifica.setActionCommand("disable");
                        Restaurant.angajati.modificaAngajat(Integer.parseInt(internalid.getText().trim()), String.valueOf(pozitie.getSelectedItem()), nume.getText().trim(), prenume.getText().trim());
                        internalid.setText("");
                        pozitie.setSelectedIndex(0);
                        nume.setText("");
                        prenume.setText("");
                    }
                }
            } else if (e.getSource() == adauga) {
                if (internalid.getText().isBlank() || String.valueOf(pozitie.getSelectedItem()).equals("selectati") || nume.getText().isBlank() || prenume.getText().isBlank()) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    JOptionPane.showMessageDialog(null, "Campuri goale!", "Eroare la adaugare!", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean invalid = false;
                    for (int i = 0; i < Restaurant.angajati.lista.size(); i++) {
                        if (Integer.parseInt(internalid.getText().trim()) == Restaurant.angajati.lista.get(i).internalid)
                            invalid = true;
                    }
                    if (invalid || Integer.parseInt(internalid.getText().trim()) < 10001 || Integer.parseInt(internalid.getText().trim()) > 99998) {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(null, "Ati introdus un ID invalid!", "Eroare la adaugare!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Restaurant.angajati.adaugaAngajat(Integer.parseInt(internalid.getText().trim()), String.valueOf(pozitie.getSelectedItem()), nume.getText().trim(), prenume.getText().trim());
                        model.addRow(new Object[]{Integer.parseInt(internalid.getText().trim()), String.valueOf(pozitie.getSelectedItem()), nume.getText().trim(), prenume.getText().trim()});
                    }
                }
                internalid.setText("");
                pozitie.setSelectedIndex(0);
                nume.setText("");
                prenume.setText("");
            } else if (e.getSource() == sterge) {
                if (tabel.getSelectedRow() == -1) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    JOptionPane.showMessageDialog(null, "Nu ati selectat nici un angajat!", "Eroare la stergere!", JOptionPane.ERROR_MESSAGE);
                } else {
                    Restaurant.angajati.stergeAngajat(tabel.getSelectedRow());
                }
                internalid.setText("");
                pozitie.setSelectedIndex(0);
                nume.setText("");
                prenume.setText("");
            }
        }
    }
}