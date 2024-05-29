
package Client;

import com.mycompany.oop_l2.*;
import java.io.*;
import java.net.Socket;


public class main {
    public static void main(String[] args){
        Transportable c1 = new Car(4,"BMW");
        Transportable c2 = new Motobike(4,"Toyota");
        Transportable c3 = new Scooter(4,"Audi");
        Transportable c4 = new Moped(4,"PepeTheFrog");
        Transportable[] averArr = new Transportable[]{c1, c2, c3, c4};
        
        Socket clientSocket=null;
        ObjectOutputStream out=null;
        ObjectInputStream  in=null;
        
        try
        {
            clientSocket = new Socket("localhost", 4004);
            System.out.println("Соединение установлено");
            out =new ObjectOutputStream(clientSocket.getOutputStream());
            in =new ObjectInputStream(clientSocket.getInputStream());
            out.writeObject(averArr);
            System.out.println("Ответ сервера:");
            System.out.println("Среднее арифметическое цен моделей: " + in.readDouble());
            
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        finally
        {
            try{

                out.close();
                in.close();
                clientSocket.close();
            }
            catch(IOException | NullPointerException r){}
        }
    }
}
