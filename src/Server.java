import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.Objects;

public class Server {

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try  {
                server = new ServerSocket(4004);
                System.out.println("Server is ready for your fashion, demon!");
                clientSocket = server.accept();
                try {
                    while (true){
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        String word = in.readLine();
                        //|| word=="null"
                        if(Objects.equals(word, "exit") || word==null ){
                            break;
                        }
                        //else{
                        System.out.println(word);
                        out.write("Did you say : " + word + "?\n");
                        out.flush();
                        //}
                    }

                } finally {
                    System.out.println("Server stopped listening 808");
                    out.flush();
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Server is dead!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
