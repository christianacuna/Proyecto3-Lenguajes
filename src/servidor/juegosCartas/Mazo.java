package servidor.juegosCartas;
import servidor.Jugador;

import java.util.Collections;
import java.util.List;

public class Mazo {
    private List<Carta> cartas;

    public void barajar(){
        Collections.shuffle(this.cartas);
    }

    public Carta getCarta() {
        return this.cartas.remove(0);
    }

    public void repartir(int n, List<Jugador> listaJugadores){
        while (n>0){
            for (Jugador j: listaJugadores) {
                j.darCarta(getCarta());
            }
            n--;
        }
    }
}
