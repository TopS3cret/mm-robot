package Strukture;

import Dijkstra.Vozlisce;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lojze on 31.5.2016.
 */
public class Trikotnik {
    public Tocka ogljisca[];
    public Tocka t;
    public Tocka ab;
    public Tocka ac;
    public Tocka bc;
    public Vozlisce v;

    public Trikotnik(){
        ogljisca = new Tocka[3];
    }

    public Trikotnik(Tocka a, Tocka b, Tocka c){
        ogljisca = new Tocka[3];
        ogljisca[0]=a;
        ogljisca[1]=b;
        ogljisca[2]=c;
    }


    public boolean isPositivelyOriented(){
        double sum=0;
        for(int i=0; i<ogljisca.length; i++){
            Tocka t1 = ogljisca[i];
            Tocka t2 = ogljisca[(i+1)%ogljisca.length];

            sum+=(t2.x-t1.x) * (t2.y+t1.y);
        }

        return sum<=0;
    }

    // if orientation is negative, the triangle is inversed
    public void fixOrientation(){
        if(!isPositivelyOriented()){
            inverse();
        }
    }

    // inverses orientation
    public void inverse(){
        List<Tocka> arr = Arrays.asList(ogljisca);
        Collections.reverse(arr);
        ogljisca = arr.toArray(ogljisca);
    }

    public void izracunajTezisce(){
        Tocka a = ogljisca[0];
        Tocka b = ogljisca[1];
        Tocka c = ogljisca[2];

        double x=(a.x+b.x+c.x)/3;

        double y=(a.y+b.y+c.y)/3;

        t = new Tocka(x,y);
    }

    public void izracunajSredisca(){
        ab = vrniSredisceDaljice(ogljisca[0], ogljisca[1]);
        ac = vrniSredisceDaljice(ogljisca[0], ogljisca[2]);
        bc = vrniSredisceDaljice(ogljisca[1], ogljisca[2]);
    }

    public static Tocka vrniSredisceDaljice(Tocka a, Tocka b){
        double x=(a.x-b.x)/2;
        x=a.x-x;

        double y=(a.y-b.y)/2;
        y=a.y-y;

        Tocka ab = new Tocka(x,y);
        return ab;
    }

    @Override
    public String toString() {
        return String.format("[ %s, %s, %s ]", ogljisca[0].toString(), ogljisca[1].toString(), ogljisca[2].toString());
    }
}
