package lab8;
import java.io.*;
import java.net.*;
 
public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }
 
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                clientSocket.getInputStream()));
        String inputLine;
		DB db = new DB();
     
	    
		while ((inputLine=in.readLine())!= null){
			if(inputLine.equals("1")){
				//inputLine=in.readLine(); jak odczytac kolejną linię?
				db.findByAuthor(inputLine);
			}
			if(inputLine.equals("2")){
				db.findByISBN(inputLine);
			}
			if(inputLine.equals("3")){
				db.deleteByAuthor(inputLine);
			}
			if(inputLine.equals("4")){
				db.deleteByISBN(inputLine);
			}
			
			out.println(inputLine);

        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
 
    }
}


