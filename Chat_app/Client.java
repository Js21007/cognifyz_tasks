import java.io.*;
import java.net.*;

public class Client {
    static Socket clientSocket = null;
    static Client obj;
    static Receive2 receiver;
    static Send2 sender;
    
    class Send2 extends Thread {
        synchronized public void run() {
            try {
                OutputStream os = clientSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
        
                String str = "";
                System.out.println("Connection Established. Write \"quit\" to exit");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while (!(str = br.readLine()).equalsIgnoreCase("quit")) {
                    dos.writeBytes("Client: " + str + "\n");
                    dos.flush();
                }
                dos.close();
            } catch (IOException e) {
                System.exit(0);
            }
        }
    }
    
    class Receive2 extends Thread {
        synchronized public void run() {
            try {
                BufferedReader br = null;
                try {
                    InputStream is = clientSocket.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    System.out.println("Unable to connect!");
                }
                String str = "";
                while ((str = br.readLine()) != null) {
                    System.out.println("Server: " + str);
                }
                br.close();
            } catch (IOException e) {
                System.exit(0);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        clientSocket = new Socket(InetAddress.getLocalHost(), 99);
        obj = new Client();
        
        receiver = obj.new Receive2();
        sender = obj.new Send2();
        receiver.start();
        sender.start();
    }
}
