import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SchimbaMasaFrame extends JFrame {
    protected ArrayList<JButton> buttons;

    public SchimbaMasaFrame() {
        setLayout(new GridLayout(3, 3));
        buttons = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            buttons.add(new JButton(String.valueOf(i + 1)));
            buttons.get(i).addActionListener(new GestorEvenimenteSMF());
        }

        for (JButton i : buttons) {
            i.setPreferredSize(new Dimension(50, 50));
            i.setFocusable(false);
            boolean found = false;
            if (Restaurant.comenzi.lista == null) {
                i.setBackground(Color.GREEN);
                add(i);
            }
            for (Comanda q : Restaurant.comenzi.lista) {
                if (q.masa == Integer.parseInt(i.getText())) {
                    i.setBackground(Color.RED);
                    found = true;
                    add(i);
                }
            }
            if (!found) {
                i.setBackground(Color.GREEN);
                add(i);
            }
        }
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
    }

    private class GestorEvenimenteSMF implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (((JButton) e.getSource()).getBackground().equals(Color.RED)) {
                JDialog.setDefaultLookAndFeelDecorated(true);
                JOptionPane.showMessageDialog(null, "Masa este ocupata!", "Eroare!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int index = 0;
            for (int i = 0; i < Restaurant.comenzi.lista.size(); i++)
                if (Restaurant.comenzi.lista.get(i).masa == FereastraPrincipala.lastClicked) {
                    index = i;
                    break;
                }
            buttons.get(Restaurant.comenzi.lista.get(index).masa - 1).setBackground(Color.GREEN);
            Restaurant.comenzi.lista.get(index).schimbaMasa(Integer.parseInt(((JButton) e.getSource()).getText()));
            ((JButton) e.getSource()).setBackground(Color.RED);
        }
    }
}
