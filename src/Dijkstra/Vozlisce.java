package Dijkstra;

import Strukture.Tocka;

import java.util.ArrayList;

/**
 * Created by egral on 02/06/16.
 */
public class Vozlisce {
    char id;
    Tocka t;
    ArrayList<Povezava> izhodne_povezave;
    Vozlisce oce;
    public double najkrajsa_pot;

    public Vozlisce (char id, Tocka t){
        this.id = id;
        this.t = t;
        izhodne_povezave = new ArrayList<Povezava>();
        this.oce = null;
        najkrajsa_pot = Double.POSITIVE_INFINITY;
    }

    public Vozlisce (char id, Tocka t, ArrayList<Povezava> iz, Vozlisce oce, double min_pot) {
        this.id = id;
        this.t = t;
        this.izhodne_povezave = iz;
        this.oce = oce;
        this.najkrajsa_pot = min_pot;
    }

    public void poveziZ(Vozlisce v2){
        Povezava p = new Povezava(Tocka.razdalja(this.t, v2.t), this, v2);
        izhodne_povezave.add(p);
    }
}
