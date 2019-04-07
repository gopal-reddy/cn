import java.util.*;
import java.io.*;
import java.net.*;
public class serverSelectiveRepeat{
     public static void printdfs(String arr[]){
        System.out.println("Received window");
         for(int i=0;i<arr.length;i++){
           System.out.println(arr[i]);
         }
     }

     public serverSelectiveRepeat(int port)throws UnknownHostException,IOException{
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
      String dfs[]=new String[ws];
      int acks[]=new int[ws];
      int i=0;
      System.out.println("Do you want to receive frames with errors(1/0)?");
      Scanner sc=new Scanner(System.in);
      int d=sc.nextInt();
      String ack="Received";
      if(d==0){
       while(true){
        while(i<ws){
          String str=input.readUTF();
          out.writeUTF(ack);
          System.out.println(ack);
          dfs[i]=str;
          acks[i]=1;
         i++;
       }
       serverSelectiveRepeat.printdfs(dfs);
       i=0;
      }
     }
     else{
        while(true){
          while(i<ws){
            String str=input.readUTF();
            out.writeUTF(ack);
            System.out.println(ack);
            dfs[i]=str;
            acks[i]=1;
            i++;
         }
         serverSelectiveRepeat.printdfs(dfs);
         i=0;
         while(i<ws){
          if(i<ws){
           String str1=input.readUTF();
           out.writeUTF(ack);
            dfs[i]=str1;
            acks[i]=1;
           System.out.println(ack);
          }
          i++;
          if(i<ws){
           dfs[i]=input.readUTF();
           out.writeUTF("Not");
           acks[i]=0;
           System.out.println("Not Received");
          }
          i++;
         }
         i=0;
         while(i<ws){
           if(acks[i]==0){
           out.writeUTF(ack);
           System.out.println(ack);
         }
         i++;
        }
        serverSelectiveRepeat.printdfs(dfs);
        i=0;
      }
     }
    }
     public static void main(String args[])throws UnknownHostException,IOException{ 
          serverSelectiveRepeat ssr=new serverSelectiveRepeat(5000);
    }
}
