package htw.berlin.webtech.webtechprojekt.persistence;

import javax.persistence.*;

@Entity(name = "gelder")
public class GeldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "geld_betrag", nullable = false)     // es sind keine null werte erlaubt
    private long geldBetrag;

    @Column(name = "einnahme", nullable = false)
    private boolean einnahme;


    public GeldEntity(String name, long geldBetrag, boolean einnahme) {

        this.name = name;
        this.geldBetrag = geldBetrag;
        this.einnahme = einnahme;
    }

    protected GeldEntity() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGeldBetrag() {
        return geldBetrag;
    }

    public void setGeldBetrag(long geldBetrag) {
        this.geldBetrag = geldBetrag;
    }

    public boolean getEinnahme() {
        return einnahme;
    }

    public void setEinnahme(boolean einnahme) {
        this.einnahme = einnahme;
    }
}
