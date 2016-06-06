import Strukture.Tocka;
import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

/**
 * Created by egral on 06/06/16.
 */
public class NaravniZlepek {


    static Tocka[] sestaviZlepek(Tocka[] lomljenka, int stOdsekov){
        int n = lomljenka.length;

        SimpleMatrix A = generirajA(n);
        SimpleMatrix b = generirajb(n, lomljenka);

        SimpleMatrix s = A.solve(b);

        Polinom3 enacbeX[] = new Polinom3[n-1];
        Polinom3 enacbeY[] = new Polinom3[n-1];
        for(int i=0; i<n-1; i++){
            enacbeX[i] = new Polinom3(lomljenka[i].x,
                    s.get(i,0),
                    3*(lomljenka[i+1].x-lomljenka[i].x)-2*s.get(i,0)-s.get(i+1,0),
                    2*(lomljenka[i].x-lomljenka[i+1].x)+s.get(i,0)+s.get(i+1,0));

            enacbeY[i] = new Polinom3(lomljenka[i].y,
                    s.get(i,1),
                    3*(lomljenka[i+1].y-lomljenka[i].y)-2*s.get(i,1)-s.get(i+1,1),
                    2*(lomljenka[i].y-lomljenka[i+1].y)+s.get(i,1)+s.get(i+1,1));
        }

        ArrayList<Tocka> res = new ArrayList<>();
        for(int i=0; i<n-1; i++){
            for(double j=0; j<1; j+=1.00/stOdsekov){
                res.add(new Tocka(enacbeX[i].vrednost(j), enacbeY[i].vrednost(j)));
            }
        }

        return res.toArray(new Tocka[0]);
    }


    static SimpleMatrix generirajA(int n){
        SimpleMatrix A = new SimpleMatrix(n,n);
        for(int i=0; i<n; i++){
            if(i==0){
                A.set(i,i,2);
                A.set(i,i+1,1);
            }
            else if(i==n-1){
                A.set(i,i,2);
                A.set(i,i-1,1);
            }
            else{
                A.set(i,i,4);
                A.set(i,i-1,1);
                A.set(i,i+1,1);
            }
        }
        return A;
    }

    static SimpleMatrix generirajb(int n, Tocka[] lomljenka){
        SimpleMatrix b = new SimpleMatrix(n,2);
        for(int i=0; i<n; i++){
            if(i==0){
                Tocka l1 = lomljenka[1];
                Tocka l0 = lomljenka[0];
                b.setRow(i, 0, 3*(l1.x-l0.x), 3*(l1.y-l0.y));
            }
            else if(i==n-1){
                Tocka lcur = lomljenka[i];
                Tocka lprev = lomljenka[i-1];
                b.setRow(i, 0, 3*(lcur.x-lprev.x), 3*(lcur.y-lprev.y));
            }
            else{
                Tocka lnext = lomljenka[i+1];
                Tocka lprev = lomljenka[i-1];
                b.setRow(i, 0, 3*(lnext.x-lprev.x), 3*(lnext.y-lprev.y));
            }
        }
        return b;
    }

    static SimpleMatrix tockaToMat(Tocka t){
        SimpleMatrix r = new SimpleMatrix(2,1);
        r.set(0,t.x);
        r.set(1,t.y);
        return r;
    }

    static class Polinom3{
        double a;
        double b;
        double c;
        double d;
        public Polinom3(double a, double b, double c, double d){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
        }

        public double vrednost(double t){
            return a + b*t + c*t*t + d*t*t*t;
        }
    }

    public static void main(String[] args){
        Tocka[] pov = {
                new Tocka(0,0),
                new Tocka(5,2),
                new Tocka(6,5),
                new Tocka(3,6),
                new Tocka(0,5)
        };

        sestaviZlepek(pov, 10);

    }

}
