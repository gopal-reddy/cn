import java.net.*; 
import java.io.*; 
  
public class simplestclient 
{ 
    // initialize socket and input output streams 
    private Socket socket = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out    = null; 
  
    // constructor to put ip address and port 
    public simplestclient(String address, int port) throws UnknownHostException,IOException
    { 
        // establish a connection 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        
             // string to read message from input 
        String line = ""; 
  
        // keep reading until "Over" is input 
        while (true) 
        { 
                line = input.readLine(); 
                out.writeUTF(line); 
            } 
        // close the connection 
    } 
  
    public static void main(String args[])  throws UnknownHostException,IOException
    { 
        simplestclient client = new simplestclient("127.0.0.1", 5000); 
    } 
} 
