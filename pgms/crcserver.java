import java.net.*; 
import java.io.*; 
public class crcserver
{
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
    int datalen,divlen;
    int dataword[],divisor[],codeword[];
     public crcserver(int port)  throws UnknownHostException,IOException
    { 
       server = new ServerSocket(port); 
       System.out.println("Server started"); 
       System.out.println("Waiting for a client ..."); 
       socket = server.accept(); 
       System.out.println("Client accepted"); 
       in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
      datalen=in.readInt();
      divlen=in.readInt();
      dataword = new int[datalen];
      codeword=new int[datalen+divlen-1];
      divisor=new int[divlen];
        for(int i=0;i<divlen;i++)
        {
         divisor[i]=in.readInt();  
        }
        for(int i=0;i<datalen;i++)
        {
             dataword[i]=in.readInt();
              codeword[i]=dataword[i];
             System.out.println(codeword[i]);
        }
       for(int i=datalen;i<(datalen+divlen-1);i++)
       {
               codeword[i]=in.readInt();
               System.out.println(codeword[i]);
      }
  }
public int[] rem0(int rem[],int i)
{
	int j;
	for (j = 1; j<divlen; j++)
	{
		rem[j - 1] = rem[j];
		if (j == divlen - 1)
			rem[j] = codeword[i];
	}
	return rem;
}
public int[] rem1(int rem[], int i)
{
	int j;
	for (j = 0; j<divlen; j++)
		rem[j] = rem[j] ^ divisor[j];
	if (i == datalen+divlen-1)
		return rem;
	if (rem[0] == 0)
		rem = rem0( rem,i);
	return rem;
}
public  void server( ) throws UnknownHostException,IOException
{
	int  i = divlen, j = 0,n=datalen+divlen-1,m=divlen;
	int  rem[]=new int[i];
	for (j = 0; j<m; j++)
		rem[j] = codeword[j];
	while (i<n)
	{
		if (rem[0] == 1)
			rem = rem1(rem, i);
		else
			rem = rem0(rem,i);
		i++;
	}
	if (rem[0] == 1)
		rem = rem1(rem, i);
	for (j = m - 1, i = n - 1; i >= (n -m + 1); i--, j--)
		codeword[i] = rem[j];
                for (i = 0; i<datalen && dataword[i] == codeword[i]; i++);
	if (i == datalen)
		System.out.println("\nData bits not corrupted");
	else
		System.out.println("\nData bits corrupted");
               
   System.out.println("Closing connection"); 
  
            socket.close(); 
            in.close(); 
    } 
  
    public static void main(String args[])  throws UnknownHostException,IOException
    { 
        crcserver crcs= new crcserver(5000); 
         crcs.server();
    } 
}