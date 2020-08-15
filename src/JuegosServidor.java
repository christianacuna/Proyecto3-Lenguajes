import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JuegosServidor extends Thread
{
    private ServerSocket socket;
    private boolean running = false;

    public static void main(String[] args) {

        int port = 8080;
        System.out.println("Starting the Server on port: "+port);
        JuegosServidor server = new JuegosServidor();
        server.startServer(port);
    }
    public void stopServer()
    {
        System.out.println("Server Stopping.");
        this.running = false;
        this.interrupt();
    }
    public void startServer(int port)
    {
        try{

            this.socket = new ServerSocket(port);
            this.start();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run()
    {
        this.running = true;
        while( this.running )
        {
            try
            {
                System.out.println("Acepting Requests");
                Socket NewSocket = this.socket.accept();
                Jugador jugador = new Jugador(NewSocket);
                httpProcessThread.start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                stopServer();
            }
        }
    }
}