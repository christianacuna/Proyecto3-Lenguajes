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
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
                System.out.println(in.nextLine());
            }
        }
    }
}
