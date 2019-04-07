import java.util.*;
import java.io.*;
import java.net.*;
public class serverStopWaitARQ{
     public serverStopWaitARQ(int port)throws UnknownHostException,IOException{
      ServerSocket ss=new ServerSocket(port);
      System.out.println("started");
      Socket s=ss.accept();
      System.out.println("client connected");
      DataInputStream input=new DataInputStream(s.getInputStream());
      DataOutputStream out=new DataOutputStream(s.getOutputStream());
      System.out.print("Waiting time is:");
      int t=Integer.parseInt(input.readUTF());
      System.out.println(t);
      System.out.println("Do you want to receive frames with errors(1/0)?");
      Scanner sc=new Scanner(System.in);
      int d=sc.nextInt();
      String ack="Received";
      if(d==0){
       while(true){
        String str=input.readUTF();
        System.out.println(str);
        out.writeUTF(ack);
        System.out.println(ack);
      }
     }
     else{
        while(true){
          String str1=input.readUTF();
          System.out.println(str1);
          out.writeUTF(ack);
          System.out.println(ack);
          String temp=input.readUTF();
          out.writeUTF("Not");
          System.out.println("Not Received");
          for(int i=0;i<1000*t;i++);
          str1=input.readUTF();
          out.writeUTF(ack);
          System.out.println(str1);
          System.out.println(ack);
         }
     }
    }
     public static void main(String args[])throws UnknownHostException,IOException{ 
          serverStopWaitARQ ssw=new serverStopWaitARQ(5000);
     }
}
