package Servidor;
import Servidor.JuegoCartas;
import java.util.List;

public class Poker extends JuegoCartas{
    List<JuegoCartas> modos;
    int pot;

    public Poker(){

    }
    public Poker(List<JuegoCartas> modos) {
        this.modos = modos;
    }

    @Override
    public void iniciarJuego() {
        int n = 5;
        while (n>0){
            for (JuegoCartas modo: modos) {
                modo.iniciarJuego();
            }
        }
    }

    public int getPot() {
        return pot;
    }
}
