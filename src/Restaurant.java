import javax.swing.*;

class Restaurant {
    static AngajatiList angajati = new AngajatiList();
    static CatalogList catalog = new CatalogList();
    static ComenziList comenzi = new ComenziList();

    public static void main(String[] args) {
        try {
            angajati.incarcaAngajati();
            catalog.incarcaProduse();
            comenzi.incarcaComenzi();
            MainFrame.getInstance();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}