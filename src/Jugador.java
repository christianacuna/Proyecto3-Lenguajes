import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
     private Coneccion coneccion;
     private int identificador;
     private int comision;
     private Object mano;

     Jugador(Socket socket, int identificador){
         this.coneccion = new Coneccion(socket);
         this.identificador = identificador;

     }

     public Coneccion getConeccion() {
        return coneccion;
     }

     public int getIdentificador() {
         return identificador;
     }

     public Object getMano() {
         return mano;
     }

     public List<Object> calcularJugada(){
         List<Object> lista = new ArrayList<>();
         return lista;
     }
}
