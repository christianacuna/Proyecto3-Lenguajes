package servidor.jugadores;
import servidor.juegosCartas.Carta;
import servidor.conexiones.Conexion;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Jugador extends Thread {
     private Conexion conexion;
     private int identificador;
     private int comision;
     private List<Carta> mano;

     public Jugador(Socket socket, int identificador){
         this.conexion = new Conexion(socket);
         this.identificador = identificador;
         this.comision = 1000;
         this.mano = new ArrayList<Carta>();
     }

     public Conexion getConeccion() {
        return conexion;
     }

     public int getIdentificador() {
         return identificador;
     }

     public Object getMano() {
         return mano;
     }

     public void darCarta(Carta c){
         this.mano.add(c);
     }

     public List<Object> calcularJugada(){
         List<Object> lista = new ArrayList<>();
         return lista;
     }
}
