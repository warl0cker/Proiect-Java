import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChelnerLogin extends JPanel {
    protected static JTextField chelnerid;
    private final JButton autentifica;

    public ChelnerLogin() {
        setBorder(BorderFactory.createTitledBorder("Chelner Login"));
        GestorEvenimenteCL ec = new GestorEvenimenteCL();
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        JLabel chelner = new JLabel("Chelner ID: ");
        chelner.setFocusable(true);
        chelnerid = new JTextField("", 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(chelner, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(chelnerid, gbc);
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

    private class GestorEvenimenteCL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == autentifica) {
                if (chelnerid.getText().isBlank()) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    JOptionPane.showMessageDialog(null, "Credentiale invalide!", "Eroare la autentificare!", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean authenticated = false;
                    for (Angajat angajat : Restaurant.angajati.lista) {
                        if (Integer.parseInt(chelnerid.getText()) == angajat.internalid && String.valueOf(angajat.pozitie).equals("chelner")) {
                            authenticated = true;
                            break;
                        }
                    }
                    if (authenticated) {
                        if (Restaurant.comenzi.lista.size() != 0) {
                            for (int i = 0; i < Restaurant.comenzi.lista.size(); i++) {
                                if (Restaurant.comenzi.lista.get(i).masa == FereastraPrincipala.lastClicked) {
                                    if (Restaurant.comenzi.lista.get(i).chelnerid == Integer.parseInt(chelnerid.getText())) {
                                        ChelnerFrame.populeazaTabelComanda(FereastraPrincipala.lastClicked);
                                        MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "chelnerFrame");
                                        return;
                                    } else {
                                        chelnerid.setText("");
                                        JDialog.setDefaultLookAndFeelDecorated(true);
                                        JOptionPane.showMessageDialog(null, "Masa este ocupata de alt chelner!", "Eroare la autentificare!", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                }
                            }
                            ChelnerFrame.populeazaTabelComanda(FereastraPrincipala.lastClicked);
                            MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "chelnerFrame");
                        } else {
                            ChelnerFrame.populeazaTabelComanda(FereastraPrincipala.lastClicked);
                            MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "chelnerFrame");
                        }
                    } else {
                        chelnerid.setText("");
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(null, "Credentiale invalide!", "Eroare la autentificare!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "fereastraPrincipala");
        }
    }
}