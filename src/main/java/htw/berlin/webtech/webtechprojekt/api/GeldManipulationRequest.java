package htw.berlin.webtech.webtechprojekt.api;

import javax.validation.constraints.Size;

public class GeldManipulationRequest {


    private String name;
    private long geldBetrag;
    private boolean einnahme;

    public GeldManipulationRequest() {}
    public GeldManipulationRequest(String name, long geldBetrag, boolean einnahme) {
        this.name = name;
        this.geldBetrag = geldBetrag;
        this.einnahme = einnahme;
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

    public boolean isEinnahme() {
        return einnahme;
    }

    public void setEinnahme(boolean einnahme) {
        this.einnahme = einnahme;
    }
}
