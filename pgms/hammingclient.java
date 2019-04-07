import java.net.*;
import java.io.*;
public class hammingclient
{
private Socket socket=null;
private DataInputStream input=null;
private DataOutputStream out=null;
public hammingclient(String address,int port) throws UnknownHostException,IOException
{
socket=new Socket(address,port);
input=new DataInputStream(System.in);
out=new DataOutputStream(socket.getOutputStream());
String data;
System.out.println("enter the 8 digits");
data=input.readLine();
char dataword[]=data.toCharArray();
int dataword1[]=new int[12];
for(int i=0,j=0;i<12;i++)
{
if(i==0 || i==1 || i==3 || i==7)
        {
            dataword1[i]=0;
            continue;
        }
    dataword1[i]= dataword[j++]-48;
}
int p1,p2,p4,p8;
    p1=dataword1[2]^dataword1[4]^dataword1[6]^dataword1[8]^dataword1[10];
    dataword1[0]=p1;
    p2=dataword1[2]^dataword1[5]^dataword1[6]^dataword1[9]^dataword1[10];
    dataword1[1]=p2;
    p4=dataword1[4]^dataword1[5]^dataword1[6]^dataword1[11];
    dataword1[3]=p4;
    p8=dataword1[8]^dataword1[9]^dataword1[10]^dataword1[11];
    dataword1[7]=p8;
    for(int i=0;i<12;i++)
    {
        System.out.println( dataword1[i]);
        out.writeInt(dataword1[i]);
    }
   input.close(); 
   out.close(); 
  socket.close(); 
 
    } 
public static void main(String args[])  throws UnknownHostException,IOException
    { 
        hammingclient client = new hammingclient("127.0.0.1", 5000); 
    } 
} 

       

