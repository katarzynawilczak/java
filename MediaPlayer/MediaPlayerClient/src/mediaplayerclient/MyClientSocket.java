package mediaplayerclient;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class MyClientSocket implements SocketListener{
    public int port;
    protected Socket socketConnection = null;
    private BufferedWriter output = null;
    private BufferedReader input = null;
    private boolean ready = false;
    private Thread socketReaderThread;
    private Thread connectionThread;
    private SocketListener fxListener;
 
    @Override
    public void onMessage(String line) {
     javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fxListener.onMessage(line);
            }
        });
    }
 
    public boolean isReady(){
        return ready;
    }
    
    public void connect() {
         try {
            connectionThread = new ConnectionThread();
            connectionThread.start();
           
            socketReaderThread = new SocketReaderThread();
            socketReaderThread.start();
        } catch (Exception e) {
             System.out.println("Blad tworzenia watku polacznia i watku nasluchiwania" + e.getMessage());
        }  
 
    }
     
    public void close() {
        try {
            if (socketConnection != null && !socketConnection.isClosed()) {
                socketConnection.close();
            }
           
        } catch (IOException e) {
            System.out.println("Blad zamkniecia polaczenia" + e.getMessage());
        }
    }
    
    public void sendMessage(String msg) {
        try {
            output.write(msg, 0, msg.length());
            output.newLine();
            output.flush();
           
        } catch (IOException e) {
            System.out.println("Blad wyslania wiadomosci" + e.getMessage());
        }
    }
    
    public MyClientSocket(SocketListener fxListener,
            int port) {
        this.port = port;
        this.fxListener = fxListener;
    }
    
    class ConnectionThread extends Thread {

        @Override
        public void run() {
            try {
                socketConnection = new Socket("localhost", port);  
                if (socketConnection != null && !socketConnection.isClosed()) {
                     input = new BufferedReader(new InputStreamReader(
                            socketConnection.getInputStream()));
                    output = new BufferedWriter(new OutputStreamWriter(
                            socketConnection.getOutputStream()));
                    output.flush();
                }
                notifyReady();
            } catch (IOException e) {
                System.out.println("Blad polaczenia z serwerem " + e.getMessage());
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
                System.out.println("Oczekiwanie... ");
                wait();
                
            } catch (Exception e) {
                 System.out.println("Blad oczekiwania watku " + e.getMessage());
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
                 System.out.println("Blad watku nasluchiwania " + e.getMessage());
            } finally {
                close();
            }
        }
    }
}

