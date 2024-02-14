public class Angajat {
    protected int internalid;
    protected String pozitie, nume, prenume;

    public Angajat(int internalid, String pozitie, String nume, String prenume) {
        this.pozitie = pozitie;
        this.nume = nume;
        this.prenume = prenume;
        this.internalid = internalid;
    }
}