import java.net.*; 
import java.io.*; 
public class bitstuffingserver
{
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
     public bitstuffingserver(int port)  throws UnknownHostException,IOException
    { 
       server = new ServerSocket(port); 
       System.out.println("Server started"); 
       System.out.println("Waiting for a client ..."); 
       socket = server.accept(); 
       System.out.println("Client accepted"); 
       in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
         char data[ ]=new char[100];
         char k=in.readChar();
          int i=0;
          System.out.println("After encoding");
          while(k!='c')
          {
                 data[i++]=k;
                  System.out.print(k);
                  k=in.readChar();
          }
       System.out.println();
       System.out.println("after decoding");
           for(int v=0;v<i;v++)
           {
                  int c=0;
                  for(int j=0;j<=4;j++)
                      {
                                if(data[v]!='1')
                                { 
                                         System.out.print("0");
                                        break;
                                 }
                                 else
                                 {
                                           System.out.print("1");
                                           c++;
                                           v++;
                                } 
                        }
           }
    System.out.println();
   System.out.println("Closing connection"); 
            socket.close(); 
            in.close(); 
    } 
  
    public static void main(String args[])  throws UnknownHostException,IOException
    { 
       bitstuffingserver server = new bitstuffingserver(5000); 
    } 
} 
            