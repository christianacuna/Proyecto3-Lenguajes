package Servidor;
import java.net.Socket;

public class Coneccion {
    private Socket socket;

    Coneccion(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket() {
        return socket;
    }
}
