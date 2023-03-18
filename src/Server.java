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

    private static int n = 5;


    public static void main(String[] args) {
        try {
            try  {
                server = new ServerSocket(4004);
                System.out.println("Server is ready for your fashion, demon!");
                clientSocket = server.accept();
                try {
                    //while (true){
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                        //XY
                        String mass = in.readLine();
                        System.out.println("xy: "+ mass);
                        //Y
//                        String y_mass = in.readLine();
//                        System.out.println("y: "+ y_mass);

                    String[] words = mass.replace("]["," ").replace("[", "").replace("]", "").replace(",","").split(" ");

                    double[] xy = Arrays.stream(words)
                            .mapToDouble(Integer::parseInt)
                            .toArray();

                    // Initialize area
                    double area = 0.0;

                    // Calculate value of shoelace formula
                    int j = n - 1;
                    for (int i = 0; i < n; i++)
                    {
                        area += (xy[j] + xy[i]) * (xy[j+5] - xy[i+5]);

                        // j is previous vertex to i
                        j = i;
                    }

                    // Return absolute value
                  //  return Math.abs(area / 2.0);
                    double res=(Math.abs(area / 2.0));
                    String ress=""+res;

                    System.out.println("res: "+ ress);
                    out.write(ress);
                    out.flush();

                } finally {
                    System.out.println("Server stopped listening 808 line");
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
