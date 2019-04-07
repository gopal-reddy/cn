import java.net.*; 
import java.io.*; 
  
public class bitstuffingclient
{ 
     
    private Socket socket = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out    = null; 
  
    public bitstuffingclient(String address, int port) throws UnknownHostException,IOException
    { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
           input  = new DataInputStream(System.in); 
            out    = new DataOutputStream(socket.getOutputStream()); 
           System.out.println("enter the data");
            String dataword=input.readLine();
             char  data[ ]=dataword.toCharArray();
              int l=dataword.length();
              System.out.println("After encoding");
              for(int i=0;i<=l-1;i++)
              {
                      int c=0;
                      for(int j=0;j<=4;j++)
                      {
                                if(data[i]!='1')
                                { 
                                         out.writeChar('0');
                                         System.out.print("0");
                                        break;
                                 }
                                 else
                                 {
                                          out.writeChar('1');
                                         System.out.print("1");
                                           c++;
                                           i++;
                                } 
                        }
                                    if(c==5)
                                  {
                                        System.out.print("0");
                                        out.writeChar('0');
                                        i=i-1;
                                 }
  
               }
             out.writeChar('c');
        input.close(); 
       out.close(); 
      socket.close(); 
 } 
public static void main(String args[])  throws UnknownHostException,IOException
    { 
        bitstuffingclient client = new bitstuffingclient("127.0.0.1", 5000); 
    } 
} 
              
