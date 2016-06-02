package Dijkstra;

import Strukture.Tocka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Marko
 */
public class Dijkstra {

    public static void main(String[] args) {
        
        Povezava ab = new Povezava(100, null, null);
        Povezava ac = new Povezava(30, null, null);
        Povezava bc = new Povezava(60, null, null);
        Povezava cd = new Povezava(20, null, null);
        Povezava bd = new Povezava(10, null, null);
        Povezava ae = new Povezava(10, null, null);
        Povezava ed = new Povezava(50, null, null);
        Povezava ad = new Povezava(40, null, null);
        
        ArrayList<Povezava> a = new ArrayList<>();
        a.add(ab);
        a.add(ac);
        a.add(ae);
        
        ArrayList<Povezava> b = new ArrayList<>();
        
        ArrayList<Povezava> c = new ArrayList<>();
        c.add(bc);
        c.add(cd);
     
        ArrayList<Povezava> d = new ArrayList<>();
        d.add(ad);
        d.add(bd);
        
        ArrayList<Povezava> e = new ArrayList<>();
        e.add(ed);
        
        
        Vozlisce A = new Vozlisce('A', null, a, null, 0);
        Vozlisce B = new Vozlisce('B', null, b, null, 1000000);
        Vozlisce C = new Vozlisce('C', null, c, null, 1000000);
        Vozlisce D = new Vozlisce('D', null, d, null, 1000000);
        Vozlisce E = new Vozlisce('E', null, e, null, 1000000);
        
        ab.koncno = B;
        ac.koncno = C;
        bc.koncno = B;
        cd.koncno = D;
        bd.koncno = B;
        ae.koncno = E;
        ed.koncno = D;
        ad.koncno = D;
        
        

        
        
    }

    public static Tocka[] izvediDijkstro(ArrayList<Vozlisce> vozlisca, Vozlisce z, Vozlisce k){

        Comparator<Vozlisce> comparator = new CompareDistance();
        PriorityQueue <Vozlisce> vrsta = new PriorityQueue<Vozlisce>(vozlisca.size(), comparator);
        vrsta.add(z);

        while (!vrsta.isEmpty()) {
            Vozlisce trenutno = vrsta.poll();

            for (int i=0; i<trenutno.izhodne_povezave.size(); i++) {
                Povezava p = trenutno.izhodne_povezave.get(i);
                Vozlisce koncno = p.koncno;
                double skupna_pot_do_koncega_vozlisca = trenutno.najkrajsa_pot + p.dolzina;

                if (skupna_pot_do_koncega_vozlisca < koncno.najkrajsa_pot) {    //najdena krajša pot
                    koncno.najkrajsa_pot = skupna_pot_do_koncega_vozlisca;      //popravi pot
                    koncno.oce = trenutno;          //nastavimo od kod prihaja povezava
                    vrsta.add(p.koncno);            //dodamo v prioritetno vrsto
                }
            }

        }


        // poisci najkrajso pot
        Vozlisce Od = z;
        Vozlisce Do = k;
        Vozlisce temp = Do;

        ArrayList<Vozlisce> resitev = new ArrayList<Vozlisce>();
        resitev.add(Do);

        while (!temp.equals(Od)) {
            temp = resitev.get(resitev.size()-1);   //zadnji iz vrste
            //System.out.println(temp.id);
            //System.out.println(temp.najkrajsa_pot + "\n");

            temp = temp.oce;    //njegov predhodnik po najkrajsi poti

            resitev.add(temp);
        }

        Collections.reverse(resitev);

        Tocka[] res = new Tocka[resitev.size()];
        for (int i=0; i<resitev.size(); i++) {
            // izpisi vozlisca
            res[i] = resitev.get(i).t;
        }

        return res;
    }
    
}


class CompareDistance implements Comparator<Vozlisce> {

    @Override
    public int compare(Vozlisce v1, Vozlisce v2) {
        if (v1.najkrajsa_pot < v2.najkrajsa_pot) {
            return -1;
        }
        else {
            return 1;
        }
    }
    
}


