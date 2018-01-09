package mediaplayerserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class MyServerSocket implements SocketListener{
    
    public int port;
    private Socket socketConnection = null;
    private ServerSocket serverSocket = null;
    private BufferedWriter output = null;
    private BufferedReader input = null;
    private Thread socketReaderThread;
    private Thread connectionThread;
    private boolean ready = false;
    private SocketListener fxListener;
    
    public void connect() {
        
         try {
            connectionThread = new ConnectionThread();
            connectionThread.start();
           
            socketReaderThread = new SocketReaderThread();
            socketReaderThread.start();
            
        } catch (Exception e){
             System.out.println("Blad tworzenia watku polaczenia i watku nasluchu "+ e.getMessage());
        }  
 
    }
    
    public void close() {
        try {
            if (socketConnection != null && !socketConnection.isClosed()) {
                socketConnection.close();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
           
        } catch (IOException e) {
              System.out.println("Blad zamykania socketow " + e.getMessage());
        }
    }
    
    public void sendMessage(String msg) {
        try {
            
            output.write(msg, 0, msg.length());
            output.newLine();
            output.flush();
           
        } catch (IOException e) {
           System.out.println("Blad wysylania wiadomosci" + e.getMessage());
        }
    }  
    
    @Override
    public void onMessage(String line) {
       if(fxListener != null)
           fxListener.onMessage(line); 
    }

    public MyServerSocket(SocketListener fxListener,
            int port) {
        this.port = port;
        this.fxListener = fxListener;
    }
    
    class ConnectionThread extends Thread {
        
        @Override
        public void run() {
             
            try{
                serverSocket = new ServerSocket(port);
                socketConnection = serverSocket.accept();
                if (socketConnection != null && !socketConnection.isClosed()) {
                    input = new BufferedReader(new InputStreamReader(
                            socketConnection.getInputStream()));
                    output = new BufferedWriter(new OutputStreamWriter(
                            socketConnection.getOutputStream()));
                    output.flush();
                }
                notifyReady();
            } catch (Exception e) {
                   System.out.println("Blad watku polaczenia" + e.getMessage());  
            }
        }
    }
    
    private synchronized void notifyReady() {
        ready = true;
        notifyAll();
    }
    
    private synchronized void waitForReady() {
        while (!ready) {
            try {
                System.out.println("Oczekiwanie...");
                wait();
                
            } catch (Exception e) {
                System.out.println("Blad oczekiwania watku" + e.getMessage());
            }
        }
    }
     
    class SocketReaderThread extends Thread {

        @Override
        public void run() {
            waitForReady();
           
            try {
                if (input != null) {
                    String line;
                    while ((line = input.readLine()) != null) {
                        onMessage(line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Blad watku nasluchiwania" + e.getMessage());
            } finally {
                close();
            }
        }
    }
}
