import java.net.*;
import java.io.*;
import java.util.*;
public class crcclient
{
private Socket socket=null;
private DataInputStream input=null;
private DataOutputStream out=null;
int datalen,divlen;
int dataword[],divisor[];
String a="";
char b[];
public crcclient(String address,int port) throws UnknownHostException,IOException
{
socket=new Socket(address,port);
input=new DataInputStream(System.in);
out=new DataOutputStream(socket.getOutputStream());
System.out.println("enter the dataword length");
a=input.readLine();
datalen=Integer.parseInt(a);
System.out.println("enter the divisorword length");
a=input.readLine();
divlen=Integer.parseInt(a);
dataword=new  int[datalen+divlen-1];
divisor=new  int[divlen];
System.out.println("enter the dataword ");
a=input.readLine();
b=a.toCharArray();
int k;
for(k=0;k<datalen;k++)
{
 dataword[k]=b[k]-48;  
}
for(;k<datalen+divlen-1;k++)
{
dataword[k]=0;
}
System.out.println("enter the divisor ");
a=input.readLine();
b=a.toCharArray();
for(int i=0;i<divlen;i++)
{
 divisor[i]=b[i]-48;   
}
}
public int[] rem0(int rem[],int i) 
{
	int j;
	for (j = 1; j<divlen; j++)
	{
		rem[j - 1] = rem[j];
		if (j == divlen- 1)
			rem[j] = dataword[i];
	}
	return rem;
}
public  int[]  rem1( int rem[],int i)
{
	int j;
	for (j = 0; j<divlen; j++)
		rem[j] = rem[j] ^ divisor[j];
	if (i == datalen+divlen-1)
		return rem;
	if (rem[0] == 0)
		rem = rem0(rem,i);
	return rem;
}
public void client () throws UnknownHostException,IOException
{
	int  i = divlen, j = 0,n=datalen+divlen-1,m=divlen;
                out.writeInt(n-m+1);
                out.writeInt(m);
                 for(int k=0;k<m;k++)
                  {
                       out.writeInt(divisor[k]);
                  }

	int  rem[ ]=new int[m];
	for (j = 0; j<m; j++)
		rem[j] = dataword[j];
	while (i<n)
	{
		if (rem[0] == 1)
			rem = rem1(rem,i);
		else
			rem = rem0( rem, i);
		i++;
	}
	if (rem[0] == 1)
		rem = rem1(rem, i);
	for (j = m - 1, i = n - 1; i >= (n -m + 1); i--, j--)
		dataword[i] = rem[j];
                System.out.println("code word is");
	for(int k=0;k<n;k++)
                {
                        System.out.println(dataword[k]);
                         out.writeInt(dataword[k]);
                 }
    input.close(); 
   out.close(); 
  socket.close(); 
} 
public static void main(String args[])  throws UnknownHostException,IOException
    { 
  crcclient crcc = new crcclient("127.0.0.1", 5000); 
crcc.client();
    } 
} 

       

