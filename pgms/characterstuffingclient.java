import java.net.*;
import java.io.*;
public class  characterstuffingclient
{
private Socket socket=null;
private DataInputStream input=null;
private DataOutputStream out=null;
public  characterstuffingclient(String address,int port) throws UnknownHostException,IOException
{
socket=new Socket(address,port);
input=new DataInputStream(System.in);
out=new DataOutputStream(socket.getOutputStream());
String data;
System.out.println("enter the  data");
data=input.readLine();
int l=data.length();
char dataword[]=data.toCharArray();
System.out.println("after encoding:");
System.out.print("STX");
for(int i=0;i<l;i++)
{
   if(dataword[i]==' ' ) 
   { 
      System.out.print("DLE");
      out.writeChar('D');
      out.writeChar('L');
      out.writeChar('E');
  }
  else
   {
     System.out.print(dataword[i]);
    out.writeChar(dataword[i]);
   }
}
out.writeChar('1');
System.out.print("ETX");
input.close(); 
   out.close(); 
  socket.close(); 
 } 
public static void main(String args[])  throws UnknownHostException,IOException
    { 
        characterstuffingclient client = new characterstuffingclient("127.0.0.1", 5000); 
    } 
} 

       



