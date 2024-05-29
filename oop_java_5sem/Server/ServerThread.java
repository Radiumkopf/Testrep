
package Server;

import java.io.*;
import java.net.*;
import com.mycompany.oop_l2.CountAndPrint;
import com.mycompany.oop_l2.Transportable;


public class ServerThread implements Runnable{
    
    private Socket socket; 

    
    public ServerThread(Socket ssocket){
        this.socket = ssocket;
    }
    
    @Override
    public void run(){
            try(
                ObjectInputStream in =new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out =new ObjectOutputStream(socket.getOutputStream()))
            {
            Transportable[] cars =(Transportable[]) in.readObject();
            out.writeDouble(CountAndPrint.totalAverage(cars));
            System.out.println("Done!");
            //socket.close();
        } catch (IOException|ClassNotFoundException ex) {}

    }
           
}

