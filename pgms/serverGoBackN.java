import java.util.*;
import java.io.*;
import java.net.*;
public class serverGoBackN{
     public serverGoBackN(int port)throws UnknownHostException,IOException{
      ServerSocket ss=new ServerSocket(port);
      System.out.println("started");
      Socket s=ss.accept();
      System.out.println("client connected");
      DataInputStream input=new DataInputStream(s.getInputStream());
      DataOutputStream out=new DataOutputStream(s.getOutputStream());
      System.out.print("Waiting time is:");
      int t=Integer.parseInt(input.readUTF());
      System.out.println(t);
      System.out.print("Server window size is:");
      int ws=Integer.parseInt(input.readUTF());
      System.out.println(ws);
      int i=0;
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
         i=0;
         while(i<ws){
           String str=input.readUTF();
System.out.println(str);
           out.writeUTF(ack);
           System.out.println(ack);
           i++;
        }
        i=0;
         while(i<ws){
         if(i<ws){
          String str1=input.readUTF();
          System.out.println(str1);
          out.writeUTF(ack);
          }
          i++;
         if(i<ws){
          System.out.println(ack);
          String temp=input.readUTF();
          out.writeUTF("Not");
          System.out.println("Not Received");
          }
          i++;
         }
         i=0;
         while(i<ws){
           String str=input.readUTF();
           System.out.println(str);
           out.writeUTF(ack);
           System.out.println(ack);
           i++;
        }
        i=0;
       }
     }
    }
     public static void main(String args[])throws UnknownHostException,IOException{ 
          serverGoBackN gbn=new serverGoBackN(5000);
     }
}