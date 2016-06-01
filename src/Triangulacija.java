/**
 * Created by Lojze on 31.5.2016.
 */
import Delaunay.ConstrainedMesh;
import Delaunay.geometries.DEdge;
import Delaunay.geometries.DPoint;
import Delaunay.geometries.DTriangle;
import Strukture.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

// Glej metodo main za primer

public class Triangulacija {

    Tocka robovi[];
    Ovira ovire[];

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

        ConstrainedMesh constrainedMesh = new ConstrainedMesh();

        try {
            // dodamo robove
            for (int i = 0; i < robovi.length; i++) {
                Tocka temp = robovi[i];
                DPoint p1 = new DPoint(temp.x, temp.y, 0);
                temp = robovi[(i + 1) % robovi.length];
                DPoint p2 = new DPoint(temp.x, temp.y, 0);
                DEdge e = new DEdge(p1, p2);
                constrainedMesh.addConstraintEdge(e);

                //Pnt p = new Pnt(robovi[i].x, robovi[i].y);
                //tr.delaunayPlace(p);
            }

            // dodamo ovire
            for (int i = 0; i < ovire.length; i++) {
                Ovira o = ovire[i];
                for (int j = 0; j < o.tocke.size(); j++) {
                    Tocka temp = o.tocke.get(j);
                    DPoint p1 = new DPoint(temp.x, temp.y, 0);
                    temp = o.tocke.get((j + 1) % o.tocke.size());
                    DPoint p2 = new DPoint(temp.x, temp.y, 0);
                    DEdge e = new DEdge(p1, p2);
                    constrainedMesh.addConstraintEdge(e);
                }
            }
            constrainedMesh.forceConstraintIntegrity();
            constrainedMesh.processDelaunay();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // Odstranimo robne trikotnike in trikotnike znotraj ovir
        ArrayList<Trikotnik> res = new ArrayList<>();
        Iterator<DTriangle> it = constrainedMesh.getTriangleList().iterator();
        while(it.hasNext()) {
            DTriangle temp = it.next();
            ArrayList<Tocka> t_list = new ArrayList<Tocka>();
            t_list.add(new Tocka(temp.getPoint(0).getX(), temp.getPoint(0).getY()));
            t_list.add(new Tocka(temp.getPoint(1).getX(), temp.getPoint(1).getY()));
            t_list.add(new Tocka(temp.getPoint(2).getX(), temp.getPoint(2).getY()));

            // Preverimo, da ne vsebuje robov
            // ArrayList<Pnt> roboviPnt = pretvoriVPnt(robovi);
            boolean vOviri = false;
            for (int i = 0; i < ovire.length; i++) {

                if (ovire[i].tocke.containsAll(t_list)) {
                    Collections.sort(t_list, new PointOrderComparator(ovire[i].tocke));
                    Trikotnik trikotnik = new Trikotnik(t_list.get(0), t_list.get(1), t_list.get(2));
                    if (trikotnik.isPositivelyOriented())
                        vOviri = true;
                    else {
                        System.out.println("KONKAVA");
                    }
                }
            }
            if (!vOviri) {
                Trikotnik trikotnik = new Trikotnik(t_list.get(0), t_list.get(1), t_list.get(2));
                trikotnik.fixOrientation();
                res.add(trikotnik);
            }
        }

        return res.toArray(new Trikotnik[0]);
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
        o1.complete();

        // pravokotnik
        Ovira o2 = new Ovira();
        o2.tocke.add(new Tocka(50,50));
        o2.tocke.add(new Tocka(55,50));
        o2.tocke.add(new Tocka(53,52));
        o2.tocke.add(new Tocka(53,54));
        o2.tocke.add(new Tocka(55,55));
        o2.tocke.add(new Tocka(50,55));
        o2.complete();

        Ovira[] ovire = {o2};

        // robove in ovire pošljemo v triangulacijo
        Triangulacija t = new Triangulacija(robovi, ovire);

        // izvedemo triangulacijo (dobimo array Trikotnikov)
        Trikotnik[] res = t.izracunaj();

        // Izpis trikotnikov
        for(int i=0; i<res.length; i++){
            System.out.println(res[i].toString());
        }
    }

    static class PointOrderComparator implements Comparator<Tocka>{
        ArrayList<Tocka> ovira;
        public PointOrderComparator(ArrayList<Tocka> ovira){
            this.ovira = ovira;
        }
        @Override
        public int compare(Tocka o1, Tocka o2) {
            return Integer.compare(ovira.indexOf(o1), ovira.indexOf(o2));
        }
    }


}
