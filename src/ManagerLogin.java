import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ManagerLogin extends JPanel {
    private final JTextField managerid;
    private final JButton autentifica;

    public ManagerLogin() {
        setBorder(BorderFactory.createTitledBorder("Manager Login"));
        GestorEvenimenteML ec = new GestorEvenimenteML();
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        JLabel manager = new JLabel("Manager ID: ");
        manager.setFocusable(true);
        managerid = new JTextField("", 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(manager, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(managerid, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        autentifica = new JButton("Autentifica");
        autentifica.addActionListener(ec);
        add(autentifica, gbc);
        gbc.gridx++;
        gbc.gridwidth = 1;
        JButton inapoi = new JButton("Inapoi");
        inapoi.addActionListener(ec);
        add(inapoi, gbc);
    }

    private class GestorEvenimenteML implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == autentifica) {
                if (managerid.getText().isBlank()) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    JOptionPane.showMessageDialog(null, "Credentiale invalide!", "Eroare la autentificare!", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean authenticated = false;
                    for (Angajat angajat : Restaurant.angajati.lista) {
                        if (Integer.parseInt(managerid.getText()) == angajat.internalid && String.valueOf(angajat.pozitie).equals("manager")) {
                            authenticated = true;
                            break;
                        }
                    }
                    managerid.setText("");
                    if (authenticated) {
                        MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "managerFrame");
                    } else {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(null, "Credentiale invalide!", "Eroare la autentificare!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "fereastraPrincipala");
        }
    }
}