import java.io.*;
import java.util.*;
import java.net.*;
public class clientSelectiveRepeat{
    public clientSelectiveRepeat(String ip,int port)throws UnknownHostException,IOException{
        Socket s=new Socket(ip,port);
        System.out.println("connected");
        DataOutputStream out=new DataOutputStream(s.getOutputStream());
        DataInputStream input=new DataInputStream(s.getInputStream());
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the waiting time in seconds");
        int t=sc.nextInt();
        out.writeUTF(""+t);
        System.out.println("Enter the window size");
        int ws=sc.nextInt();
        String dfs[]=new String[ws];
        int acks[]=new int[ws];
        int i=0,j=0; 
        out.writeUTF(""+ws);
        while(true){
         System.out.println("Transmitting window"+j);
         while(i<ws){
           System.out.println("Enter data Frame"+i);
           String df=sc.next();
           dfs[i]=df;
           out.writeUTF(df);
           String ack=input.readUTF();
           if(!ack.equals("Received")){
               acks[i]=0;
            }
            else{
               acks[i]=1;
            }
            i++;
        }
        for(i=0;i<ws;i++){
           if(acks[i]==0)
             break;
        }
        if(i!=ws){
            System.out.println("Retransmitting lost frames in window"+j);
            for(i=0;i<ws;i++){
              if(acks[i]==0){
                System.out.println(dfs[i]);
                String ack=input.readUTF();
            }
          }
        }
      i=0;
      j++;
     }        
    }
    public static void main(String args[])throws UnknownHostException,IOException{
        clientSelectiveRepeat csr=new clientSelectiveRepeat("127.0.0.1",5000);
    }
}
