package Strukture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lojze on 31.5.2016.
 */
public class Trikotnik {
    public Tocka ogljisca[];

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

    @Override
    public String toString() {
        return String.format("[ %s, %s, %s ]", ogljisca[0].toString(), ogljisca[1].toString(), ogljisca[2].toString());
    }
}
