package Dijkstra;

/**
 * Created by egral on 02/06/16.
 */
public class Povezava {
    double dolzina;
    Vozlisce zacetno;
    Vozlisce koncno;

    public Povezava (double d, Vozlisce z, Vozlisce k) {
        this.dolzina = d;
        this.zacetno = z;
        this.koncno = k;
    }
}
