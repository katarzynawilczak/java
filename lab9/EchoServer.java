package lab9;


import java.io.*;
import java.net.*;
 
public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
	Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(6611);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }

	while(true){
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
	new EchoThread(clientSocket).start();
 	
 	}
    }
}


