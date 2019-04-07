import java.io.*;
import java.util.*;
import java.net.*;
public class clientstopwait{
    public clientstopwait(String ip,int port)throws UnknownHostException,IOException{
        Socket s=new Socket(ip,port);
        System.out.println("connected");
        DataOutputStream out=new DataOutputStream(s.getOutputStream());
        DataInputStream input=new DataInputStream(s.getInputStream());
        Scanner sc=new Scanner(System.in);
        while(true){
          System.out.println("Enter data Frame");
          String df=sc.nextLine();
          out.writeUTF(df);
          String ack=input.readUTF();
          if(!ack.equals("Received")){
              for(int i=0;i<10000000;i++);
          }
        }
    }
    public static void main(String args[])throws UnknownHostException,IOException{
        clientstopwait csw=new clientstopwait("127.0.0.1",5000);
    }
}
