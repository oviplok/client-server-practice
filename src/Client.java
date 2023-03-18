import java.io.*;
import java.util.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    //private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static int n = 5;;

    private static int X[] = {-1, -2, 1,4,1};
    private static int Y[] = {-1, -2, 4,0,-1};


    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 4004);
                //reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
               // System.out.println("Print what you want to say to server:");
                System.out.println("Your X: " + Arrays.toString(X));
                out.write(Arrays.toString(X));
                out.flush();


                System.out.println("Your Y: " + Arrays.toString(Y));
                out.write(Arrays.toString(Y));
                out.flush();

                //Вывод с сервера
                //                String serverWord = in.readLine();
//                System.out.println(serverWord);

            } finally {


                System.out.println("Client has been closed...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}