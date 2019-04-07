import java.net.*; 
import java.io.*; 
public class hammingserver
{
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
     public hammingserver(int port)  throws UnknownHostException,IOException
    { 
       server = new ServerSocket(port); 
       System.out.println("Server started"); 
       System.out.println("Waiting for a client ..."); 
       socket = server.accept(); 
       System.out.println("Client accepted"); 
       in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
       int dataword[]=new int[12];
       for(int i=0;i<11;i++)
       {
            dataword[i]=in.readInt();
            System.out.println(dataword[i]);
      }
     int c1,c2,c4,c8;//c1 c2 c4 c8 are check bits
    System.out.println("Verifying the received Data :-)");
    c1=dataword[0]^dataword[2]^dataword[4]^dataword[6]^dataword[8]^dataword[10];
    c2=dataword[1]^dataword[2]^dataword[5]^dataword[6]^dataword[9]^dataword[10];
    c4=dataword[3]^dataword[4]^dataword[5]^dataword[6]^dataword[11];
    c8=dataword[7]^dataword[8]^dataword[9]^dataword[10]^dataword[11];
    if(c1==0 && c2==0 && c4==0 && c8==0)
    {
        System.out.println("-------------data is  not changed---------------");
    }
    else
    {
        System.out.println("-----------data is changed------------");
    }
   System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
            in.close(); 
    } 
  
    public static void main(String args[])  throws UnknownHostException,IOException
    { 
        hammingserver server = new hammingserver(5000); 
    } 
} 

  
       