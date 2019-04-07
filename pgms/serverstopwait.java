import java.util.*;
import java.io.*;
import java.net.*;
public class serverstopwait{
     public serverstopwait(int port)throws UnknownHostException,IOException{
      ServerSocket ss=new ServerSocket(port);
      System.out.println("started");
      Socket s=ss.accept();
      System.out.println("client connected");
      DataInputStream input=new DataInputStream(s.getInputStream());
      DataOutputStream out=new DataOutputStream(s.getOutputStream());
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
          input.readUTF();
         }
     }
    }
     public static void main(String args[])throws UnknownHostException,IOException{ 
          serverstopwait ssw=new serverstopwait(5000);
     }
}
