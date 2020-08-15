import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class JuegosServidor extends Thread
{
    private ServerSocket socket;
    private boolean running = false;
    private int acumulador;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {

        int port = 8080;
        System.out.println("Iniciando servidor en puerto: "+port);
        JuegosServidor server = new JuegosServidor();
        int numeroJuego = menu();
        server.startServer(port);
    }
    static private int menu(){
        System.out.println(ANSI_CYAN+"Seleccione el juego de cartas [1-4]"+ANSI_RESET);
        System.out.println("1 - Hold'em");
        System.out.println("2 - FiveCards");
        System.out.println("3 - Omaha");
        System.out.println("4 - SevenCards");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        if (n > 0 && n < 5){
            System.out.println(ANSI_GREEN+"Juego reconosido"+ANSI_RESET);
            return n;
        }else{
            System.out.println(ANSI_RED+"Juego no reconsido" +ANSI_RESET);
            return menu();
        }
    }
    public void stopServer()
    {
        System.out.println("Deteniendo");
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
        acumulador = 0;
        while( this.running )
        {
            try
            {
                System.out.println("Esperando Jugadores...");
                Socket NewSocket = this.socket.accept();
                acumulador += 1;
                System.out.println("Jugador "+ANSI_BLUE + acumulador+ ANSI_RESET+" encontrado");
                Jugador jugador = new Jugador(NewSocket,acumulador);
                JuegoCartas juegoCartas = new JuegoCartas();
                System.out.println(jugador.getIdentificador());
                System.out.println(jugador.getConeccion().getSocket().getRemoteSocketAddress().toString());
                juegoCartas.start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                stopServer();
            }
        }
    }
}