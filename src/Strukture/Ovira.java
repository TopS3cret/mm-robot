package Strukture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Lojze on 31.5.2016.
 */
public class Ovira {
    public ArrayList<Tocka> tocke;
    public Ovira(){
        tocke = new ArrayList<Tocka>();
    }

    public boolean isPositivelyOriented(){
        double sum=0;
        for(int i=0; i<tocke.size(); i++){
            Tocka t1 = tocke.get(i);
            Tocka t2 = tocke.get((i+1)%tocke.size());

            sum+=(t2.x-t1.x) * (t2.y+t1.y);
        }

        return sum<=0;
    }

    public void complete(){
        if (!isPositivelyOriented()){
            Collections.reverse(tocke);
            System.out.println("FLIPPED");
        }
    }
}
