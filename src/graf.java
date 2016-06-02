import Dijkstra.Vozlisce;
import Strukture.Tocka;
import Strukture.Trikotnik;

import java.util.*;

public class graf {


	public static void main (String[] args){


	}

	public static ArrayList<Vozlisce> izdelajGraf(Tocka zacetek, Tocka konec, Strukture.Trikotnik[] trikotniki){
		//nastavimo zacasni na prvi trikotnik iz lista tringulacije

		double minZacetek=Double.POSITIVE_INFINITY;
		int mintZacetekI = -1;
		double minKonec=Double.POSITIVE_INFINITY;
		int minKonecI = -1;

		ArrayList<Vozlisce> vozlisca = new ArrayList<>();

		//sprehodimo se cez vse trikotnike in izracunamo tezisca
		for(int i=0; i<trikotniki.length; i++){
			Strukture.Trikotnik zacasni = trikotniki[i];
			//trikotniki imajo tocke a,b,c
			//izracunamo
			zacasni.izracunajSredisca();

			//izracunamo tezisce
			zacasni.izracunajTezisce();
			if(Tocka.razdalja(zacasni.t, zacetek)<minZacetek){
				minZacetek = Tocka.razdalja(zacasni.t, zacetek);
				mintZacetekI = i;
			}
			if(Tocka.razdalja(zacasni.t, konec)<minKonec){
				minKonec = Tocka.razdalja(zacasni.t, konec);
				minKonecI = i;
			}
			Vozlisce v = new Vozlisce('-', zacasni.t);
			zacasni.v = v;
			vozlisca.add(v);
		}

		Vozlisce vz = new Vozlisce('0', zacetek);
		vz.najkrajsa_pot = 0;
		vz.poveziZ(trikotniki[mintZacetekI].v);

		Vozlisce vk = new Vozlisce('1', konec);
		trikotniki[minKonecI].v.poveziZ(vk);

		for(int i=0; i<trikotniki.length; i++){
			Trikotnik zacasni = trikotniki[i];

			for(int j=i+1; j<trikotniki.length; j++){
				Trikotnik primerjalni = trikotniki[j];
				if(zacasni==primerjalni){
					continue;
				}
				//ce ne gre za isti trikotnik, za sosdnja trikotnika izracunamo razdaljo
				//med njunima težiščema. Seveda gre vmes povezava čez središče skupne stranice
				//nerodna resitev, 9x ponovimo
				else{
					if(zacasni.ab.equals(primerjalni.ab)){
						Vozlisce sr = new Vozlisce('-', zacasni.ab);
						zacasni.v.poveziZ(sr);
						primerjalni.v.poveziZ(sr);
						sr.poveziZ(zacasni.v);
						sr.poveziZ(primerjalni.v);
						vozlisca.add(sr);
					}
					else if(zacasni.ab.equals(primerjalni.ac)){
						Vozlisce sr = new Vozlisce('-', zacasni.ab);
						zacasni.v.poveziZ(sr);
						primerjalni.v.poveziZ(sr);
						sr.poveziZ(zacasni.v);
						sr.poveziZ(primerjalni.v);
						vozlisca.add(sr);
					}
					else if(zacasni.ab.equals(primerjalni.bc)){
						Vozlisce sr = new Vozlisce('-', zacasni.ab);
						zacasni.v.poveziZ(sr);
						primerjalni.v.poveziZ(sr);
						sr.poveziZ(zacasni.v);
						sr.poveziZ(primerjalni.v);
						vozlisca.add(sr);
					}
					else if(zacasni.ac.equals(primerjalni.ab)){
						Vozlisce sr = new Vozlisce('-', zacasni.ac);
						zacasni.v.poveziZ(sr);
						primerjalni.v.poveziZ(sr);
						sr.poveziZ(zacasni.v);
						sr.poveziZ(primerjalni.v);
						vozlisca.add(sr);
					}
					else if(zacasni.ac.equals(primerjalni.ac)){
						Vozlisce sr = new Vozlisce('-', zacasni.ac);
						zacasni.v.poveziZ(sr);
						primerjalni.v.poveziZ(sr);
						sr.poveziZ(zacasni.v);
						sr.poveziZ(primerjalni.v);
						vozlisca.add(sr);
					}
					else if(zacasni.ac.equals(primerjalni.bc)){
						Vozlisce sr = new Vozlisce('-', zacasni.ac);
						zacasni.v.poveziZ(sr);
						primerjalni.v.poveziZ(sr);
						sr.poveziZ(zacasni.v);
						sr.poveziZ(primerjalni.v);
						vozlisca.add(sr);
					}
					else if(zacasni.bc.equals(primerjalni.ab)){
						Vozlisce sr = new Vozlisce('-', zacasni.bc);
						zacasni.v.poveziZ(sr);
						primerjalni.v.poveziZ(sr);
						sr.poveziZ(zacasni.v);
						sr.poveziZ(primerjalni.v);
						vozlisca.add(sr);
					}
					else if(zacasni.bc.equals(primerjalni.ac)){
						Vozlisce sr = new Vozlisce('-', zacasni.bc);
						zacasni.v.poveziZ(sr);
						primerjalni.v.poveziZ(sr);
						sr.poveziZ(zacasni.v);
						sr.poveziZ(primerjalni.v);
						vozlisca.add(sr);
					}
					else if(zacasni.bc.equals(primerjalni.bc)){
						Vozlisce sr = new Vozlisce('-', zacasni.bc);
						zacasni.v.poveziZ(sr);
						primerjalni.v.poveziZ(sr);
						sr.poveziZ(zacasni.v);
						sr.poveziZ(primerjalni.v);
						vozlisca.add(sr);
					}
				}
			}
		}

		vozlisca.add(0, vz);
		vozlisca.add(vk);
		return vozlisca;
	}



}