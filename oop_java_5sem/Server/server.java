
package Server;

import java.io.*;

import java.net.*;

//import com.mycompany.oop_l2.CountAndPrint;

public class Server {
    
    static int port = 4004;
    
    public static void main(String[] args){
        Socket clientS;
        ServerSocket serverS;
        try{
            serverS = new ServerSocket(port);
            while (true) {

                clientS = serverS.accept();
                Thread sthread = new Thread(new ServerThread(clientS));
                sthread.start();
                sthread.join();
                clientS.close();
                
            }
            
        }
        catch(IOException | InterruptedException e){ }
    }
}
