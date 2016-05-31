package Strukture;

/**
 * Created by Lojze on 31.5.2016.
 */
public class Trikotnik {
    Tocka ogljisca[];

    public Trikotnik(){
        ogljisca = new Tocka[3];
    }

    public Trikotnik(Tocka a, Tocka b, Tocka c){
        ogljisca = new Tocka[3];
        ogljisca[0]=a;
        ogljisca[1]=b;
        ogljisca[2]=c;
    }

}
