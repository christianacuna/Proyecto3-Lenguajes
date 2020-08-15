import java.util.Collections;
import java.util.List;

public class Mazo {
    private List<Carta> cartas;

    public void barajar(){
        Collections.shuffle(this.cartas);
    }

    public Carta getCarta() {
        return this.cartas.remove(this.cartas.size() -1);
    }

    public void repartir(int n, List<Jugador> listaJugadores){

    }
}
