import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Cliente {
    public static void main(String[] args) throws Exception {
        System.out.println("Indique cual IP quiere conectarse y a cual puerto");
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        String[] address = ip.split(":");
        try (Socket socket = new Socket(address[0],Integer.parseInt(address[1]))) {
            System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(scanner.hasNextLine());
            System.out.println(socket.isConnected());
            System.out.println("--");
            while (scanner.hasNextLine() && socket.getInputStream().read() != -1) {
                //System.out.println(socket.isConnected());
                //System.out.println(socket.isClosed());
                //System.out.println(socket.isBound());
                //System.out.println(socket.isInputShutdown());
                //System.out.println(socket.isOutputShutdown());
                //System.out.println(in.hasNext());
                out.println(scanner.nextLine());
                System.out.println(in.nextLine());
            }
            System.out.println("Conexion terminada");
        }
    }
}
