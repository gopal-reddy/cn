import java.net.*; 
import java.io.*; 
public class characterstuffingserver
{
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
     public characterstuffingserver(int port)  throws UnknownHostException,IOException
    { 
       server = new ServerSocket(port); 
       System.out.println("Server started"); 
       System.out.println("Waiting for a client ..."); 
       socket = server.accept(); 
       System.out.println("Client accepted"); 
       in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
       char k=in.readChar();
       char dataword[]=new char[100];
       System.out.println("encoded data is");
       System.out.print("STX");
       int i=0;
         while(k!='1')
        {
            dataword[i++]=k;
             System.out.print(k);
             k=in.readChar();
       }
       System.out.println("ETX");
       System.out.println("After decoding");
       dataword[i]='\0';
       char space[ ]={'D','L','E','\0'};
        for(int v=0;dataword[v]!='\0';v++)
        {
              int c=0;
             for(int j=0;j<=2;j++)
              {
                  if(dataword[v]!=space[j])
                  {
                        break;
                  }
                   else
                       {
                        c++;
                        v++;
                      }
              }
             if(c==0)
             {
                 System.out.print(dataword[v]);
             }
             else if(c==3)
             {
                 System.out.print("  ");
                v=v-1;
             }
             else
             {
                 for(int x=0;x<c;x++)
                  {
                       System.out.print(space[x]) ;    
                 }    
                 v=v-1;
             }
      }
    System.out.println();
   System.out.println("Closing connection"); 
            socket.close(); 
            in.close(); 
    } 
  
    public static void main(String args[])  throws UnknownHostException,IOException
    { 
       characterstuffingserver server = new characterstuffingserver(5000); 
    } 
} 
              
      


              
                
