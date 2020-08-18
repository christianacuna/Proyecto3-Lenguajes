package servidor.conecciones;
import java.net.Socket;

public class Coneccion {
    private Socket socket;

    public Coneccion(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket() {
        return socket;
    }
}
