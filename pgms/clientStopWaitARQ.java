import java.io.*;
import java.util.*;
import java.net.*;
public class clientStopWaitARQ{
    public clientStopWaitARQ(String ip,int port)throws UnknownHostException,IOException{
        Socket s=new Socket(ip,port);
        System.out.println("connected");
        DataOutputStream out=new DataOutputStream(s.getOutputStream());
        DataInputStream input=new DataInputStream(s.getInputStream());
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the waiting time in seconds");
        int t=sc.nextInt();
        out.writeUTF(""+t);
        System.out.println("");
        int fn=0;
        while(true){
          System.out.println("Enter data Frame"+fn);
          String df=sc.next();
          out.writeUTF(df);
          String ack=input.readUTF();
          if(!ack.equals("Received")){
              for(int i=0;i<1000*t;i++);
              System.out.println("Retransmitting "+df);
              out.writeUTF(df);
              ack=input.readUTF();
          }
          fn++;
        }
    }
    public static void main(String args[])throws UnknownHostException,IOException{
        clientStopWaitARQ csw=new clientStopWaitARQ("127.0.0.1",5000);
    }
}
