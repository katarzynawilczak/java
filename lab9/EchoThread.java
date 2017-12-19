package lab9;
import java.io.*;
import java.net.*;

public class EchoThread extends Thread{
	protected Socket clientSocket;
	
	public EchoThread(Socket socket){
		this.clientSocket = socket;
	} 

public void run(){
	PrintWriter out = null;
	BufferedReader in = null;
	try{
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	

	}catch(IOException e){
		return;
	}
	String inputLine;
	while(true){
	try{
		if ((inputLine = in.readLine()) != null) {
		     //System.out.println("echo: " + in.readLine());
		     out.println(inputLine);
		}
		else{
			clientSocket.close();
		}
	
		out.close();
		in.close();
		
	}catch(IOException e){
			return;
	}
	}
}
	
}

