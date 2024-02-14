import java.util.*;

public class ComenziList {
    protected ArrayList<Comanda> lista = new ArrayList<>();

    public void incarcaComenzi() {
        lista = FileManager.decodeFile();
        if (lista == null) lista = new ArrayList<>();
    }
}