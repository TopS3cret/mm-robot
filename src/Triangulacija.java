/**
 * Created by Lojze on 31.5.2016.
 */
import Strukture.*;
import Delaunay.*;

import java.util.ArrayList;
import java.util.Iterator;

// Glej metodo main za primer

public class Triangulacija {

    Tocka robovi[];
    Ovira ovire[];
    private static int initialSize = 10000;

    public Triangulacija(){
        robovi = new Tocka[4];
    }

    public Triangulacija(Tocka robovi[], Ovira ovire[]){
        this.robovi = robovi;
        this.ovire = ovire;
    }

    public void nastaviRobove(Tocka robovi[]){
        this.robovi = robovi;
    }

    public void nastaviOvire(Ovira ovire[]){
        this.ovire = ovire;
    }

    public Trikotnik[] izracunaj(){
        Triangle zacetniTrikotnik = new Triangle(
                new Pnt(-initialSize, -initialSize),
                new Pnt( initialSize, -initialSize),
                new Pnt(           0,  initialSize));
        Triangulation tr = new Triangulation(zacetniTrikotnik);

        // dodamo robove
        for(int i=0; i<robovi.length; i++){
            Pnt p = new Pnt(robovi[i].x, robovi[i].y);
            tr.delaunayPlace(p);
        }

        // dodamo ovire
        for(int i=0; i<ovire.length; i++){
            Iterator<Tocka> it = ovire[i].tocke.iterator();
            while(it.hasNext()){
                Tocka t = it.next();
                Pnt p = new Pnt(t.x, t.y);
                tr.delaunayPlace(p);
            }
        }

        // Odstranimo robne trikotnike in trikotnike znotraj ovir
        ArrayList<Trikotnik> res = new ArrayList<>();
        Iterator<Triangle> it = tr.iterator();
        while(it.hasNext()){
            Triangle t = it.next();

            // Preverimo, da ne vsebuje robov
            // ArrayList<Pnt> roboviPnt = pretvoriVPnt(robovi);
            boolean vOviri=false;
            if(!t.containsAny(zacetniTrikotnik)){
                // za vsako oviro preverimo, ce so vse tocke trikotnika na oviri
                for(int i=0; i<ovire.length; i++){
                    ArrayList<Pnt> oviraPnt = pretvoriVPnt(ovire[i].tocke.toArray(new Tocka[0]));
                    if(oviraPnt.containsAll(t)){
                        vOviri=true;
                    }
                }
                if(!vOviri){
                    Tocka a = new Tocka(t.get(0).coord(0), t.get(0).coord(1));
                    Tocka b = new Tocka(t.get(1).coord(0), t.get(1).coord(1));
                    Tocka c = new Tocka(t.get(2).coord(0), t.get(2).coord(1));
                    Trikotnik trikotnik=new Trikotnik(a,b,c);
                    res.add(trikotnik);
                }
            }
        }

        return res.toArray(new Trikotnik[0]);
    }

    private ArrayList<Pnt> pretvoriVPnt(Tocka[] tocke){
        ArrayList<Pnt> roboviPnt = new ArrayList<Pnt>();
        for(int i=0; i<tocke.length; i++){
            roboviPnt.add(new Pnt(tocke[i].x, tocke[i].y));
        }
        return roboviPnt;
    }

    public static void main(String args[]){
        // definiramo robove našega polja (ekrana)
        Tocka[] robovi={
                new Tocka(0,0),
                new Tocka(100,0),
                new Tocka(100,100),
                new Tocka(0,100)
        };

        // definiramo ovire
        // trikotnik
        Ovira o1 = new Ovira();
        o1.tocke.add(new Tocka(5,5));
        o1.tocke.add(new Tocka(7,5));
        o1.tocke.add(new Tocka(6,8));

        // pravokotnik
        Ovira o2 = new Ovira();
        o2.tocke.add(new Tocka(50,50));
        o2.tocke.add(new Tocka(55,50));
        o2.tocke.add(new Tocka(55,55));
        o2.tocke.add(new Tocka(50,55));

        Ovira[] ovire = {o1};

        // robove in ovire pošljemo v triangulacijo
        Triangulacija t = new Triangulacija(robovi, ovire);

        // izvedemo triangulacijo (dobimo array Trikotnikov)
        Trikotnik[] res = t.izracunaj();

        // Izpis trikotnikov
        for(int i=0; i<res.length; i++){
            System.out.println(res[i].toString());
        }
    }



}
