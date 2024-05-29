
package com.mycompany.oop_l2;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;

public class OOP_L2 {

    public static void main(String[] args) throws Exception, IOException{
        
        
        Transportable sc = new Car(4,"Tota");
        Transportable c1 = new Car(4,"BMW");
        Transportable c2 = new Motobike(4,"Toyota");
        Transportable c3 = new Scooter(4,"Audi");
        Transportable c4 = new Moped(4,"PepeTheFrog");
        Transportable[] averArr = new Transportable[]{c1, c2, c3, c4};
        
        /*
        filename = "SybmbolStreamTest";
        FileWriter fileWriter = new FileWriter(filename);
        CountAndPrint.writeTransportable(transport,fileWriter);
        FileReader fileReader = new FileReader(filename);
        Transportable transport3 = CountAndPrint.readTransportable(fileReader);
        CountAndPrint.printModelAndPrices(transport3);
        fileWriter.close();
        fileReader.close();
        
        transport3 = CountAndPrint.readTransportable(new InputStreamReader(System.in));
        CountAndPrint.writeTransportable(transport3,new OutputStreamWriter(System.out));
        System.out.println();
        
        //Serialize
        filename = "Transport_Serilization";
        Transportable oldCar = new Car(3,"Toyota");
        System.out.println("---------");
        CountAndPrint.printModelAndPrices(oldCar);
        System.out.println("---------");
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut);
        objectOutputStream.writeObject(oldCar);
        
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
        Transportable newCar = (Transportable) objectInputStream.readObject();
        CountAndPrint.printModelAndPrices(newCar);
        
        fileOut.close();
        fileIn.close();
        objectOutputStream.close();
        objectInputStream.close();
        
        /*
        Transportable transport1 = new Motobike(5, "Nissan");
        CountAndPrint.printModelAndPrices(transport1);
        transport1.addNewModel("booka", 15234);

        CountAndPrint.printModelAndPrices(transport1);
        System.out.println("---------");
        transport1.modModelName("booka", "ola" );
        */
        
        

    }
}
