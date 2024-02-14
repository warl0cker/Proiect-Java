import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FereastraPrincipala extends JPanel {
    private final JButton manager;
    protected static JButton[] mese;
    protected static int lastClicked;

    public FereastraPrincipala() {
        setBorder(BorderFactory.createTitledBorder("Fereastra Principala"));
        mese = new JButton[9];
        GestorEvenimenteFP ec = new GestorEvenimenteFP();
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        manager = new JButton("Manager") {{
            setSize(100, 100);
        }};
        manager.setHorizontalTextPosition(JButton.CENTER);
        manager.setVerticalTextPosition(JButton.CENTER);
        manager.setContentAreaFilled(true);
        manager.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        manager.addActionListener(ec);
        add(manager, gbc);

        mese[0] = new JButton("Masa 1") {{
            setSize(100, 100);
        }};
        mese[0].setIcon(new ImageIcon("resources/masa2.png"));
        mese[0].setHorizontalTextPosition(JButton.CENTER);
        mese[0].setVerticalTextPosition(JButton.CENTER);
        mese[0].setContentAreaFilled(false);
        mese[0].setFocusable(false);
        mese[0].setOpaque(true);
        mese[0].setBackground(Color.GREEN);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mese[0].addActionListener(ec);
        add(mese[0], gbc);

        mese[1] = new JButton("Masa 2") {{
            setSize(100, 100);
        }};
        mese[1].setIcon(new ImageIcon("resources/masa1.png"));
        mese[1].setHorizontalTextPosition(JButton.CENTER);
        mese[1].setVerticalTextPosition(JButton.CENTER);
        mese[1].setContentAreaFilled(false);
        mese[1].setFocusable(false);
        mese[1].setOpaque(true);
        mese[1].setBackground(Color.GREEN);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mese[1].addActionListener(ec);
        add(mese[1], gbc);

        mese[2] = new JButton("Masa 3") {{
            setSize(100, 100);
        }};
        mese[2].setIcon(new ImageIcon("resources/masa2.png"));
        mese[2].setHorizontalTextPosition(JButton.CENTER);
        mese[2].setVerticalTextPosition(JButton.CENTER);
        mese[2].setContentAreaFilled(false);
        mese[2].setFocusable(false);
        mese[2].setOpaque(true);
        mese[2].setBackground(Color.GREEN);
        gbc.gridx = 2;
        gbc.gridy = 1;
        mese[2].addActionListener(ec);
        add(mese[2], gbc);

        mese[3] = new JButton("Masa 4") {{
            setSize(100, 100);
        }};
        mese[3].setIcon(new ImageIcon("resources/masa2.png"));
        mese[3].setHorizontalTextPosition(JButton.CENTER);
        mese[3].setVerticalTextPosition(JButton.CENTER);
        mese[3].setContentAreaFilled(false);
        mese[3].setFocusable(false);
        mese[3].setOpaque(true);
        mese[3].setBackground(Color.GREEN);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mese[3].addActionListener(ec);
        add(mese[3], gbc);

        mese[4] = new JButton("Masa 5") {{
            setSize(100, 100);
        }};
        mese[4].setIcon(new ImageIcon("resources/masa1.png"));
        mese[4].setHorizontalTextPosition(JButton.CENTER);
        mese[4].setVerticalTextPosition(JButton.CENTER);
        mese[4].setContentAreaFilled(false);
        mese[4].setFocusable(false);
        mese[4].setOpaque(true);
        mese[4].setBackground(Color.GREEN);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mese[4].addActionListener(ec);
        add(mese[4], gbc);

        mese[5] = new JButton("Masa 6") {{
            setSize(100, 100);
        }};
        mese[5].setIcon(new ImageIcon("resources/masa2.png"));
        mese[5].setHorizontalTextPosition(JButton.CENTER);
        mese[5].setVerticalTextPosition(JButton.CENTER);
        mese[5].setContentAreaFilled(false);
        mese[5].setFocusable(false);
        mese[5].setOpaque(true);
        mese[5].setBackground(Color.GREEN);
        gbc.gridx = 2;
        gbc.gridy = 2;
        mese[5].addActionListener(ec);
        add(mese[5], gbc);

        mese[6] = new JButton("Masa 7") {{
            setSize(100, 100);
        }};
        mese[6].setIcon(new ImageIcon("resources/masa2.png"));
        mese[6].setHorizontalTextPosition(JButton.CENTER);
        mese[6].setVerticalTextPosition(JButton.CENTER);
        mese[6].setContentAreaFilled(false);
        mese[6].setFocusable(false);
        mese[6].setOpaque(true);
        mese[6].setBackground(Color.GREEN);
        gbc.gridx = 0;
        gbc.gridy = 3;
        mese[6].addActionListener(ec);
        add(mese[6], gbc);

        mese[7] = new JButton("Masa 8") {{
            setSize(100, 100);
        }};
        mese[7].setIcon(new ImageIcon("resources/masa1.png"));
        mese[7].setHorizontalTextPosition(JButton.CENTER);
        mese[7].setVerticalTextPosition(JButton.CENTER);
        mese[7].setContentAreaFilled(false);
        mese[7].setFocusable(false);
        mese[7].setOpaque(true);
        mese[7].setBackground(Color.GREEN);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mese[7].addActionListener(ec);
        add(mese[7], gbc);

        mese[8] = new JButton("Masa 9") {{
            setSize(100, 100);
        }};
        mese[8].setIcon(new ImageIcon("resources/masa2.png"));
        mese[8].setHorizontalTextPosition(JButton.CENTER);
        mese[8].setVerticalTextPosition(JButton.CENTER);
        mese[8].setContentAreaFilled(false);
        mese[8].setFocusable(false);
        mese[8].setOpaque(true);
        mese[8].setBackground(Color.GREEN);
        gbc.gridx = 2;
        gbc.gridy = 3;
        mese[8].addActionListener(ec);
        add(mese[8], gbc);
    }

    public static void colorTables() {
        for (int i = 0; i < 9; i++) FereastraPrincipala.mese[i].setBackground(Color.GREEN);
        if (Restaurant.comenzi.lista.size() > 0) {
            for (int i = 0; i < Restaurant.comenzi.lista.size(); i++) {
                if (FereastraPrincipala.mese[Restaurant.comenzi.lista.get(i).masa - 1].getBackground() == Color.GREEN)
                    FereastraPrincipala.mese[Restaurant.comenzi.lista.get(i).masa - 1].setBackground((Color.RED));
            }
        }
    }

    private class GestorEvenimenteFP implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == manager) {
                MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "managerLogin");
            } else {
                lastClicked = Integer.parseInt(((JButton) e.getSource()).getText().split(" ")[1]);
                MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "chelnerLogin");
            }
        }
    }
}