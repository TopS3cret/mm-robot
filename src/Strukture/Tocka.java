package Strukture;

/**
 * Created by Lojze on 31.5.2016.
 */
public class Tocka{
    public double x;
    public double y;

    public Tocka(){
        x=0;
        y=0;
    }

    public Tocka(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static double razdalja(Tocka t1, Tocka t2){
        return Math.sqrt((t1.x-t2.x)*(t1.x-t2.x)+(t1.y-t2.y)*(t1.y-t2.y));
    }

    @Override
    public String toString() {
        return String.format("(%.0f,%.0f)", x,y);
    }

    @Override
    public boolean equals(Object obj) {
        Tocka t = (Tocka)obj;
        return (this.x == t.x && this.y == t.y);
    }
}
