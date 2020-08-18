import java.util.Collections;
import java.util.List;

public class Analizador {
    static final int CANT_CARTAS = 5;

    public boolean esEscaleraReal(List<Carta> cartas){
        return esEscalera(cartas) && esColor(cartas) && cartas.get(4).getNumero() == 14;
    }

    public boolean esEscalera(List<Carta> cartas){
        Collections.sort(cartas);
        int n1 = cartas.get(0).getNumero();
        boolean b= true;
        for(int i = 1; i<CANT_CARTAS && b; i++)
            b = (n1+i == cartas.get(i).getNumero()) || (n1+i+9 == cartas.get(i).getNumero());
        return b;
    }

    public boolean esEscaleraColor(List<Carta> cartas){
        Collections.sort(cartas);
        return esEscalera(cartas) && esColor(cartas);
    }

    public boolean esPoker(List<Carta> cartas){
        Collections.sort(cartas);
        boolean b1, b2;

        b1 = cartas.get(0).getNumero() == cartas.get(1).getNumero() &&
                cartas.get(1).getNumero() == cartas.get(2).getNumero() &&
                cartas.get(2).getNumero() == cartas.get(3).getNumero();

        b2 = cartas.get(1).getNumero() == cartas.get(2).getNumero() &&
                cartas.get(2).getNumero() == cartas.get(3).getNumero() &&
                cartas.get(3).getNumero() == cartas.get(4).getNumero();

        return b1 || b2;
    }

    public boolean esFullHouse(List<Carta> cartas){
        Collections.sort(cartas);
        boolean b1, b2;

        b1 = cartas.get(0).getNumero() == cartas.get(1).getNumero() &&
                cartas.get(1).getNumero() == cartas.get(2).getNumero() &&
                cartas.get(3).getNumero() == cartas.get(4).getNumero();

        b2 = cartas.get(0).getNumero() == cartas.get(1).getNumero() &&
                cartas.get(2).getNumero() == cartas.get(3).getNumero() &&
                cartas.get(3).getNumero() == cartas.get(4).getNumero();

        return b1 || b2;
    }

    public boolean esColor(List<Carta> cartas){
        boolean b = true;
        for(int i = 1; i<CANT_CARTAS && b; i++)
            b = cartas.get(0).getPalo() == cartas.get(i).getPalo();
        return b;
    }

    public boolean esTrio(List<Carta> cartas){
        Collections.sort(cartas);
        boolean b1, b2, b3;
        if (esPoker(cartas) || esFullHouse(cartas))
            return false;

        b1 = cartas.get(0).getNumero() == cartas.get(1).getNumero() &&
                cartas.get(1).getNumero() == cartas.get(2).getNumero();

        b2 = cartas.get(1).getNumero() == cartas.get(2).getNumero() &&
                cartas.get(2).getNumero() == cartas.get(3).getNumero();

        b3 = cartas.get(2).getNumero() == cartas.get(3).getNumero() &&
                cartas.get(3).getNumero() == cartas.get(4).getNumero();

        return b1 || b2 || b3;
    }

    public boolean esDoblePareja(List<Carta> cartas){
        Collections.sort(cartas);
        boolean b1, b2, b3;
        if (esPoker(cartas) || esFullHouse(cartas) || esTrio(cartas))
            return false;

        b1 = cartas.get(0).getNumero() == cartas.get(1).getNumero() &&
                cartas.get(2).getNumero() == cartas.get(3).getNumero();

        b2 = cartas.get(0).getNumero() == cartas.get(1).getNumero() &&
                cartas.get(3).getNumero() == cartas.get(4).getNumero();

        b3 = cartas.get(1).getNumero() == cartas.get(2).getNumero() &&
                cartas.get(3).getNumero() == cartas.get(4).getNumero();

        return b1 || b2 || b3;
    }

    public boolean esPareja(List<Carta> cartas){
        Collections.sort(cartas);
        boolean b1, b2, b3, b4;

        if (esPoker(cartas) || esFullHouse(cartas) || esTrio(cartas) || esDoblePareja(cartas))
            return false;

        b1 = cartas.get(0).getNumero() == cartas.get(1).getNumero();
        b2 = cartas.get(1).getNumero() == cartas.get(2).getNumero();
        b3 = cartas.get(2).getNumero() == cartas.get(3).getNumero();
        b4 = cartas.get(3).getNumero() == cartas.get(4).getNumero();

        return b1 || b2 || b3 || b4;
    }
}
