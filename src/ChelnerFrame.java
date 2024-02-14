import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Map;

public class ChelnerFrame extends JPanel {
    protected static DefaultTableModel modelTabelProduse, modelTabelComanda;
    protected final JScrollPane scrollPaneProduse, scrollPaneComanda;
    protected final JButton inapoi, breakfast, pizza, nonalcoolice, alcoolice, desert, mutaMasa, adaugaProdus, stergeProdus, printeazaBon;
    protected static JPanel buttonsAndPret, pretTotal, leftFrame, rightFrame, buttonsCatalog, buttonsComanda, buttonsHelp, tabelProduse, tabelComanda;
    protected static JTable tabelComandaProduse, tabelComandaTabel;
    protected static JLabel pretTotalLabel;
    protected static float costTotal = 0;
    protected static String pretTotalText = "Pret Total: " + costTotal + " Lei";

    public ChelnerFrame() {
        setBorder(BorderFactory.createTitledBorder("Plaseaza Comanda"));
        GestorEvenimenteCF ec = new GestorEvenimenteCF();
        setLayout(new BorderLayout(1, 3));

        leftFrame = new JPanel(new BorderLayout(2, 1));
        rightFrame = new JPanel(new BorderLayout(4, 1));
        buttonsAndPret = new JPanel(new BorderLayout(2, 1));
        tabelProduse = new JPanel(new GridBagLayout());
        tabelComanda = new JPanel(new GridBagLayout());
        buttonsCatalog = new JPanel();
        buttonsComanda = new JPanel();
        buttonsHelp = new JPanel();
        pretTotal = new JPanel();

        GridBagConstraints gbcComanda = new GridBagConstraints();
        GridBagConstraints gbcProduse = new GridBagConstraints();

        leftFrame.setBorder(BorderFactory.createTitledBorder("Catalog Produse"));
        rightFrame.setBorder(BorderFactory.createTitledBorder("Comanda Bon"));
        pretTotal.setBorder(BorderFactory.createTitledBorder("Comanda Total"));

        breakfast = new JButton("Breakfast");
        pizza = new JButton("Pizza");
        nonalcoolice = new JButton("NonAlcoolice");
        alcoolice = new JButton("Alcoolice");
        desert = new JButton("Desert");
        mutaMasa = new JButton("Muta Masa");
        adaugaProdus = new JButton("Adauga Produs");
        stergeProdus = new JButton("Sterge Produs");
        printeazaBon = new JButton("Printeaza Bon");
        inapoi = new JButton("Inapoi");
        pretTotalLabel = new JLabel(pretTotalText, SwingConstants.RIGHT);

        mutaMasa.setPreferredSize(new Dimension(100, 50));
        printeazaBon.setPreferredSize(new Dimension(150, 50));
        inapoi.setPreferredSize(new Dimension(100, 50));

        breakfast.addActionListener(ec);
        pizza.addActionListener(ec);
        nonalcoolice.addActionListener(ec);
        alcoolice.addActionListener(ec);
        desert.addActionListener(ec);
        mutaMasa.addActionListener(ec);
        adaugaProdus.addActionListener(ec);
        stergeProdus.addActionListener(ec);
        printeazaBon.addActionListener(ec);
        inapoi.addActionListener(ec);

        tabelComandaProduse = new JTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        tabelComandaProduse.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tabelComandaProduse.setDefaultEditor(Object.class, null);
        tabelComandaProduse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String[] coloane = {"Denumire", "Cantitate", "Unitate Masura", "Pret"};
        modelTabelProduse = new DefaultTableModel(coloane, 0);
        tabelComandaProduse.setModel(modelTabelProduse);

        gbcProduse.fill = GridBagConstraints.BOTH;
        gbcProduse.weightx = 0.1;
        gbcProduse.weighty = 0.1;
        gbcProduse.gridwidth = 4;
        gbcProduse.gridx = 0;
        gbcProduse.gridy = 0;
        scrollPaneProduse = new JScrollPane(tabelComandaProduse);
        tabelProduse.add(scrollPaneProduse, gbcProduse);
        tabelComandaTabel = new JTable();
        tabelComandaTabel.setPreferredScrollableViewportSize(tabelComandaTabel.getPreferredSize());
        tabelComandaTabel.setDefaultEditor(Object.class, null);
        tabelComandaTabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String[] coloaneComanda = {"Cantitate", "Denumire", "Pret"};
        modelTabelComanda = new DefaultTableModel(coloaneComanda, 0);
        tabelComandaTabel.setModel(modelTabelComanda);

        gbcComanda.fill = GridBagConstraints.BOTH;
        gbcComanda.weightx = 0.1;
        gbcComanda.weighty = 0.1;
        gbcComanda.gridwidth = 3;
        gbcComanda.gridx = 0;
        gbcComanda.gridy = 0;
        scrollPaneComanda = new JScrollPane(tabelComandaTabel);
        tabelComanda.add(scrollPaneComanda, gbcComanda);

        buttonsCatalog.add(breakfast);
        buttonsCatalog.add(pizza);
        buttonsCatalog.add(nonalcoolice);
        buttonsCatalog.add(alcoolice);
        buttonsCatalog.add(desert);
        buttonsComanda.add(adaugaProdus);
        buttonsComanda.add(stergeProdus);
        buttonsHelp.add(mutaMasa);
        buttonsHelp.add(printeazaBon);
        buttonsHelp.add(inapoi);
        pretTotal.add(pretTotalLabel);

        buttonsAndPret.add(buttonsHelp, BorderLayout.SOUTH);
        buttonsAndPret.add(pretTotal, BorderLayout.NORTH);
        leftFrame.add(buttonsCatalog, BorderLayout.NORTH);
        leftFrame.add(tabelProduse, BorderLayout.CENTER);
        rightFrame.add(buttonsComanda, BorderLayout.NORTH);
        rightFrame.add(tabelComanda, BorderLayout.CENTER);
        rightFrame.add(buttonsAndPret, BorderLayout.SOUTH);

        add(leftFrame, BorderLayout.WEST);
        add(rightFrame, BorderLayout.EAST);
    }

    private class GestorEvenimenteCF implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == breakfast) {
                resetTabel(modelTabelProduse);
                populeazaCatalogProduse("Breakfast");
                revalidate();
                repaint();
            } else if (e.getSource() == pizza) {
                resetTabel(modelTabelProduse);
                populeazaCatalogProduse("Pizza");
                revalidate();
                repaint();
            } else if (e.getSource() == nonalcoolice) {
                resetTabel(modelTabelProduse);
                populeazaCatalogProduse("Nonalcoolice");
                revalidate();
                repaint();
            } else if (e.getSource() == alcoolice) {
                resetTabel(modelTabelProduse);
                populeazaCatalogProduse("Alcoolice");
                revalidate();
                repaint();
            } else if (e.getSource() == desert) {
                resetTabel(modelTabelProduse);
                populeazaCatalogProduse("Desert");
                revalidate();
                repaint();
            } else if (e.getSource() == stergeProdus) {
                if (tabelComandaTabel.getSelectedRow() == -1) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    JOptionPane.showMessageDialog(null, "Nici un produs selectat!", "Eroare la stergere produs!", JOptionPane.ERROR_MESSAGE);
                } else {
                    costTotal -= Float.parseFloat(tabelComandaTabel.getValueAt(tabelComandaTabel.getSelectedRow(), 2).toString());
                    pretTotalLabel.setText("Pret Total: " + costTotal + " Lei");
                    for (int i = 0; i < Restaurant.comenzi.lista.size(); i++) {
                        if (Restaurant.comenzi.lista.get(i).masa == FereastraPrincipala.lastClicked) {
                            Restaurant.comenzi.lista.get(i).stergeProdus(tabelComandaTabel.getValueAt(tabelComandaTabel.getSelectedRow(), 1).toString(), costTotal);
                            break;
                        }
                    }
                    if (Integer.parseInt(tabelComandaTabel.getValueAt(tabelComandaTabel.getSelectedRow(), 0).toString()) > 1)
                        tabelComandaTabel.setValueAt(Integer.parseInt(tabelComandaTabel.getValueAt(tabelComandaTabel.getSelectedRow(), 0).toString()) - 1, tabelComandaTabel.getSelectedRow(), 0);
                    else modelTabelComanda.removeRow(tabelComandaTabel.getSelectedRow());
                    if (tabelComandaTabel.getRowCount() == 0) {
                        FereastraPrincipala.mese[FereastraPrincipala.lastClicked - 1].setBackground(Color.GREEN);
                        checkSizeComenzi();
                    }
                    revalidate();
                    repaint();
                }
            } else if (e.getSource() == adaugaProdus) {
                if (tabelComandaProduse.getSelectedRow() == -1) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    JOptionPane.showMessageDialog(null, "Nici un produs selectat!", "Eroare la adaugare produs!", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (tabelComandaTabel.getRowCount() == 0) {
                        costTotal += Float.parseFloat(tabelComandaProduse.getValueAt(tabelComandaProduse.getSelectedRow(), 3).toString());
                        Restaurant.comenzi.lista.add(new Comanda(FereastraPrincipala.lastClicked, costTotal, Integer.parseInt(ChelnerLogin.chelnerid.getText())));
                        FereastraPrincipala.mese[(FereastraPrincipala.lastClicked - 1)].setBackground(Color.RED);
                        Restaurant.comenzi.lista.get(Restaurant.comenzi.lista.size() - 1).adaugaProdus(tabelComandaProduse.getValueAt(tabelComandaProduse.getSelectedRow(), 0).toString(), costTotal);
                        modelTabelComanda.addRow(new Object[]{1, tabelComandaProduse.getValueAt(tabelComandaProduse.getSelectedRow(), 0).toString(), Float.parseFloat(tabelComandaProduse.getValueAt(tabelComandaProduse.getSelectedRow(), 3).toString())});
                        pretTotalLabel.setText("Pret Total: " + costTotal + " Lei");
                    } else {
                        for (int i = 0; i < Restaurant.comenzi.lista.size(); i++) {
                            if (Restaurant.comenzi.lista.get(i).masa == FereastraPrincipala.lastClicked) {
                                costTotal += Float.parseFloat(tabelComandaProduse.getValueAt(tabelComandaProduse.getSelectedRow(), 3).toString());
                                String denumireProdus = tabelComandaProduse.getValueAt(tabelComandaProduse.getSelectedRow(), 0).toString();
                                pretTotalLabel.setText("Pret Total: " + costTotal + " Lei");
                                int found = 0;
                                for (int j = 0; j < tabelComandaTabel.getRowCount(); j++) {
                                    if (String.valueOf(tabelComandaTabel.getValueAt(j, 1).toString()).equals(denumireProdus)) {
                                        Restaurant.comenzi.lista.get(i).adaugaProdus(denumireProdus, costTotal);
                                        tabelComandaTabel.setValueAt(Integer.parseInt(tabelComandaTabel.getValueAt(j, 0).toString()) + 1, j, 0);
                                        revalidate();
                                        repaint();
                                        found = 1;
                                        break;
                                    }
                                }
                                if (found == 0) {
                                    modelTabelComanda.addRow(new Object[]{1, tabelComandaProduse.getValueAt(tabelComandaProduse.getSelectedRow(), 0).toString(), tabelComandaProduse.getValueAt(tabelComandaProduse.getSelectedRow(), 3).toString()});
                                    Restaurant.comenzi.lista.get(i).adaugaProdus(denumireProdus, costTotal);
                                }
                                break;
                            }
                        }
                    }
                }
            } else if (e.getSource() == mutaMasa) {
                new SchimbaMasaFrame();
            } else if (e.getSource() == printeazaBon) {
                if (tabelComandaTabel.getRowCount() == 0) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    JOptionPane.showMessageDialog(null, "Comanda goala!", "Eroare la printare bon!", JOptionPane.ERROR_MESSAGE);
                } else {
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    printerJob.setJobName("printeaza-bon");
                    printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
                        if (pageIndex > 0) return Printable.NO_SUCH_PAGE;
                        Graphics2D graphics2D = (Graphics2D) graphics;
                        graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                        double scaleX = pageFormat.getImageableWidth() / scrollPaneComanda.getWidth();
                        double scaleY = pageFormat.getImageableHeight() / scrollPaneComanda.getHeight();
                        double scale = Math.min(scaleX, scaleY);
                        graphics2D.scale(scale, scale);
                        graphics2D.translate(0, 0);
                        scrollPaneComanda.paint(graphics2D);
                        graphics2D.translate(0, 360);
                        pretTotal.paint(graphics2D);
                        return Printable.PAGE_EXISTS;
                    });
                    boolean returnResult = printerJob.printDialog();
                    if (returnResult) {
                        try {
                            printerJob.print();
                        } catch (PrinterException printerException) {
                            JDialog.setDefaultLookAndFeelDecorated(true);
                            JOptionPane.showMessageDialog(null, "Am intampinat o exceptie la printare. Incercati din nou! " + printerException.getMessage(), "Eroare la printare bon!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    FereastraPrincipala.mese[(FereastraPrincipala.lastClicked - 1)].setBackground(Color.GREEN);
                    checkSizeComenzi();
                }
            } else {
                ChelnerLogin.chelnerid.setText("");
                FereastraPrincipala.colorTables();
                MainFrame.getInstance().cardLayout.show(MainFrame.getInstance().cardPanel, "fereastraPrincipala");
            }
        }

        public static void checkSizeComenzi() {
            if (Restaurant.comenzi.lista.size() - 1 == 0) {
                Restaurant.comenzi.lista.remove(0);
                FileManager.encodeFile();
            } else {
                for (int i = 0; i < Restaurant.comenzi.lista.size(); i++) {
                    if (Restaurant.comenzi.lista.get(i).masa == FereastraPrincipala.lastClicked) {
                        Restaurant.comenzi.lista.remove(i);
                        FileManager.encodeFile();
                        break;
                    }
                }
            }
        }
    }

    public static void populeazaCatalogProduse(String button) {
        for (Produs produs : Restaurant.catalog.lista) {
            if (String.valueOf(produs.categorie).equals(button)) {
                modelTabelProduse.addRow(new Object[]{produs.denumire, produs.cantitate, produs.unitateMasura, produs.pret});
            }
        }
    }

    public static void populeazaTabelComanda(int masa) {
        resetTabel(modelTabelComanda);
        if (Restaurant.comenzi.lista.size() > 0) {
            for (int i = 0; i < Restaurant.comenzi.lista.size(); i++) {
                if (Restaurant.comenzi.lista.get(i).masa == masa) {
                    for (Map.Entry<Produs, Integer> produs : Restaurant.comenzi.lista.get(i).lista.entrySet())
                        modelTabelComanda.addRow(new Object[]{produs.getValue(), produs.getKey().denumire, produs.getKey().pret});
                    costTotal = Restaurant.comenzi.lista.get(i).pret;
                    break;
                } else costTotal = 0;
            }
        } else costTotal = 0;
        pretTotalLabel.setText("Pret Total: " + costTotal + " Lei");
        rightFrame.setBorder(BorderFactory.createTitledBorder("Comanda masa " + FereastraPrincipala.lastClicked));
    }

    public static void resetTabel(DefaultTableModel model) {
        model.setRowCount(0);
    }
}