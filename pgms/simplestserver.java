import java.net.*; 
import java.io.*; 
  
public class  simplestserver 
{ 
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
  
    // constructor with port 
    public simplestserver(int port) throws UnknownHostException,IOException
    { 
        // starts server and waits for a connection 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
  
            String line = ""; 
  
            // reads message from client until "Over" is sent 
            while(true) 
            { 
                    line = in.readUTF(); 
                    System.out.println(line); 
          } 
       //     System.out.println("Closing connection"); 
  
            // close connection 
       
    } 
  
    public static void main(String args[]) throws UnknownHostException,IOException
    { 
        simplestserver server = new simplestserver(5000); 
    } 
} 