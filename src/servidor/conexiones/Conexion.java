package servidor.conexiones;
import java.net.Socket;

public class Conexion {
    private Socket socket;

    public Conexion(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket() {
        return socket;
    }
}
